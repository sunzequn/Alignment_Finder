package com.sunzequn.af.dbpedia_geonames;

import com.sunzequn.af.common.Conf;
import com.sunzequn.af.prepare.Query;
import com.sunzequn.af.prepare.Triple;
import com.sunzequn.af.utils.ReadUtil;
import com.sunzequn.af.utils.TimeUtil;
import com.sunzequn.af.utils.TripleUtil;
import com.sunzequn.af.utils.WriteUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by sloriac on 16-11-8.
 */
public class Main {

    public static void main(String[] args) throws Exception {

        Set<String> dbpediaLinkedInstances = new HashSet<>();
        Set<String> geonamesLinkedInstances = new HashSet<>();
        List<Triple> linkedTriples = new ArrayList<>();

        ReadUtil readUtil = new ReadUtil(Conf.DBPEDIA_GEONAMES_LINKS);
        List<String> links = readUtil.readByLine();
        for (String link : links) {
            Triple triple = TripleUtil.parseTTLLine(link);
            linkedTriples.add(triple);
            dbpediaLinkedInstances.add(triple.getS());
            geonamesLinkedInstances.add(triple.getO());
        }
        System.out.println("linked triples : " + linkedTriples.size());
        System.out.println("DBpedia linked instances : " + dbpediaLinkedInstances.size());
        System.out.println("GeoNames linked instances : " + geonamesLinkedInstances.size());

        readUtil.close();

//        linkedTriples2file(dbpediaLinkedInstances, Conf.VIRTUOSO_DBPEDIA, Conf.VIRTUOSO_DBPEDIA_CORE_GRAPH, 5000, Conf.DBPEDIA_LINKED_TRIPLES);
//        linkedTriples2file(geonamesLinkedInstances, Conf.VIRTUOSO_GEONAMES, null, 5000, Conf.GEONAMES_LINKED_TRIPLES);

        oInLinked(geonamesLinkedInstances, Conf.GEONAMES_LINKED_TRIPLES, Conf.GEONAMES_O_IS_LINKED);
        oInLinked(dbpediaLinkedInstances, Conf.DBPEDIA_LINKED_TRIPLES, Conf.DBPEDIA_O_IS_LINKED);
    }


    /**
     * 查询数据库，将DBpedia链接实例的三元组写入文件
     *
     * @param linkedInstances
     * @param virtuosoPort
     * @param timeout
     * @param file
     * @throws Exception
     */
    private static void linkedTriples2file(Set<String> linkedInstances, String virtuosoPort, String graph, int timeout, String file) {
        TimeUtil.start();
        Query query = new Query();
        WriteUtil writeUtil = new WriteUtil(file, false);
        int num = 0;
        for (String uri : linkedInstances) {
            List<Triple> triples = null;
            try {
                triples = query.queryAllTriplesByS(virtuosoPort, Conf.VIRTUOSO_SUFFIX, graph, uri, timeout);
            } catch (Exception e) {
                System.out.println("error " + uri);
            }
            if (triples != null) {
                num++;
                if (num % 10000 == 0) {
                    System.out.println("processing : " + num);
                }
                for (Triple triple : triples) {
                    // 每行是一个三元组，主谓宾之间用split分割
                    writeUtil.write(triple.getS() + Conf.SPLIT + triple.getP() + Conf.SPLIT + triple.getO());
                }
                writeUtil.flush();
            }
        }
        writeUtil.close();
        System.out.println("have triples : " + num);
        TimeUtil.print();
    }

    private static void oInLinked(Set<String> linkedInstances, String tripleFile, String file) {
        ReadUtil readUtil = new ReadUtil(tripleFile);
        List<String> lines = readUtil.readByLine();
        int num = 0;
        Set<String> ss = new HashSet<>();
        Set<String> os = new HashSet<>();
        Set<String> ps = new HashSet<>();
        WriteUtil writeUtil = new WriteUtil(file, false);
        for (String line : lines) {
            Triple triple = TripleUtil.parseLine(line);
            if (triple != null && linkedInstances.contains(triple.getO())) {
//                System.out.println(triple);
                ss.add(triple.getS());
                ps.add(triple.getP());
                os.add(triple.getO());
                writeUtil.write(triple.getS() + Conf.SPLIT + triple.getP() + Conf.SPLIT + triple.getO());
                num++;
            }
        }
        writeUtil.close();
        System.out.println("到自己的直接的环: " + num);
        System.out.println("构成环的主语数: " + ss.size());
        System.out.println("构成环的谓语数: " + ps.size());
        System.out.println("构成环的宾语数: " + os.size());
    }

}
