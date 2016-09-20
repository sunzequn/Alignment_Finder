package com.sunzequn.af.prepare;

import com.sunzequn.af.common.Conf;
import com.sunzequn.af.common.IdSet;
import com.sunzequn.af.db.Triple;
import com.sunzequn.af.utils.TripleUtil;
import com.sunzequn.af.utils.WriteUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;

/**
 * Created by sloriac on 16-9-16.
 */
public class LinkedInstance2Id {

    public static void main(String[] args) throws Exception {
        IdSet.load();
        LineIterator it = FileUtils.lineIterator(new File(Conf.SAME_AS_FILE), "utf-8");
        WriteUtil writeUtil = new WriteUtil(Conf.SAME_AS_FILE_BY_ID, false);
        int num = 0;
        while (it.hasNext()) {
            String line = it.nextLine();
            Triple triple = TripleUtil.parseLine(line);
            //s是Dbpedia, o是GeoNames
            String dbpediaUriId = IdSet.dbpediaIdUri.get(triple.getS());
            String geonamesUriId = IdSet.geonamesIdUri.get(triple.getO());
            if (dbpediaUriId == null || geonamesUriId == null) {
                System.out.println("获取id失败");
                num++;
            } else {
                writeUtil.write(dbpediaUriId + Conf.SPLIT + geonamesUriId);
            }
        }
        writeUtil.close();
        System.out.println(num);
    }
}
