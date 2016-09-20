package com.sunzequn.af.db;

import com.sunzequn.af.prepare.Triple;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by sloriac on 16-9-18.
 */
public class TripleDao extends BaseDao {

    private String table;
    private Connection connection;

    public TripleDao(String table) {
        this.table = table;
        connection = getConnection();
    }

    public int[] addBatch(List<Triple> triples) {
        String sql = "insert into " + table + " (s, p, o) values (?, ?, ?)";
        Object[][] params = new Object[triples.size()][3];
        for (int i = 0; i < triples.size(); i++) {
            params[i][0] = triples.get(i).getS();
            params[i][1] = triples.get(i).getP();
            params[i][2] = triples.get(i).getO();
        }
        return batch(connection, sql, params);
    }

    public List<Triple> getByS(String s) {
        String sql = "select * from " + table + " where s = ?";
        Object[] params = {s};
        return query(connection, sql, params, Triple.class);
    }

    public List<Triple> getByP(String p) {
        String sql = "select * from " + table + " where p = ?";
        Object[] params = {p};
        return query(connection, sql, params, Triple.class);
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
