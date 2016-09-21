package com.sunzequn.af.algorithm1;

import com.sunzequn.af.common.Constant;
import com.sunzequn.af.common.Threshold;
import com.sunzequn.af.db.Frequency;
import com.sunzequn.af.db.FrequencyDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sloriac on 16-9-17.
 * <p>
 * 针对一个属性
 */
class Block {
    //目标属性
    private String targetPropId;
    private double score;
    private List<SubBlock> subBlocks = new ArrayList<>();

    Block(String targetPropId, double score) {
        this.targetPropId = targetPropId;
        this.score = score;
    }

    void init() {
        FrequencyDao targetFrequencyDao = new FrequencyDao(Constant.TARGET_FREQUENCY_TABLE);
        //得到相关的属性值频数对
        List<Frequency> targetFrequencies = targetFrequencyDao.getByProp(targetPropId);
        targetFrequencyDao.close();
        if (targetFrequencies != null) {
            final int[] num = {0};
            targetFrequencies.stream().filter(targetFrequency -> targetFrequency.getFrequency() > Threshold.PROP_VALUE_FREQUENCY).forEach(targetFrequency -> {
                SubBlock subBlock = new SubBlock(targetFrequency.getProp(), targetFrequency.getValue(), targetFrequency.getFrequency());
                subBlock.init();
                subBlocks.add(subBlock);
                num[0]++;
            });
            System.out.println("相关属性值对数量：" + num[0]);
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
