package com.sunzequn.af.algorithm1;

import com.sunzequn.af.common.Conf;
import com.sunzequn.af.utils.MapUtil;
import com.sunzequn.af.utils.ReadUtil;
import com.sunzequn.af.utils.WriteUtil;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sloriac on 16-9-19.
 * <p>
 * 给目标属性打分，决定循环顺序以及过滤掉那些属性
 */
public class ScoreTargetProp {

    public static void main(String[] args) {
        generateScoreOfProp(Conf.GEONAMES_FREQUENCY_PROP, Conf.GEONAMES_PROP_RANGE, Conf.GEONAMES_PROP_SCORE);
        generateScoreOfProp(Conf.DBPEDIA_FREQUENCY_PROP, Conf.DBPEDIA_PROP_RANGE, Conf.DBPEDIA_PROP_SCORE);
    }

    private static void generateScoreOfProp(String propFrequencyFile, String propRangeFile, String targetFile) {
        Map<String, Integer> propFrequencyMap = parseTwoColumnFile(propFrequencyFile);
        Map<String, Integer> propRangeMap = parseTwoColumnFile(propRangeFile);
        Map<String, Double> propScoreMap = new HashMap<>();
        for (Map.Entry<String, Integer> entry : propFrequencyMap.entrySet()) {
            int frequency = entry.getValue();
            int range = propRangeMap.get(entry.getKey());
            double score = ((double) frequency) / ((double) range);
            score = Double.parseDouble(new DecimalFormat("#.00").format(score));
            propScoreMap.put(entry.getKey(), score);
        }
        List<Map.Entry<String, Double>> entries = MapUtil.sortMapByDoubleValue(propScoreMap);
        toFile(entries, targetFile);

    }

    private static Map<String, Integer> parseTwoColumnFile(String file) {
        Map<String, Integer> map = new HashMap<>();
        ReadUtil readUtil = new ReadUtil(file);
        List<String> lines = readUtil.readByLine();
        for (String line : lines) {
            String[] params = line.split(Conf.SPLIT);
            map.put(params[0], Integer.parseInt(params[1]));
        }
        return map;
    }

    private static void toFile(List<Map.Entry<String, Double>> entries, String file) {
        WriteUtil writeUtil = new WriteUtil(file, false);
        for (Map.Entry<String, Double> entry : entries) {
            writeUtil.write(entry.getKey() + Conf.SPLIT + entry.getValue());
        }
        writeUtil.close();
    }
}
