package com.sunzequn.af.algorithm1.partition;

import com.sunzequn.af.dao.Triple;
import com.sunzequn.af.dao.TripleDao;
import com.sunzequn.af.utils.TripleUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sloriac on 16-9-17.
 *
 * 针对一个属性
 */
public class Block {
    private String targetTable;
    private String sourceTable;
    //目标属性
    private String targetPropId;
    private int frequency;
    private List<SubBlock> subBlocks = new ArrayList<>();

    public Block(String targetTable, String sourceTable, String targetPropId, int frequency) {
        this.targetTable = targetTable;
        this.sourceTable = sourceTable;
        this.targetPropId = targetPropId;
        this.frequency = frequency;
    }

    public void init() {
        TripleDao targetTripleDao = new TripleDao(targetTable);
        List<Triple> relatedTargetTriples = targetTripleDao.getByP(targetPropId);
        if (relatedTargetTriples != null) {
            Map<String, Integer> propValueFrequency = TripleUtil.getFrequencyPropValues(relatedTargetTriples);

        }

    }

    public String getTargetPropId() {
        return targetPropId;
    }

    public int getFrequency() {
        return frequency;
    }

    public List<SubBlock> getSubBlocks() {
        return subBlocks;
    }
}
