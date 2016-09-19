package com.sunzequn.af.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by sloriac on 16-9-6.
 */
public class BaseDao {


    private static final String CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String JDBC_URL_PREFIX = "jdbc:mysql://localhost:3306/alignment_finder";
    private static final String JDBC_URL_SUFFIX = "?useUnicode=true";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    protected Connection getConnection() {
        try {
            Class.forName(CLASS_NAME);
            return DriverManager.getConnection(JDBC_URL_PREFIX + JDBC_URL_SUFFIX, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected <T> List<T> query(Connection connection, String sql, Object[] params, Class clazz) {
        QueryRunner queryRunner = new QueryRunner();
        try {
            List<T> ts = queryRunner.query(connection, sql, new BeanListHandler<T>(clazz), params);
            if (ts != null && ts.size() > 0) {
                return ts;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected int execute(Connection connection, String sql, Object[] params) {
        QueryRunner queryRunner = new QueryRunner();
        try {
            int res = queryRunner.update(connection, sql, params);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    protected int[] batch(Connection connection, String sql, Object[][] params) {
        try {
            connection.setAutoCommit(false);
            QueryRunner queryRunner = new QueryRunner();
            int[] res = queryRunner.batch(connection, sql, params);
            connection.commit();
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
