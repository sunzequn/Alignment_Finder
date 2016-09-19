package com.sunzequn.af.algorithm1.partition;

import java.util.List;
import java.util.Map;

/**
 * Created by sloriac on 16-9-17.
 */
public class LinkedPair {

    private String targetInstanceId;
    private String sourceInstanceId;
    private Map<String, List<String[]>> targetFacts;
    private Map<String, List<String[]>> sourceFacts;

    public LinkedPair(String targetInstanceId, String sourceInstanceId, Map<String, List<String[]>> targetFacts, Map<String, List<String[]>> sourceFacts) {
        this.targetInstanceId = targetInstanceId;
        this.sourceInstanceId = sourceInstanceId;
        this.targetFacts = targetFacts;
        this.sourceFacts = sourceFacts;
    }

    public String getTargetInstanceId() {
        return targetInstanceId;
    }

    public void setTargetInstanceId(String targetInstanceId) {
        this.targetInstanceId = targetInstanceId;
    }

    public String getSourceInstanceId() {
        return sourceInstanceId;
    }

    public void setSourceInstanceId(String sourceInstanceId) {
        this.sourceInstanceId = sourceInstanceId;
    }

    public Map<String, List<String[]>> getTargetFacts() {
        return targetFacts;
    }

    public void setTargetFacts(Map<String, List<String[]>> targetFacts) {
        this.targetFacts = targetFacts;
    }

    public Map<String, List<String[]>> getSourceFacts() {
        return sourceFacts;
    }

    public void setSourceFacts(Map<String, List<String[]>> sourceFacts) {
        this.sourceFacts = sourceFacts;
    }

    @Override
    public String toString() {
        return "LinkedPair{" +
                "targetInstanceId='" + targetInstanceId + '\'' +
                ", sourceInstanceId='" + sourceInstanceId + '\'' +
                ", targetFacts=" + targetFacts +
                ", sourceFacts=" + sourceFacts +
                '}';
    }
}
