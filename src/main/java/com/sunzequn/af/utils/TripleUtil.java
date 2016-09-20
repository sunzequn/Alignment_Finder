package com.sunzequn.af.utils;

import com.sunzequn.af.common.Conf;
import com.sunzequn.af.db.Triple;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sloriac on 16-9-16.
 */
public class TripleUtil {

    public static Map<String, Integer> getFrequencyPropValues(List<Triple> triples, int threshold) {
        Map<String, Integer> propValuesMap = new HashMap<>();
        for (Triple triple : triples) {
            String key = triple.getP() + "_" + triple.getO();
            MapUtil.addValueNum(propValuesMap, key);
        }
        Map<String, Integer> temp = new HashMap<>();
        propValuesMap.entrySet().stream().filter(entry -> entry.getValue() > threshold).forEach(entry -> {
            temp.put(entry.getKey(), entry.getValue());
        });
        return temp.size() > 0 ? temp : null;
    }

    public static Triple parseLineId(String line) {
        String[] params = line.split(Conf.SPLIT);
        if (params.length == 3) {
            return new Triple(params[0], params[1], params[2]);
        }
        return null;
    }

    /**
     * 将ttl文件的一行解析为一个三元组
     *
     * @param line
     * @return
     */
    public static Triple parseLine(String line) {
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
}
