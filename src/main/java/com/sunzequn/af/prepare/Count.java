package com.sunzequn.af.prepare;

import com.sunzequn.af.common.Conf;
import com.sunzequn.af.utils.ReadUtil;

/**
 * Created by sloriac on 16-9-7.
 *
 * 基本的数量统计
 */
public class Count {

    public static void main(String[] args) {
//        countGeonamesTriples();
        countDbpediaTriples();
    }

    private static void countGeonamesTriples() {
        ReadUtil readUtil = new ReadUtil(Conf.GEONAMES_CORE_TRIPLES_BY_ID);
        System.out.println("GeoNames有效三元组数量: " + readUtil.countLines());
    }

    private static void countDbpediaTriples() {
        ReadUtil readUtil = new ReadUtil(Conf.DBPEDIA_ID_LITERAL);
        System.out.println("DBpedia有效三元组数量: " + readUtil.countLines());
    }
}
