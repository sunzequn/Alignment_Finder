package com.sunzequn.af.algorithm1;

import com.sunzequn.af.common.Constant;
import com.sunzequn.af.common.Threshold;
import com.sunzequn.af.db.Frequency;
import com.sunzequn.af.db.FrequencyDao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sloriac on 16-9-17.
 * <p>
 * 针对一个属性
 */
class Block implements Serializable {

    private static final long serialVersionUID = 1L;

    //目标属性
    private String targetPropId;
    private double score;
    private List<SubBlock> subBlocks = new ArrayList<>();

    Block(String targetPropId, double score) {
        this.targetPropId = targetPropId;
        this.score = score;
    }

    void init() {

        //得到相关的属性值频数对
        List<Frequency> targetFrequencies = Constant.targetFrequencyDao.getByProp(targetPropId);
        if (targetFrequencies != null) {
            final int[] num = {0};
            targetFrequencies.stream().filter(targetFrequency -> targetFrequency.getFrequency() > Threshold.PROP_VALUE_FREQUENCY).forEach(targetFrequency -> {
                SubBlock subBlock = new SubBlock(targetFrequency.getProp(), targetFrequency.getValue(), targetFrequency.getFrequency());
//                System.out.println("构造subBlock: " + targetFrequency.getProp() + " " + targetFrequency.getValue());
                subBlock.init();
                if (subBlock.getLinkedPairs().size() > 0) {
                    subBlocks.add(subBlock);
                    num[0]++;
                }
            });
            System.out.println("相关属性值对数量：" + num[0]);
        }
    }

    public void mine() {
        System.out.println("Block: " + targetPropId + " " + score);
        for (SubBlock subBlock : subBlocks) {
            subBlock.mine();
        }
    }

    public String getTargetPropId() {
        return targetPropId;
    }

    public double getScore() {
        return score;
    }

    public List<SubBlock> getSubBlocks() {
        return subBlocks;
    }

}
