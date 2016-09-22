package com.sunzequn.af.prepare;

import com.sunzequn.af.common.Conf;
import com.sunzequn.af.utils.ReadUtil;
import com.sunzequn.af.utils.TripleUtil;
import com.sunzequn.af.utils.WriteUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by sloriac on 16-9-21.
 */
public class Delete {

    public static void main(String[] args) {
        ReadUtil sameAsReadUtil = new ReadUtil(Conf.SAME_AS_FILE_BY_ID);
//        ReadUtil readUtil = new ReadUtil(Conf.DBPEDIA_CORE_TRIPLES_BY_ID + "_before_delete");
//        WriteUtil writeUtil  = new WriteUtil(Conf.DBPEDIA_CORE_TRIPLES_BY_ID, false);
        ReadUtil readUtil = new ReadUtil(Conf.GEONAMES_CORE_TRIPLES_BY_ID + "_before_delete");
        WriteUtil writeUtil = new WriteUtil(Conf.GEONAMES_CORE_TRIPLES_BY_ID, false);

        Set<String> linkedInstanceIDs = new HashSet<>();
        List<String> lines = sameAsReadUtil.readByLine();
        for (String line : lines) {
            String[] params = line.split(Conf.SPLIT);
            linkedInstanceIDs.add(params[0]);
            linkedInstanceIDs.add(params[1]);
        }
        System.out.println("id数量: " + linkedInstanceIDs.size());

        lines = readUtil.readByLine();
        System.out.println(lines.size());
        int num = 0;
        for (String line : lines) {
            Triple triple = TripleUtil.parseLineId(line);
            if (linkedInstanceIDs.contains(triple.getS())) {
                writeUtil.write(triple.getS() + Conf.SPLIT + triple.getP() + Conf.SPLIT + triple.getO());
            } else {
                num++;
            }
        }
        writeUtil.close();
        System.out.println(num);
    }
}
