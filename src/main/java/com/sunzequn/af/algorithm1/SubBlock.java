package com.sunzequn.af.algorithm1;

import com.sunzequn.af.common.Constant;
import com.sunzequn.af.db.TripleDao;
import com.sunzequn.af.prepare.Triple;

import java.util.*;

/**
 * Created by sloriac on 16-9-17.
 *
 * 针对一个属性值对
 */
class SubBlock {

    //和Block的目标属性是一样的
    private String targetPropId;
    private String targetPropValueId;
    private int frequency;
    private List<LinkedPair> linkedPairs = new ArrayList<>();

    SubBlock(String targetPropId, String targetPropValueId, int frequency) {
        this.targetPropId = targetPropId;
        this.targetPropValueId = targetPropValueId;
        this.frequency = frequency;
    }

    void init() {
        TripleDao targetTripleDao = new TripleDao(Constant.TARGET_TRIPLES_TABLE);
        List<Triple> targetTriples = targetTripleDao.getByPV(targetPropId, targetPropValueId);
        targetTripleDao.close();
        if (targetTriples != null) {
            Set<String> targetSubjects = new HashSet<>();
            for (Triple targetTriple : targetTriples) {
                targetSubjects.add(targetTriple.getS());
            }
            for (String targetSubject : targetSubjects) {
                String matchedSubject = Constant.matchedInstances.get(targetSubject);
                //有的实例没有抽出相关三元组数据
                if (matchedSubject == null) {
                    System.out.println(targetSubject + " 没有链接实例");
                    continue;
                }
                LinkedPair linkedPair = new LinkedPair(targetSubject, matchedSubject);
                linkedPair.init();
                linkedPairs.add(linkedPair);
            }
            System.out.println("符合pv对的链接实例对：" + linkedPairs.size());
        }
    }

    public String getTargetPropId() {
        return targetPropId;
    }

    public String getTargetPropValueId() {
        return targetPropValueId;
    }

    public int getFrequency() {
        return frequency;
    }

    public List<LinkedPair> getLinkedPairs() {
        return linkedPairs;
    }
}
