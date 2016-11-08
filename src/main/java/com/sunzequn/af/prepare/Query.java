package com.sunzequn.af.prepare;

import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.riot.thrift.TRDF;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

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

    public List<Triple> queryAllTriplesByS(String baseUrl, String urlSuffix, String graph, String sUri, int timeout) {
        String sql = null;
        if (graph == null) {
            sql = "select * where {<" + sUri + "> ?p ?o}";
        } else {
            sql = "select * from <" + graph + "> where {<" + sUri + "> ?p ?o}";
        }
        ResultSet rs = null;
        try {
            rs = run(baseUrl, urlSuffix, sql, timeout);
        } catch (Exception e) {
            return null;
        }
        if (rs != null) {
            List<Triple> triples = new ArrayList<>();
            while (rs.hasNext()) {
                QuerySolution result = rs.nextSolution();
                RDFNode p = result.get("p");
                RDFNode o = result.get("o");
                Triple triple = new Triple(sUri, p.toString(), o.toString());
//                System.out.println(triple);
                triples.add(triple);
            }
            return triples.size() > 0 ? triples : null;
        }
        return null;
    }


}
