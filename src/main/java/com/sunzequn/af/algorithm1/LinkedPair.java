package com.sunzequn.af.algorithm1;

import com.sunzequn.af.common.Constant;
import com.sunzequn.af.db.TripleDao;
import com.sunzequn.af.prepare.Triple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sloriac on 16-9-17.
 */
class LinkedPair {

    private String targetInstanceId;
    private String sourceInstanceId;
    private Map<String, String> targetFacts = new HashMap<>();
    private Map<String, String> sourceFacts = new HashMap<>();

    LinkedPair(String targetInstanceId, String sourceInstanceId) {
        this.targetInstanceId = targetInstanceId;
        this.sourceInstanceId = sourceInstanceId;
    }

    void init() {
        TripleDao targetTripleDao = new TripleDao(Constant.TARGET_TRIPLES_TABLE);
        TripleDao sourceTripleDao = new TripleDao(Constant.SOURCE_TRIPLES_TABLE);
        List<Triple> targetRelatedTriples = targetTripleDao.getByS(targetInstanceId);
        List<Triple> sourceRelatedTriples = sourceTripleDao.getByS(sourceInstanceId);
        if (targetRelatedTriples == null || sourceRelatedTriples == null) {
            System.out.println(targetInstanceId + " " + sourceInstanceId);
            return;
        }
        targetTripleDao.close();
        sourceTripleDao.close();
        for (Triple targetRelatedTriple : targetRelatedTriples) {
            targetFacts.put(targetRelatedTriple.getP(), targetRelatedTriple.getO());
        }
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

    public Map<String, String> getTargetFacts() {
        return targetFacts;
    }

    public Map<String, String> getSourceFacts() {
        return sourceFacts;
    }
}
