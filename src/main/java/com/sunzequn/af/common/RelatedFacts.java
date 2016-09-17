package com.sunzequn.af.common;

import com.sunzequn.af.utils.ReadUtil;

import java.util.*;

/**
 * Created by sloriac on 16-9-17.
 */
public class RelatedFacts {

    //key是属性，value是一个二元组的list，０元是主语，１元是宾语
    private Map<String, List<String[]>> facts = new HashMap<>();

    public RelatedFacts(String factsFile) {
        ReadUtil readUtil = new ReadUtil(factsFile);
        List<String> lines = readUtil.readByLine();
        for (String line : lines) {
            String[] params = line.split(CONF.SPLIT);
            String[] so = new String[2];
            so[0] = params[0];
            so[1] = params[2];
            List<String[]> list;
            if (facts.get(params[1]) == null) {
                list = new ArrayList<>();
            } else {
                list = facts.get(params[1]);
            }
            list.add(so);
            facts.put(params[1], list);
        }
        System.out.println("所有属性的数量" + facts.size());
    }

    public List<String[]> getFactsByProp(String propId) {
        return facts.get(propId);
    }

}
