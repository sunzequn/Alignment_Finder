package com.sunzequn.af.prepare;

import com.sunzequn.af.utils.ParseUtil;
import com.sunzequn.af.utils.WriteUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;

/**
 * Created by sloriac on 16-9-16.
 */
public class LinkedInstance2Id {

    public static void main(String[] args) throws Exception {
        IDSET.load();
        LineIterator it = FileUtils.lineIterator(new File(CONF.SAME_AS_FILE), "utf-8");
        WriteUtil writeUtil = new WriteUtil(CONF.SAME_AS_FILE_BY_ID, false);
        int num = 0;
        while (it.hasNext()) {
            String line = it.nextLine();
            Triple triple = ParseUtil.parseLine(line);
            //s是Dbpedia, o是GeoNames
            String dbpediaUriId = IDSET.dbpediaIdUri.get(triple.getS());
            String geonamesUriId = IDSET.geonamesIdUri.get(triple.getO());
            if (dbpediaUriId == null || geonamesUriId == null) {
                System.out.println("获取id失败");
                num++;
            } else {
                writeUtil.write(dbpediaUriId + CONF.SPLIT + geonamesUriId);
            }
        }
        writeUtil.close();
        System.out.println(num);
    }
}