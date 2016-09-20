package com.sunzequn.af.db;

import com.sunzequn.af.common.Conf;
import com.sunzequn.af.prepare.Triple;
import com.sunzequn.af.utils.TripleUtil;
import com.sunzequn.af.utils.ReadUtil;
import com.sunzequn.af.utils.TimeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sloriac on 16-9-18.
 */
public class Triple2Db {

    public static void main(String[] args) {
//        toDb(Conf.GEONAMES_CORE_TRIPLES_BY_ID, "g_triples");
        toDb(Conf.DBPEDIA_CORE_TRIPLES_BY_ID, "d_triples");
    }

    private static void toDb(String triplesFile, String table) {
        TimeUtil.start();
        ReadUtil readUtil = new ReadUtil(triplesFile);
        TripleDao tripleDao = new TripleDao(table);
        List<String> lines = readUtil.readByLine();
        int batchNum = 100000;
        List<Triple> triples = new ArrayList<>();
        for (String line : lines) {
            Triple triple = TripleUtil.parseLineId(line);
            triples.add(triple);
            if (triples.size() == batchNum) {
                System.out.println(triples.size());
                tripleDao.addBatch(triples);
                triples = new ArrayList<>();
            }
        }
        if (triples.size() > 0) {
            System.out.println(triples.size());
            tripleDao.addBatch(triples);
        }
        TimeUtil.print();
    }
}
