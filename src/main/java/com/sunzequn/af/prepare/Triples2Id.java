package com.sunzequn.af.prepare;

import com.sunzequn.af.bean.Literal;
import com.sunzequn.af.bean.Prop;
import com.sunzequn.af.bean.Uri;
import com.sunzequn.af.dao.LiteralDao;
import com.sunzequn.af.dao.PropDao;
import com.sunzequn.af.dao.UriDao;
import com.sunzequn.af.utils.SerializableUtil;
import com.sunzequn.af.utils.TimeUtil;
import com.sunzequn.af.utils.WriteUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sloriac on 16-9-7.
 *
 * 将三元组转化为id表示
 */
public class Triples2Id {

    public static void main(String[] args) throws Exception {
//        handleTriples(CONF.GEONAMES_CORE_TRIPLES, CONF.GEONAMES_CORE_TRIPLES_BY_ID, CONF.GEONAMES_URI_ID, CONF.GEONAMES_PROP_ID, CONF.GEONAMES_LITERAL_ID);
        handleTriples(CONF.DBPEDIA_CORE_TRIPLES, CONF.DBPEDIA_CORE_TRIPLES_BY_ID, CONF.DBPEDIA_URI_ID, CONF.DBPEDIA_PROP_ID, CONF.DBPEDIA_LITERAL_ID);
    }

    private static void handleTriples(String sourceFile, String targetFile, String tid, String tprop, String tliteral) throws Exception {
        Map<String, Long> uriIds = new HashMap<>();
        Map<String, Long> propIds = new HashMap<>();
        Map<String, Long> literalIds = new HashMap<>();
        UriDao uriDao = new UriDao(tid);
        PropDao propDao = new PropDao(tprop);
        LiteralDao literalDao = new LiteralDao(tliteral);
        //加载id数据
        TimeUtil.start();
        List<Uri> uris = uriDao.getAll();
        List<Prop> props = propDao.getAll();
        List<Literal> literals = literalDao.getAll();
        TimeUtil.print();

        for (Uri uri : uris) {
            uriIds.put(uri.getUri(), uri.getId());
        }
        for (Prop prop : props) {
            propIds.put(prop.getProp(), prop.getId());
        }
        for (Literal literal : literals) {
            literalIds.put(literal.getLiteral(), literal.getId());
        }

        LineIterator it = FileUtils.lineIterator(new File(sourceFile), "utf-8");
        WriteUtil writeUtil = new WriteUtil(targetFile, false);
        long index = 0;
        while (it.hasNext()) {
            try {
                String line = it.nextLine();
                Triple t = (Triple) SerializableUtil.str2Object(line);
                long sid = uriIds.get(t.getS());
                long pid = propIds.get(t.getP());
                long oid;
                if (t.getO().startsWith("http://")) {
                    oid = uriIds.get(t.getO());
                } else {
                    oid = literalIds.get(t.getO());
                }
                writeUtil.write(sid + "\t" + pid + "\t" + oid);
                index++;
                if (index % 10000 == 0) {
                    System.out.println(index);
                    writeUtil.flush();
                }
            } catch (Exception e) {
                System.out.println("id出错");
            }
        }
        writeUtil.close();
        LineIterator.closeQuietly(it);
        System.out.println("三元组数量： " + index);

    }


}
