package com.sunzequn.af.dao;

import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFactory;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by sloriac on 16-9-1.
 */
public class Query {

    /**
     * 通过HTTP去查询
     *
     * @param baseUrl
     * @param urlSuffix
     * @param sparql
     * @param timeout
     * @return
     * @throws Exception
     */
    public ResultSet run(String baseUrl, String urlSuffix, String sparql, int timeout) throws Exception {
        ParameterizedSparqlString pss = new ParameterizedSparqlString(sparql);
        URL url = new URL(baseUrl + URLEncoder.encode(pss.toString(), "UTF-8") + urlSuffix);
        URLConnection connAPI = url.openConnection();
        connAPI.setConnectTimeout(timeout);
        connAPI.connect();
        ResultSet rs = ResultSetFactory.fromXML(connAPI.getInputStream());
        return rs.hasNext() ? rs : null;
    }


}
