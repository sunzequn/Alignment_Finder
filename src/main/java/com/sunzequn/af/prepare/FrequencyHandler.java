package com.sunzequn.af.prepare;

import com.sunzequn.af.common.Conf;
import com.sunzequn.af.utils.MapUtil;
import com.sunzequn.af.utils.TripleUtil;
import com.sunzequn.af.utils.ReadUtil;
import com.sunzequn.af.utils.WriteUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sloriac on 16-9-16.
 * <p>
 * 统计属性已经属性值对的频数　
 */
public class FrequencyHandler {

    public static void main(String[] args) {
        propFrequency(Conf.GEONAMES_CORE_TRIPLES_BY_ID, Conf.GEONAMES_FREQUENCY_PROP);
        propFrequency(Conf.DBPEDIA_CORE_TRIPLES_BY_ID, Conf.DBPEDIA_FREQUENCY_PROP);
        propValueFrequency(Conf.GEONAMES_CORE_TRIPLES_BY_ID, Conf.GEONAMES_FREQUENCY_PROP_VALUE);
        propValueFrequency(Conf.DBPEDIA_CORE_TRIPLES_BY_ID, Conf.DBPEDIA_FREQUENCY_PROP_VALUE);
    }

    private static void propFrequency(String sourceFile, String targetFile) {
        ReadUtil readUtil = new ReadUtil(sourceFile);
        Map<String, Integer> propFrequency = new HashMap<>();
        List<String> lines = readUtil.readByLine();
        for (String line : lines) {
            Triple triple = TripleUtil.parseLineId(line);
            MapUtil.addValueNum(propFrequency, triple.getP());
        }
        toFile(propFrequency, targetFile);
    }

    private static void propValueFrequency(String sourceFile, String targetFile) {
        ReadUtil readUtil = new ReadUtil(sourceFile);
        Map<String, Integer> propValueFrequency = new HashMap<>();
        List<String> lines = readUtil.readByLine();
        for (String line : lines) {
            Triple triple = TripleUtil.parseLineId(line);
            MapUtil.addValueNum(propValueFrequency, triple.getP() + "_" + triple.getO());
        }
        toFile(propValueFrequency, targetFile);
    }

    private static void toFile(Map<String, Integer> map, String targetFile) {
        WriteUtil writeUtil = new WriteUtil(targetFile, false);
        //先排序
        List<Map.Entry<String, Integer>> entries = MapUtil.sortMapByValue(map);
        for (Map.Entry<String, Integer> entry : entries) {
            writeUtil.write(entry.getKey() + Conf.SPLIT + entry.getValue());
        }
        writeUtil.close();
    }
}
