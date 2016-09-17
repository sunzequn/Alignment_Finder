package com.sunzequn.af.algorithm1.partition;

import com.sunzequn.af.prepare.Triple;

import java.util.List;

/**
 * Created by sloriac on 16-9-17.
 */
public class LinkedPair {

    private String targetInstanceId;
    private String sourceInstanceId;
    private List<Triple> targetFacts;
    private List<Triple> sourceFacts;

    public LinkedPair(String targetInstanceId, String sourceInstanceId, List<Triple> targetFacts, List<Triple> sourceFacts) {
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

    public List<Triple> getTargetFacts() {
        return targetFacts;
    }

    public void setTargetFacts(List<Triple> targetFacts) {
        this.targetFacts = targetFacts;
    }

    public List<Triple> getSourceFacts() {
        return sourceFacts;
    }

    public void setSourceFacts(List<Triple> sourceFacts) {
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
