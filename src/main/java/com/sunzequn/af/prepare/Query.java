package com.sunzequn.af.prepare;

import com.sunzequn.af.utils.PropertiesUtil;
import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFactory;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by sloriac on 16-9-1.
 */
public class Query {

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
