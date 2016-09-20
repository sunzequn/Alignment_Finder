package com.sunzequn.af.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by sloriac on 16-9-19.
 */
public class FrequencyDao extends BaseDao {

    private String table;
    private Connection connection;

    public FrequencyDao(String table) {
        this.table = table;
        connection = getConnection();
    }

    public int[] addBatch(List<Frequency> frequencies) {
        String sql = "insert into " + table + " (prop, value, frequency) values (?, ?, ?)";
        Object[][] params = new Object[frequencies.size()][3];
        for (int i = 0; i < frequencies.size(); i++) {
            params[i][0] = frequencies.get(i).getProp();
            params[i][1] = frequencies.get(i).getValue();
            params[i][2] = frequencies.get(i).getFrequency();
        }
        return batch(connection, sql, params);
    }

    public List<Frequency> getByProp(String prop) {
        String sql = "select * from " + table + " where prop = ?";
        Object[] params = {prop};
        return query(connection, sql, params, Frequency.class);
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
