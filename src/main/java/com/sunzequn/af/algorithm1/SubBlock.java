package com.sunzequn.af.algorithm1;

import java.util.List;

/**
 * Created by sloriac on 16-9-17.
 *
 * 针对一个属性值对
 */
public class SubBlock {

    private String targetTable;
    private String sourceTable;
    //和Block的目标属性是一样的
    private String targetPropId;
    private String targetPropValueId;
    private int frequency;
    private List<LinkedPair> linkedPairs;

    public SubBlock(String targetTable, String sourceTable, String targetPropId, String targetPropValueId, int frequency) {
        this.targetTable = targetTable;
        this.sourceTable = sourceTable;
        this.targetPropId = targetPropId;
        this.targetPropValueId = targetPropValueId;
        this.frequency = frequency;
    }

}
