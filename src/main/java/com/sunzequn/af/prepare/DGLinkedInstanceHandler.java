package com.sunzequn.af.prepare;

import com.sunzequn.af.common.Conf;
import com.sunzequn.af.utils.*;
import org.apache.commons.io.FileUtils;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sloriac on 16-9-1.
 *
 * 读取sameAs文件，抽取DBpedia和GeoNames的三元组数据，写入文件
 */
public class DGLinkedInstanceHandler {

    private static final String DBPEDIA_CORE_GRAPH = "<http://en.dbpedia.org/>";
    private static final String GEONAMES_CORE_GRAPH = "<http://www.geonames.org/about/>";
    private static final int TIME_OUT = 6000;

    private static String geonamesBaseUrl;
    private static String dbpediaBaseUrl;
    private static String urlSuffix;

    private static Query query = new Query();
    private static WriteUtil dbpediaWriteUtil = new WriteUtil(Conf.DBPEDIA_CORE_TRIPLES, false);
    private static WriteUtil geonamesWriteUtil = new WriteUtil(Conf.GEONAMES_CORE_TRIPLES, false);

    public static void main(String[] args) throws Exception {
        init();
        TimeUtil.start();
        process();
        TimeUtil.print();
    }

    private static void init() {
        PropertiesUtil propertiesUtil = new PropertiesUtil(Conf.VIRTUOSO_CONF);
        geonamesBaseUrl = propertiesUtil.getValue("geonames.baseUrl");
        dbpediaBaseUrl = propertiesUtil.getValue("dbpedia.baseUrl");
        urlSuffix = propertiesUtil.getValue("url.suffix");
    }

    private static void process() throws Exception {
        File file = new File(Conf.SAME_AS_FILE);
        List<String> lines = FileUtils.readLines(file, "utf-8");
        System.out.println("链接实例的数据量： " + lines.size());
        for (String line : lines) {
            Triple triple = TripleUtil.parseLine(line);
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

    private static String generateSparql(String graph, String uri) {
        return "select * from " + graph + " where {<" + uri + "> ?p ?o}";
    }

    private static void close() {
        dbpediaWriteUtil.close();
        geonamesWriteUtil.close();
    }
}
