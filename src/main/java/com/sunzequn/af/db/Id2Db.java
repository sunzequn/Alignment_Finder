package com.sunzequn.af.db;

import com.sun.org.apache.bcel.internal.generic.LSHL;
import com.sunzequn.af.common.Conf;
import com.sunzequn.af.utils.ReadUtil;
import com.sunzequn.af.utils.SerializableUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sloriac on 16-9-20.
 */
public class Id2Db {

    public static void main(String[] args) throws Exception {
//        toDb(Conf.GEONAMES_ID_PROP, Conf.GEONAMES_TABLE_ID_PROP);
//        toDb(Conf.GEONAMES_ID_LITERAL, Conf.GEONAMES_TABLE_ID_LITERAL);
//        toDb(Conf.GEONAMES_ID_URI, Conf.GEONAMES_TABLE_ID_URI);

        toDb(Conf.DBPEDIA_ID_LITERAL, Conf.DBPEDIA_TABLE_ID_LITERAL);
        toDb(Conf.DBPEDIA_ID_PROP, Conf.DBPEDIA_TABLE_ID_PROP);
        toDb(Conf.DBPEDIA_ID_URI, Conf.DBPEDIA_TABLE_ID_URI);
    }

    private static void toDb(String idFile, String idTable) throws Exception {
        ReadUtil readUtil = new ReadUtil(idFile);
        List<String> lines = readUtil.readByLine();
        IdDao idDao = new IdDao(idTable);
        List<Id> ids = new ArrayList<>();
        int batchNum = 1000;
        for (String line : lines) {
            String[] params = line.split(Conf.SPLIT);
            Id id = new Id(params[0], (String) SerializableUtil.str2Object(params[1]));
            ids.add(id);
            if (ids.size() == batchNum) {
                System.out.println(ids.size());
                idDao.addBatch(ids);
                ids = new ArrayList<>();
            }
        }
        if (ids.size() > 0) {
            System.out.println(ids.size());
            idDao.addBatch(ids);
        }
    }
}
