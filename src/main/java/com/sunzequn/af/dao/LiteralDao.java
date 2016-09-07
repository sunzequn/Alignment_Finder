package com.sunzequn.af.dao;

import com.sunzequn.af.bean.Literal;
import com.sunzequn.af.bean.Uri;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sloriac on 16-9-6.
 */
public class LiteralDao extends BaseDao {

    private String table;
    private Connection connection;

    public LiteralDao(String table) {
        connection = getConnection();
        this.table = table;
    }

    public int[] addBatch(List<Literal> literals) {
        String sql = "insert into " + table + " (literal) values (?)";
        Object[][] parmas = new Object[literals.size()][1];
        for (int i = 0; i < literals.size(); i++) {
            parmas[i][0] = literals.get(i).getLiteral();
        }
        return batch(connection, sql, parmas);
    }

}
