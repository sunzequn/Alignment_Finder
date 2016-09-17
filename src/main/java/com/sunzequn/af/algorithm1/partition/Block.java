package com.sunzequn.af.algorithm1.partition;

import java.util.List;

/**
 * Created by sloriac on 16-9-17.
 */
public class Block {

    //目标属性
    private String targetPropId;
    private List<SubBlock> subBlocks;

    public Block(String targetPropId, List<SubBlock> subBlocks) {
        this.targetPropId = targetPropId;
        this.subBlocks = subBlocks;
    }

    public String getTargetPropId() {
        return targetPropId;
    }

    public void setTargetPropId(String targetPropId) {
        this.targetPropId = targetPropId;
    }

    public List<SubBlock> getSubBlocks() {
        return subBlocks;
    }

    public void setSubBlocks(List<SubBlock> subBlocks) {
        this.subBlocks = subBlocks;
    }

    @Override
    public String toString() {
        return "Block{" +
                "targetPropId='" + targetPropId + '\'' +
                ", subBlocks=" + subBlocks +
                '}';
    }
}
