package com.sunzequn.af.dao;

import com.sunzequn.af.bean.Uri;
import com.sunzequn.af.prepare.CONF;

import java.sql.Connection;
import java.util.List;

/**
 * Created by sloriac on 16-9-6.
 */
public class UriDao extends BaseDao {

    private String table;
    private Connection connection;

    public UriDao(String table) {
        connection = getConnection();
        this.table = table;
    }

    public int[] addBatch(List<Uri> uris) {
        String sql = "insert into " + table + " (uri) values (?)";
        Object[][] parmas = new Object[uris.size()][1];
        for (int i = 0; i < uris.size(); i++) {
            parmas[i][0] = uris.get(i).getUri();
        }
        return batch(connection, sql, parmas);
    }

    public List<Uri> getAll() {
        String sql = "select * from " + table;
        return query(connection, sql, null, Uri.class);
    }

}
