package com.sunzequn.af.db;

import com.sunzequn.af.common.Conf;
import com.sunzequn.af.utils.ReadUtil;
import com.sunzequn.af.utils.TimeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sloriac on 16-9-19.
 * <p>
 * 将属性值频数写入数据库
 */
public class Frequency2Db {


    public static void main(String[] args) {
//        toDb(Conf.GEONAMES_FREQUENCY_PROP_VALUE, Conf.GEONAMES_FREQUENCY_TABLE);
        toDb(Conf.DBPEDIA_FREQUENCY_PROP_VALUE, Conf.DBPEDIA_FREQUENCY_TABLE);
    }

    private static void toDb(String triplesFile, String table) {
        TimeUtil.start();
        ReadUtil readUtil = new ReadUtil(triplesFile);
        FrequencyDao frequencyDao = new FrequencyDao(table);
        List<String> lines = readUtil.readByLine();
        int batchNum = 100000;
        List<Frequency> frequencies = new ArrayList<>();
        for (String line : lines) {
            String[] params = line.split(Conf.SPLIT);
            String[] propValue = params[0].split("_");
            Frequency frequency = new Frequency(propValue[0], propValue[1], Integer.parseInt(params[1]));
            frequencies.add(frequency);
            if (frequencies.size() == batchNum) {
                System.out.println(frequencies.size());
                frequencyDao.addBatch(frequencies);
                frequencies = new ArrayList<>();
            }
        }
        if (frequencies.size() > 0) {
            System.out.println(frequencies.size());
            frequencyDao.addBatch(frequencies);
        }
        TimeUtil.print();
    }
}
