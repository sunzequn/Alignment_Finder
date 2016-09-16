package com.sunzequn.af.prepare;

import com.sunzequn.af.utils.ReadUtil;
import com.sunzequn.af.utils.SerializableUtil;
import com.sunzequn.af.utils.TimeUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sloriac on 16-9-16.
 * <p>
 * id和uri等的对应集合
 */
public class IDSET {

    public static Map<String, String> geonamesIdUri;
    public static Map<String, String> geonamesIdProp;
    public static Map<String, String> geonamesIdLiteral;


    public static Map<String, String> dbpediaIdUri;
    public static Map<String, String> dbpediaIdProp;
    public static Map<String, String> dbpediaIdLiteral;

    public static void load() throws Exception {

        geonamesIdUri = loadId(CONF.GEONAMES_ID_URI);
        geonamesIdProp = loadId(CONF.GEONAMES_ID_PROP);
        geonamesIdLiteral = loadId(CONF.GEONAMES_ID_LITERAL);

        dbpediaIdUri = loadId(CONF.DBPEDIA_ID_URI);
        dbpediaIdProp = loadId(CONF.DBPEDIA_ID_PROP);
        dbpediaIdLiteral = loadId(CONF.DBPEDIA_ID_LITERAL);

    }

    private static Map<String, String> loadId(String file) throws Exception {
        TimeUtil.start();
        Map<String, String> ids = new HashMap<>();
        ReadUtil readUtil = new ReadUtil(file);
        List<String> lines = readUtil.readByLine();
        for (String line : lines) {
            String[] params = line.split(CONF.SPLIT);
            ids.put((String) SerializableUtil.str2Object(params[1]), params[0]);
        }
        System.out.println(ids.size());
        System.out.print("加载 " + file + " 的id ");
        TimeUtil.print();
        return ids;
    }
}
