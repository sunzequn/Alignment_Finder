package com.sunzequn.af.prepare;

import com.sunzequn.af.common.Conf;
import com.sunzequn.af.db.Triple;
import com.sunzequn.af.utils.SerializableUtil;
import com.sunzequn.af.utils.TimeUtil;
import com.sunzequn.af.utils.WriteUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by sloriac on 16-9-5.
 * <p>
 * 给抽取得到的三元组数据，生成id存储在文件里面
 */
public class GenerateId {

    private static final int BATCH_SIZE = 50000;

    public static void main(String[] args) throws Exception {
//        handleFiles(Conf.GEONAMES_CORE_TRIPLES, Conf.GEONAMES_ID_URI, Conf.GEONAMES_ID_PROP, Conf.GEONAMES_ID_LITERAL, Conf.GEONAMES_ID_PREFIX_URI, Conf.GEONAMES_ID_PREFIX_PROP, Conf.GEONAMES_ID_PREFIX_LITERAL);
        handleFiles(Conf.DBPEDIA_CORE_TRIPLES, Conf.DBPEDIA_ID_URI, Conf.DBPEDIA_ID_PROP, Conf.DBPEDIA_ID_LITERAL, Conf.DBPEDIA_ID_PREFIX_URI, Conf.DBPEDIA_ID_PREFIX_PROP, Conf.DBPEDIA_ID_PREFIX_LITERAL);
    }

    private static void handleFiles(String file, String fid, String fprop, String fliteral, String uriPrefix, String propPrefix, String literalPrefix) throws Exception {
        Set<String> uris = new HashSet<>();
        Set<String> props = new HashSet<>();
        Set<String> literals = new HashSet<>();
        File triplesFile = new File(file);
        LineIterator it = FileUtils.lineIterator(triplesFile, "UTF-8");

        TimeUtil.start();
        while (it.hasNext()) {
            String line = it.nextLine();
            Triple t = (Triple) SerializableUtil.str2Object(line);
            uris.add(t.getS());
            props.add(t.getP());
            if (t.getO().startsWith("http://")) {
                uris.add(t.getO());
            } else {
                literals.add(t.getO());
            }
        }
        LineIterator.closeQuietly(it);
        TimeUtil.print();

        System.out.println("uri数量 " + uris.size());
        System.out.println("prop数量 " + props.size());
        System.out.println("literal数量 " + literals.size());

        // ---------------- //

        TimeUtil.start();
        //处理uri的id
        toFile(uris, fid, uriPrefix, Conf.SPLIT, BATCH_SIZE);

        //处理prop的id
        toFile(props, fprop, propPrefix, Conf.SPLIT, BATCH_SIZE);

        //处理literal的id
        toFile(literals, fliteral, literalPrefix, Conf.SPLIT, BATCH_SIZE);

        TimeUtil.print();
    }

    private static void toFile(Set<String> datas, String file, String prefix, String split, int batchSize) throws Exception {
        WriteUtil writeUtil = new WriteUtil(file, false);
        int num = 0;
        for (String data : datas) {
            writeUtil.write(prefix + num + split + SerializableUtil.object2Str(data));
            num++;
            if (num % batchSize == 0) {
                writeUtil.flush();
            }
        }
        writeUtil.close();
    }
}
