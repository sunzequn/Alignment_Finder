package com.sunzequn.af.algorithm1.load_file;

import com.sunzequn.af.common.Conf;
import com.sunzequn.af.utils.ReadUtil;

import java.util.*;

/**
 * Created by sloriac on 16-9-17.
 */
public class RelatedFacts {

    //key是属性，value是一个二元组的list，０元是主语，１元是宾语
    private Map<String, List<String[]>> factsPSO = new HashMap<>();
    //key是主语，value是一个二元组的list，０元是谓词，１元是宾语
    private Map<String, List<String[]>> factsSPO = new HashMap<>();

    public RelatedFacts(String factsFile) {
        ReadUtil readUtil = new ReadUtil(factsFile);
        List<String> lines = readUtil.readByLine();
        System.out.println(lines.size());
        for (String line : lines) {
            String[] params = line.split(Conf.SPLIT);
            //pso
            String[] so = new String[2];
            so[0] = params[0];
            so[1] = params[2];
            List<String[]> soList;
            if (factsPSO.get(params[1]) == null) {
                soList = new ArrayList<>();
            } else {
                soList = factsPSO.get(params[1]);
            }
            soList.add(so);
            factsPSO.put(params[1], soList);
            //spo
            String[] po = new String[2];
            po[0] = params[1];
            po[1] = params[2];
            List<String[]> poList;
            if (factsSPO.get(params[0]) == null) {
                poList = new ArrayList<>();
            } else {
                poList = factsSPO.get(params[0]);
            }
            poList.add(po);
            factsSPO.put(params[0], poList);
        }
        System.out.println("所有属性的数量" + factsSPO.size() + "/　所有主语的数量" + factsSPO.size());
    }

    public List<String[]> getFactsByProp(String propId) {
        return factsPSO.get(propId);
    }

    public List<String[]> getFactsBySubject(String uriId) {
        return factsSPO.get(uriId);
    }

}
