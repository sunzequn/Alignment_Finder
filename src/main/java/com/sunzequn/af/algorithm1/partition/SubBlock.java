package com.sunzequn.af.algorithm1.partition;

import java.util.List;

/**
 * Created by sloriac on 16-9-17.
 */
public class SubBlock {

    private String targetPropId;
    private String targetPropValueId;
    private List<LinkedPair> linkedPairs;

    public SubBlock(String targetPropId, String targetPropValueId, List<LinkedPair> linkedPairs) {
        this.targetPropId = targetPropId;
        this.targetPropValueId = targetPropValueId;
        this.linkedPairs = linkedPairs;
    }

    public String getTargetPropId() {
        return targetPropId;
    }

    public void setTargetPropId(String targetPropId) {
        this.targetPropId = targetPropId;
    }

    public String getTargetPropValueId() {
        return targetPropValueId;
    }

    public void setTargetPropValueId(String targetPropValueId) {
        this.targetPropValueId = targetPropValueId;
    }

    public List<LinkedPair> getLinkedPairs() {
        return linkedPairs;
    }

    public void setLinkedPairs(List<LinkedPair> linkedPairs) {
        this.linkedPairs = linkedPairs;
    }

    @Override
    public String toString() {
        return "SubBlock{" +
                "targetPropId='" + targetPropId + '\'' +
                ", targetPropValueId='" + targetPropValueId + '\'' +
                ", linkedPairs=" + linkedPairs +
                '}';
    }
}
