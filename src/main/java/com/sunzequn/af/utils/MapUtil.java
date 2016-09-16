package com.sunzequn.af.utils;

import java.util.*;

/**
 * Created by sloriac on 16-9-16.
 */
public class MapUtil {

    public static void addValueNum(Map<String, Integer> map, String key) {
        if (map.containsKey(key)) {
            int value = map.get(key);
            value++;
            map.put(key, value);
        } else {
            map.put(key, 1);
        }
    }

    public static List<Map.Entry<String, Integer>> sortMapByValue(Map<String, Integer> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return null;
        }
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(oriMap.entrySet());
        Collections.sort(entries, (o1, o2) -> o2.getValue() - o1.getValue());
        return entries;
    }

}
