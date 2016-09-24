package com.sunzequn.af.algorithm1;

import com.sunzequn.af.common.Constant;
import com.sunzequn.af.db.TripleDao;
import com.sunzequn.af.prepare.Triple;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sloriac on 16-9-17.
 */
class LinkedPair implements Serializable {

    private static final long serialVersionUID = 1L;

    private String targetInstanceId;
    private String sourceInstanceId;
    //    private Map<String, String> targetFacts = new HashMap<>();
    private Map<String, String> sourceFacts = new HashMap<>();

    LinkedPair(String targetInstanceId, String sourceInstanceId) {
        this.targetInstanceId = targetInstanceId;
        this.sourceInstanceId = sourceInstanceId;
    }

    void init() {

//        List<Triple> targetRelatedTriples = Constant.targetTripleDao.getByS(targetInstanceId);
        List<Triple> sourceRelatedTriples = Constant.sourceTripleDao.getByS(sourceInstanceId);
        if (sourceRelatedTriples == null) {
//            System.out.println(targetInstanceId + " " + sourceInstanceId);
            return;
        }
//        for (Triple targetRelatedTriple : targetRelatedTriples) {
//            targetFacts.put(targetRelatedTriple.getP(), targetRelatedTriple.getO());
//        }
        for (Triple sourceRelatedTriple : sourceRelatedTriples) {
            sourceFacts.put(sourceRelatedTriple.getP(), sourceRelatedTriple.getO());
        }
    }

    public String getTargetInstanceId() {
        return targetInstanceId;
    }

    public String getSourceInstanceId() {
        return sourceInstanceId;
    }

    public Map<String, String> getSourceFacts() {
        return sourceFacts;
    }
}
