package com.sunzequn.af.algorithm1;

import com.sunzequn.af.common.Constant;
import com.sunzequn.af.utils.TimeUtil;

/**
 * Created by sloriac on 16-9-18.
 */
public class Main {

    public static void main(String[] args) {

        //从文件加载target属性频数
        TargetRelations targetRelations = new TargetRelations(Constant.TARGET_PROP_FREQUENCY_FILE);

        while (!targetRelations.isEmpty()) {
            String[] relation = targetRelations.popRelation();
            String propId = relation[0];
            //对每一个属性构造block
            int frequency = Integer.parseInt(relation[1]);
            TimeUtil.start();
            Block block = new Block(propId, frequency);
            block.init();
            TimeUtil.print("为 " + propId + " 构造Block ");
        }

    }
}
