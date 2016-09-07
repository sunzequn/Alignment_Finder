package com.sunzequn.af.dao;

import com.sunzequn.af.bean.Prop;
import com.sunzequn.af.bean.Uri;

import java.sql.Connection;
import java.util.List;

/**
 * Created by sloriac on 16-9-6.
 */
public class PropDao extends BaseDao {

    private String table;
    private Connection connection;

    public PropDao(String table) {
        connection = getConnection();
        this.table = table;
    }

    public int[] addBatch(List<Prop> props) {
        String sql = "insert into " + table + " (prop) values (?)";
        Object[][] parmas = new Object[props.size()][1];
        for (int i = 0; i < props.size(); i++) {
            parmas[i][0] = props.get(i).getProp();
        }
        return batch(connection, sql, parmas);
    }

    public List<Prop> getAll() {
        String sql = "select * from " + table;
        return query(connection, sql, null, Prop.class);
    }
}
