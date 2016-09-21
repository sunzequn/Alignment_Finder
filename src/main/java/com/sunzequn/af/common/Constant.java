package com.sunzequn.af.common;

import com.sunzequn.af.utils.ReadUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sloriac on 16-9-19.
 * <p>
 * Main算法所需常量
 */
public class Constant {

    public static final String TARGET_PROP_FILE = Conf.DBPEDIA_PROP_SCORE;
    public static final String TARGET_TRIPLES_TABLE = Conf.DBPEDIA_TABLE_TRIPLES;
    public static final String SOURCE_TRIPLES_TABLE = Conf.GEONAMES_TABLE_TRIPLES;
    public static final String TARGET_FREQUENCY_TABLE = Conf.DBPEDIA_TABLE_FREQUENCY;
    public static final String SOURCE_FREQUENCY_TABLE = Conf.GEONAMES_TABLE_FREQUENCY;

    public static final Map<String, String> matchedInstances = new HashMap<>();

    static {
        ReadUtil readUtil = new ReadUtil(Conf.SAME_AS_FILE_BY_ID);
        List<String> lines = readUtil.readByLine();
        for (String line : lines) {
            String[] params = line.split(Conf.SPLIT);
            matchedInstances.put(params[0], params[1]);
        }
        System.out.println("链接实例数目：" + matchedInstances.size());
    }
}
