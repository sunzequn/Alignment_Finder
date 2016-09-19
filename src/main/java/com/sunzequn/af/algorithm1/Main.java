package com.sunzequn.af.algorithm1;

import com.sunzequn.af.algorithm1.load_file.TargetRelations;
import com.sunzequn.af.algorithm1.partition.Block;
import com.sunzequn.af.common.Conf;
import com.sunzequn.af.utils.TimeUtil;

/**
 * Created by sloriac on 16-9-18.
 */
public class Main {

    private static final String TARGE_PROP_FREQUENCY = Conf.DBPEDIA_FREQUENCY_PROP;
    private static final String TARGET_TRIPLES_TABLE = Conf.DBPEDIA_TRIPLES_TABLE;
    private static final String SOURCE_TRIPLES_TABLE = Conf.GEONAMES_TRIPLES_TABLE;

    public static void main(String[] args) {

        //加载target属性频数
        TargetRelations targetRelations = new TargetRelations(TARGE_PROP_FREQUENCY);

        while (!targetRelations.isEmpty()) {
            String[] relation = targetRelations.popRelation();
            String propId = relation[0];
            //对每一个属性构造block
            int frequency = Integer.parseInt(relation[1]);
            TimeUtil.start();
            Block block = new Block(TARGET_TRIPLES_TABLE, SOURCE_TRIPLES_TABLE, propId, frequency);
            block.init();
            TimeUtil.print("为 " + propId + " 构造Block ");
        }

    }
}
