package com.sunzequn.af.prepare;

import com.sunzequn.af.bean.Literal;
import com.sunzequn.af.bean.Prop;
import com.sunzequn.af.bean.Uri;
import com.sunzequn.af.dao.LiteralDao;
import com.sunzequn.af.dao.PropDao;
import com.sunzequn.af.dao.UriDao;
import com.sunzequn.af.utils.SerializableUtil;
import com.sunzequn.af.utils.TimeUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by sloriac on 16-9-5.
 * <p>
 * 将抽取得到的三元组数据，写入mysql，给一个id编号，节省空间
 */
public class GenerateId {

    private static final int BATCH_SIZE = 50000;

    public static void main(String[] args) throws Exception {
//        handleFiles(CONF.GEONAMES_CORE_TRIPLES, CONF.GEONAMES_URI_ID, CONF.GEONAMES_PROP_ID, CONF.GEONAMES_LITERAL_ID);
        handleFiles(CONF.DBPEDIA_CORE_TRIPLES, CONF.DBPEDIA_URI_ID, CONF.DBPEDIA_PROP_ID, CONF.DBPEDIA_LITERAL_ID);
    }

    private static void handleFiles(String file, String tid, String tprop, String tliteral) throws Exception {
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

        System.out.println(uris.size());
        System.out.println(props.size());
        System.out.println(literals.size());

        // ---------------- //

        TimeUtil.start();
        //处理uri的id
        UriDao uriDao = new UriDao(tid);
        List<Uri> uriList = new ArrayList<>();
        for (String uri : uris) {
            uriList.add(new Uri(uri));
            if (uriList.size() == BATCH_SIZE) {
                System.out.println("uri: " + uriList.size());
                uriDao.addBatch(uriList);
                uriList = new ArrayList<>();
            }
        }
        if (uriList.size() > 0) {
            System.out.println("uri: " + uriList.size());
            uriDao.addBatch(uriList);
        }
        //处理prop的id
        PropDao propDao = new PropDao(tprop);
        List<Prop> propList = new ArrayList<>();
        for (String prop : props) {
            propList.add(new Prop(prop));
            if (propList.size() == BATCH_SIZE) {
                System.out.println("prop: " + propList.size());
                propDao.addBatch(propList);
                propList = new ArrayList<>();
            }
        }
        if (propList.size() > 0) {
            System.out.println("prop: " + propList.size());
            propDao.addBatch(propList);
        }
        //处理literal的id
        LiteralDao literalDao = new LiteralDao(tliteral);
        List<Literal> literalList = new ArrayList<>();
        for (String literal : literals) {
            literalList.add(new Literal(literal));
            if (literalList.size() == BATCH_SIZE) {
                System.out.println("literal: " + literalList.size());
                literalDao.addBatch(literalList);
                literalList = new ArrayList<>();
            }
        }
        if (literalList.size() > 0) {
            System.out.println("literal: " + literalList.size());
            literalDao.addBatch(literalList);
        }

        TimeUtil.print();
    }

}
