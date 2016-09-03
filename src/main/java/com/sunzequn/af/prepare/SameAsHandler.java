package com.sunzequn.af.prepare;

import com.sunzequn.af.utils.PropertiesUtil;
import com.sunzequn.af.utils.SerializableUtil;
import com.sunzequn.af.utils.TimeUtil;
import com.sunzequn.af.utils.WriteUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sloriac on 16-9-1.
 */
public class SameAsHandler {

    // DBpedia官方提供的到GeoNames的链接实例数据文件
    private static final String SAME_AS_FILE = "src/main/resources/data/geonames_links_en.ttl";
    private static final String VIRTUOSO_CONF = "src/main/resources/conf/virtuoso.properties";
    private static final String DBPEDIA_CORE_TRIPLES = "src/main/resources/data/dbpedia_triples_core";
    private static final String GEONAMES_CORE_TRIPLES = "src/main/resources/data/geonames_triples_core";
    private static final String DBPEDIA_CORE_GRAPH = "<http://en.dbpedia.org/>";
    private static final String GEONAMES_CORE_GRAPH = "<http://www.geonames.org/about/>";
    private static final int TIME_OUT = 6000;

    private static String geonamesBaseUrl;
    private static String dbpediaBaseUrl;
    private static String urlSuffix;

    private static Query query = new Query();
    private static WriteUtil dbpediaWriteUtil = new WriteUtil(DBPEDIA_CORE_TRIPLES, false);
    private static WriteUtil geonamesWriteUtil = new WriteUtil(GEONAMES_CORE_TRIPLES, false);

    public static void main(String[] args) throws Exception {
        init();
        TimeUtil.start();
        process();
        TimeUtil.print();
    }

    private static void init() {
        PropertiesUtil propertiesUtil = new PropertiesUtil(VIRTUOSO_CONF);
        geonamesBaseUrl = propertiesUtil.getValue("geonames.baseUrl");
        dbpediaBaseUrl = propertiesUtil.getValue("dbpedia.baseUrl");
        urlSuffix = propertiesUtil.getValue("url.suffix");
    }

    private static void process() throws Exception {
        File file = new File(SAME_AS_FILE);
        List<String> lines = FileUtils.readLines(file, "utf-8");
        System.out.println("链接实例的数据量： " + lines.size());
        for (String line : lines) {
            Triple triple = parseLine(line);
            String dbpediaUri = triple.getS();
            processData(dbpediaBaseUrl, DBPEDIA_CORE_GRAPH, dbpediaUri, dbpediaWriteUtil);
            String geonamesUri = triple.getO();
            processData(geonamesBaseUrl, GEONAMES_CORE_GRAPH, geonamesUri, geonamesWriteUtil);
        }
        close();
    }

    private static void processData(String baseUri, String graph, String uri, WriteUtil writeUtil) throws Exception {
        List<Triple> triples = processQuery(baseUri, graph, uri);
        if (triples != null) {
            for (Triple triple : triples) {
                // 将对象序列化为字符串
                String serStr = SerializableUtil.object2Str(triple);
                // 反序列化
                Triple t = (Triple) SerializableUtil.str2Object(serStr);
                // 检查序列化是否有问题
                if (triple.getS().equals(t.getS()) && triple.getP().equals(t.getP()) && triple.getO().equals(t.getO())) {
                    writeUtil.write(serStr);
                } else {
                    System.out.println("序列化出现问题");
                }
            }
            writeUtil.flush();
        }
    }

    private static List<Triple> processQuery(String baseUrl, String graph, String uri) throws Exception {
        String sparql = generateSparql(graph, uri);
        ResultSet rs = query.run(baseUrl, urlSuffix, sparql, TIME_OUT);
        if (rs == null) {
            System.out.println("查询无果" + uri);
            return null;
        }
        List<Triple> triples = new ArrayList<>();
        try {
            while (rs.hasNext()) {
                QuerySolution qs = rs.next();
                Triple triple = new Triple(uri, qs.get("p").toString(), qs.get("o").toString());
                triples.add(triple);
            }
            return triples.size() != 0 ? triples : null;
        } catch (Exception e) {
            System.out.println(uri);
            return null;
        }

    }

    /**
     * 将ttl文件的一行解析为一个三元组
     *
     * @param line
     * @return
     */
    private static Triple parseLine(String line) {
        line = StringUtils.removeEnd(line, ".").trim();
        String[] params = line.split(" ");
        if (params.length == 3) {
            return new Triple(removeBrackets(params[0]), removeBrackets(params[1]), removeBrackets(params[2]));
        }
        return null;
    }

    private static String removeBrackets(String uri) {
        uri = StringUtils.removeEnd(uri.trim(), ">");
        return StringUtils.removeStart(uri, "<").trim();
    }

    private static String generateSparql(String graph, String uri) {
        return "select * from " + graph + " where {<" + uri + "> ?p ?o}";
    }

    private static void close() {
        dbpediaWriteUtil.close();
        geonamesWriteUtil.close();
    }
}
