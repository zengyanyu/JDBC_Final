package com.zengyy.handler;

import java.sql.ResultSet;
import java.util.List;

/**
 * @author zengyanyu
 */
public interface IResultSetHandler<T> {

    /**
     * 处理结果集
     *
     * @param rs
     * @return
     * @throws Exception
     */
    T handle(ResultSet rs) throws Exception;
}
