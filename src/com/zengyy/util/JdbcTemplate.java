package com.zengyy.util;

import com.zengyy.handler.IResultSetHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * JDBC操作的模板类
 *
 * @author zengyanyu
 */
public class JdbcTemplate {

    /**
     * DML(增删改)的模板
     *
     * @param sql    DML操作的SQL模板(带有占位符参数?)
     * @param params SQL模板中?对应的参数值
     * @return 受影响的行数
     */
    public static int update(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConn();
            ps = conn.prepareStatement(sql);
            // 设置占位符参数
            for (int index = 0; index < params.length; index++) {
                ps.setObject(index + 1, params[index]);
            }
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, ps, null);
        }
        return 0;
    }

    /**
     * DQL(查询)的模板
     *
     * @param sql    DQL操作的SQL模板
     * @param rsh    结果集处理器
     * @param params SQL模板中?对应的参数值
     * @return
     */
    public static <T> T query(String sql, IResultSetHandler<T> rsh, Object... params) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConn();
            ps = conn.prepareStatement(sql);
            // 设置占位符参数
            for (int index = 0; index < params.length; index++) {
                ps.setObject(index + 1, params[index]);
            }
            rs = ps.executeQuery();
            return rsh.handle(rs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, ps, rs);
        }
        return null;
    }
}
