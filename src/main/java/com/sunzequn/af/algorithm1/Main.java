package com.sunzequn.af.algorithm1;

import com.sun.org.apache.bcel.internal.generic.LSHL;
import com.sunzequn.af.common.Conf;
import com.sunzequn.af.common.Constant;
import com.sunzequn.af.utils.ReadUtil;
import com.sunzequn.af.utils.SerializableUtil;
import com.sunzequn.af.utils.TimeUtil;
import com.sunzequn.af.utils.WriteUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by sloriac on 16-9-18.
 */
public class Main {

    public static void main(String[] args) throws Exception {
//        toFile();
        fromFile();
    }

    private static void fromFile() throws Exception {
        File file = new File(Conf.BLOCK);
        LineIterator it = FileUtils.lineIterator(file, "UTF-8");
        int num = 0;
        TimeUtil.start();
        while (it.hasNext()) {
            num++;
            System.out.println("第 " + num + " 个Block");
            String line = it.nextLine();
            //最后一行有问题
            if (num == 3) {
                try {
                    Block block = (Block) SerializableUtil.str2Object(line);
                    block.mine();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        TimeUtil.print();
    }

    private static void toFile() throws Exception {
        Map<String, String> matchedInstances = Constant.matchedInstances;
        //从文件加载target属性频数
        TargetRelations targetRelations = new TargetRelations(Constant.TARGET_PROP_FILE);
        WriteUtil writeUtil = new WriteUtil(Conf.BLOCK, false);
        int num = 0;
        while (!targetRelations.isEmpty()) {
            num++;
            if (num == 5) {
                writeUtil.close();
                return;
            }
            String[] relation = targetRelations.popRelation();
            String propId = relation[0];
            //对每一个属性构造block
            Double score = Double.parseDouble(relation[1]);
            TimeUtil.start();
            System.out.println("为 " + propId + " 构造Block: ");
            Block block = new Block(propId, score);
            block.init();
            writeUtil.write(SerializableUtil.object2Str(block));
            writeUtil.flush();
            TimeUtil.print();
            System.out.println();
        }
        writeUtil.close();
    }
}
