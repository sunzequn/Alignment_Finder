package com.sunzequn.af.algorithm1;

import com.sunzequn.af.common.Constant;
import com.sunzequn.af.utils.TimeUtil;

import java.util.Map;

/**
 * Created by sloriac on 16-9-18.
 */
public class Main {

    public static void main(String[] args) {
        Map<String, String> matchedInstances = Constant.matchedInstances;
        //从文件加载target属性频数
        TargetRelations targetRelations = new TargetRelations(Constant.TARGET_PROP_FILE);

        while (!targetRelations.isEmpty()) {
            String[] relation = targetRelations.popRelation();
            String propId = relation[0];
            //对每一个属性构造block
            Double score = Double.parseDouble(relation[1]);
            TimeUtil.start();
            System.out.println("为 " + propId + " 构造Block: ");
            Block block = new Block(propId, score);
            block.init();
            TimeUtil.print();
            System.out.println();
        }

    }
}
