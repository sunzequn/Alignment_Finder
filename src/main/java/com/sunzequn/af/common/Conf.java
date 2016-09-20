package com.sunzequn.af.common;

/**
 * Created by sloriac on 16-9-5.
 */
public class Conf {

    public static final String SPLIT = "\t";

    // DBpedia官方提供的到GeoNames的链接实例数据文件
    public static final String SAME_AS_FILE = "/home/sloriac/code/Alignment_Finder/data/origin/geonames_links_en.ttl";
    public static final String VIRTUOSO_CONF = "/home/sloriac/code/Alignment_Finder/src/main/resources/conf/virtuoso.properties";

    public static final String SAME_AS_FILE_BY_ID = "/home/sloriac/code/Alignment_Finder/data/id/geonames_links_by_id";

    //DBpedia和GeoNames的三元组文件
    public static final String DBPEDIA_CORE_TRIPLES = "/home/sloriac/code/Alignment_Finder/data/origin/dbpedia_triples_core";
    public static final String GEONAMES_CORE_TRIPLES = "/home/sloriac/code/Alignment_Finder/data/origin/geonames_triples_core";

    public static final String DBPEDIA_CORE_TRIPLES_BY_ID = "/home/sloriac/code/Alignment_Finder/data/id/dbpedia_triples_core_by_id";
    public static final String GEONAMES_CORE_TRIPLES_BY_ID = "/home/sloriac/code/Alignment_Finder/data/id/geonames_triples_core_by_id";

    //DBpedia和GeoNames的id文件
    public static final String GEONAMES_ID_URI = "/home/sloriac/code/Alignment_Finder/data/id/geonames_id_uri";
    public static final String GEONAMES_ID_PROP = "/home/sloriac/code/Alignment_Finder/data/id/geonames_id_prop";
    public static final String GEONAMES_ID_LITERAL = "/home/sloriac/code/Alignment_Finder/data/id/geonames_id_literal";

    public static final String DBPEDIA_ID_URI = "/home/sloriac/code/Alignment_Finder/data/id/dbpedia_id_uri";
    public static final String DBPEDIA_ID_PROP = "/home/sloriac/code/Alignment_Finder/data/id/dbpedia_id_prop";
    public static final String DBPEDIA_ID_LITERAL = "/home/sloriac/code/Alignment_Finder/data/id/dbpedia_id_literal";

    public static final String DBPEDIA_ONTOLOGY = "/home/sloriac/code/Alignment_Finder/data/origin/dbpedia_2015-10.nt";

    //frequency
    public static final String DBPEDIA_FREQUENCY_PROP = "/home/sloriac/code/Alignment_Finder/data/frequency/dbpedia_frequency_prop";
    public static final String GEONAMES_FREQUENCY_PROP = "/home/sloriac/code/Alignment_Finder/data/frequency/geonames_frequency_prop";
    public static final String DBPEDIA_FREQUENCY_PROP_VALUE = "/home/sloriac/code/Alignment_Finder/data/frequency/dbpedia_frequency_prop_value";
    public static final String GEONAMES_FREQUENCY_PROP_VALUE = "/home/sloriac/code/Alignment_Finder/data/frequency/geonames_frequency_prop_value";

    //frequency
    public static final String DBPEDIA_PROP_RANGE = "/home/sloriac/code/Alignment_Finder/data/frequency/dbpedia_prop_range";
    public static final String GEONAMES_PROP_RANGE = "/home/sloriac/code/Alignment_Finder/data/frequency/geonames_prop_range";
    public static final String DBPEDIA_PROP_SCORE = "/home/sloriac/code/Alignment_Finder/data/frequency/dbpedia_prop_score";
    public static final String GEONAMES_PROP_SCORE = "/home/sloriac/code/Alignment_Finder/data/frequency/geonames_prop_score";

    //id前缀
    public static final String DBPEDIA_ID_PREFIX_URI = "D.U.";
    public static final String DBPEDIA_ID_PREFIX_PROP = "D.P.";
    public static final String DBPEDIA_ID_PREFIX_LITERAL = "D.L.";

    public static final String GEONAMES_ID_PREFIX_URI = "G.U.";
    public static final String GEONAMES_ID_PREFIX_PROP = "G.P.";
    public static final String GEONAMES_ID_PREFIX_LITERAL = "G.L.";

    public static final String DBPEDIA_TRIPLES_TABLE = "d_triples";
    public static final String GEONAMES_TRIPLES_TABLE = "g_triples";

    public static final String DBPEDIA_FREQUENCY_TABLE = "d_frequency";
    public static final String GEONAMES_FREQUENCY_TABLE = "g_frequency";

}
