package com.sunzequn.af.db;

import java.sql.Connection;
import java.util.List;

/**
 * Created by sloriac on 16-9-20.
 */
public class IdDao extends BaseDao {
    private String table;
    private Connection connection;

    public IdDao(String table) {
        this.table = table;
        connection = getConnection();
    }

    public int[] addBatch(List<Id> ids) {
        String sql = "insert into " + table + " (id, content) values (?, ?)";
        Object[][] params = new Object[ids.size()][2];
        for (int i = 0; i < ids.size(); i++) {
            params[i][0] = ids.get(i).getId();
            params[i][1] = ids.get(i).getContent();
        }
        return batch(connection, sql, params);
    }

}
