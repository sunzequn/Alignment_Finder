package com.sunzequn.af.prepare;

import com.sunzequn.af.utils.ReadUtil;
import com.sunzequn.af.utils.TimeUtil;
import com.sunzequn.af.utils.WriteUtil;

import java.util.*;

/**
 * Created by sloriac on 16-9-9.
 * <p>
 * 统计谓词和键值对的频数
 */
public class Frequency {

    public static void main(String[] args) {
//        handlePropFrequency(CONF.GEONAMES_CORE_TRIPLES_BY_ID, CONF.GEONAMES_FREQUENCY_PROP);
        handlePropFrequency(CONF.DBPEDIA_CORE_TRIPLES_BY_ID, CONF.DBPEDIA_FREQUENCY_PROP);

//        handleKVFrequency(CONF.GEONAMES_CORE_TRIPLES_BY_ID, CONF.GEONAMES_FREQUENCY_KV);
//        handleKVFrequency(CONF.DBPEDIA_CORE_TRIPLES_BY_ID, CONF.DBPEDIA_FREQUENCY_KV);

    }

    private static void handlePropFrequency(String tripleByIdFile, String targetFile) {

        TimeUtil.start();

        Map<Long, Long> propsMap = new HashMap<>();
        ReadUtil readUtil = new ReadUtil(tripleByIdFile);
        WriteUtil writeUtil = new WriteUtil(targetFile, false);
        List<String> lines = readUtil.readByLine();
        System.out.println("三元组数目： " + lines.size());

        for (String line : lines) {
            String[] params = line.split("\t");
            //取得谓词id
            long pid = Long.parseLong(params[1]);
            if (propsMap.containsKey(pid)) {
                long value = propsMap.get(pid);
                value++;
                propsMap.put(pid, value);
            } else {
                propsMap.put(pid, 1L);
            }
        }

        System.out.println("谓词数量： " + propsMap.size());

        Set<Map.Entry<Long, Long>> entries = propsMap.entrySet();
        for (Map.Entry<Long, Long> entry : entries) {
            writeUtil.write(entry.getKey() + "\t" + entry.getValue());
        }
        writeUtil.close();

        TimeUtil.print();
    }

    private static void handleKVFrequency(String tripleByIdFile, String targetFile) {

        TimeUtil.start();

        Map<String, Long> kvMap = new HashMap<>();
        ReadUtil readUtil = new ReadUtil(tripleByIdFile);
        WriteUtil writeUtil = new WriteUtil(targetFile, false);
        List<String> lines = readUtil.readByLine();
        System.out.println("三元组数目： " + lines.size());

        for (String line : lines) {
            String[] params = line.split("\t");
            //取得谓词id
            long pid = Long.parseLong(params[1]);
            long oid = Long.parseLong(params[2]);
            String kv = pid + "_" + oid;
            if (kvMap.containsKey(kv)) {
                long value = kvMap.get(kv);
                value++;
                kvMap.put(kv, value);
            } else {
                kvMap.put(kv, 1L);
            }
        }

        System.out.println("键值对数量： " + kvMap.size());

        Set<Map.Entry<String, Long>> entries = kvMap.entrySet();
        for (Map.Entry<String, Long> entry : entries) {
            writeUtil.write(entry.getKey() + "\t" + entry.getValue());
        }
        writeUtil.close();

        TimeUtil.print();
    }
}
