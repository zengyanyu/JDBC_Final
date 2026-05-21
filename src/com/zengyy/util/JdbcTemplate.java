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
     * 批量DML操作（批量插入、批量更新、批量删除）
     *
     * @param sql    带占位符的SQL语句（例如 INSERT INTO t_user(name,age) VALUES(?,?)）
     * @param params 批量参数列表：每一个 Object[] 是一组占位符参数
     * @return 每条SQL受影响的行数数组
     */
    public static int[] batchUpdate(String sql, Object[]... params) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConn();
            // 关闭自动提交，批量操作必须手动控制事务
            conn.setAutoCommit(false);

            ps = conn.prepareStatement(sql);

            // 遍历每一组参数，添加到批处理
            for (Object[] param : params) {
                // 给占位符赋值
                for (int i = 0; i < param.length; i++) {
                    ps.setObject(i + 1, param[i]);
                }
                // 添加到批处理任务
                ps.addBatch();
            }

            // 执行批量操作
            int[] rows = ps.executeBatch();
            // 提交事务
            conn.commit();
            return rows;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, ps, null);
        }
        return new int[0];
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
            conn = DruidUtil.getConn();
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
