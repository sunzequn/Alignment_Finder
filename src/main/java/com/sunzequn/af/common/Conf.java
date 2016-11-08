package com.sunzequn.af.common;

/**
 * Created by sloriac on 16-9-5.
 */
public class Conf {

    public static final String SPLIT = "\t";

    // DBpedia官方提供的到GeoNames的链接实例数据文件
    public static final String DBPEDIA_GEONAMES_LINKS = "/home/sloriac/code/Alignment_Finder/src/main/resources/data/geonames_links_en.ttl";
    public static final String DBPEDIA_LINKED_TRIPLES = "/home/sloriac/code/Alignment_Finder/src/main/resources/data/dbpedia_linked_triples";
    public static final String GEONAMES_LINKED_TRIPLES = "/home/sloriac/code/Alignment_Finder/src/main/resources/data/geonames_linked_triples";

    public static final String DBPEDIA_O_IS_LINKED = "/home/sloriac/code/Alignment_Finder/src/main/resources/data/dbpedia_o_linked_triples";
    public static final String GEONAMES_O_IS_LINKED = "/home/sloriac/code/Alignment_Finder/src/main/resources/data/geonames_o_linked_triples";

    public static final String VIRTUOSO_CONF = "Alignment_Finder/src/main/resources/conf/virtuoso.properties";
    public static final String VIRTUOSO_DBPEDIA = "http://localhost:8891/sparql?default-graph-uri=&query=";
    public static final String VIRTUOSO_DBPEDIA_CORE_GRAPH = "http://dbpedia.org";
    public static final String VIRTUOSO_GEONAMES = "http://localhost:8890/sparql?default-graph-uri=&query=";
    public static final String VIRTUOSO_SUFFIX = "&format=xml%2Fhtml&timeout=0&debug=on";

}
