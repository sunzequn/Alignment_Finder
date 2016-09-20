package com.sunzequn.af.algorithm1;

import com.sunzequn.af.common.Conf;
import com.sunzequn.af.common.Threshold;
import com.sunzequn.af.utils.ReadUtil;

import java.util.*;

/**
 * Created by sloriac on 16-9-17.
 * <p>
 * 目标关系，也就是Horn子句左侧部分
 */
public class TargetRelations {

    private LinkedList<String[]> propFrequencies = new LinkedList<>();

    public TargetRelations(String propFrequencyFile) {
        ReadUtil readUtil = new ReadUtil(propFrequencyFile);
        List<String> lines = readUtil.readByLine();
        for (String line : lines) {
            String[] params = line.split(Conf.SPLIT);
            if (Integer.parseInt(params[1]) >= Threshold.PROP_FREQUENCY) {
                propFrequencies.add(params);
            }
        }
        System.out.println("待处理的关系数：" + propFrequencies.size());
    }

    public boolean isEmpty() {
        return propFrequencies.isEmpty();
    }

    /**
     * 数组的０元是属性id，１元是频数
     *
     * @return
     */
    public String[] popRelation() {
        if (propFrequencies.isEmpty())
            return null;
        return propFrequencies.pop();
    }
}

