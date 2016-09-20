package com.sunzequn.af.prepare;


import com.sunzequn.af.common.Conf;
import com.sunzequn.af.common.IdSet;
import com.sunzequn.af.utils.SerializableUtil;
import com.sunzequn.af.utils.TimeUtil;
import com.sunzequn.af.utils.WriteUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.util.Map;

/**
 * Created by sloriac on 16-9-7.
 * <p>
 * 将三元组转化为id表示
 */
public class Triples2Id {

    public static void main(String[] args) throws Exception {
        IdSet.load();
//        handleTriples(Conf.GEONAMES_CORE_TRIPLES, Conf.GEONAMES_CORE_TRIPLES_BY_ID, IdSet.geonamesIdUri, IdSet.geonamesIdProp, IdSet.geonamesIdLiteral);
        handleTriples(Conf.DBPEDIA_CORE_TRIPLES, Conf.DBPEDIA_CORE_TRIPLES_BY_ID, IdSet.dbpediaIdUri, IdSet.dbpediaIdProp, IdSet.dbpediaIdLiteral);
    }

    private static void handleTriples(String sourceFile, String targetFile, Map<String, String> idUri, Map<String, String> idProp, Map<String, String> idLiteral) throws Exception {
        TimeUtil.start();
        LineIterator it = FileUtils.lineIterator(new File(sourceFile), "utf-8");
        WriteUtil writeUtil = new WriteUtil(targetFile, false);
        long index = 0;
        while (it.hasNext()) {
            String line = it.nextLine();
            Triple t = (Triple) SerializableUtil.str2Object(line);
            if (t.getP().equals("http://www.w3.org/1999/02/22-rdf-syntax-ns#type") && (!t.getO().startsWith("http://dbpedia.org/ontology"))) {
                continue;
            }
            String sid = idUri.get(t.getS());
            String pid = idProp.get(t.getP());
            String oid;
            if (t.getO().startsWith("http://")) {
                oid = idUri.get(t.getO());
            } else {
                oid = idLiteral.get(t.getO());
            }
            if (sid == null || pid == null || oid == null) {
                System.out.println("id获取出错");
                continue;
            }
            try {
                writeUtil.write(sid + Conf.SPLIT + pid + Conf.SPLIT + oid);
                index++;
                if (index % 100000 == 0) {
//                    System.out.println(index);
                    writeUtil.flush();
                }
            } catch (Exception e) {
                System.out.println("id出错 " + t);
            }
        }
        writeUtil.close();
        LineIterator.closeQuietly(it);
        System.out.println("三元组数量： " + index);
        TimeUtil.print();
    }


}
