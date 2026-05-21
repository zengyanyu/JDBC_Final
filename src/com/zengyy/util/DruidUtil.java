package com.zengyy.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Properties;

/**
 * @author zengyanyu
 */
public class DruidUtil {

    private static DataSource ds = null;

    static {
        try {
            Properties properties = new Properties();
            properties.load(Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("db.properties"));
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接对象
     *
     * @return
     */
    public static Connection getConn() {
        try {
            System.out.println("ds = " + ds);
            return ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Connection conn = getConn();
        System.out.println("conn = " + conn);
    }
}
