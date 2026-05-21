package com.zengyy.dao.impl;

import com.zengyy.dao.IStudentDAO;
import com.zengyy.domain.Student;
import com.zengyy.handler.BeanHandler;
import com.zengyy.handler.BeanListHandler;
import com.zengyy.util.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zengyanyu
 */
public class StudentDAOImpl implements IStudentDAO {

    @Override
    public void save(Student student) {
        JdbcTemplate.update("INSERT INTO student(name,age) VALUES(?,?)", student.getName(), student.getAge());
    }

    /**
     * 批量保存
     */
    @Override
    public void batchSave() {
        // 1. 准备SQL
        String sql = "INSERT INTO student(name, age) VALUES(?, ?)";

        // 2. 准备批量参数（每一行是一个对象数组）
        List<Object[]> paramList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            // 每一组参数就是一个 Object[]
            Object[] row = {"李四", 21};
            paramList.add(row);
        }

        // 3. 执行批量保存
        int[] rows = JdbcTemplate.batchUpdate(sql, paramList.toArray(new Object[0][]));
    }

    @Override
    public void delete(Long id) {
        JdbcTemplate.update("delete from student where id = ?", id);
    }

    @Override
    public void update(Long id, Student student) {
        JdbcTemplate.update("update student set name = ?,age=? where id = ?",
                student.getName(), student.getAge(), id);
    }

    @Override
    public Student get(Long id) {
        String sql = "SELECT * FROM student WHERE id = ?";
        return JdbcTemplate.query(sql, new BeanHandler<>(Student.class), id);
    }

    @Override
    public List<Student> listAll() {
        String sql = "SELECT * FROM student";
        return JdbcTemplate.query(sql, new BeanListHandler<>(Student.class));
    }

    @Override
    public Long total() {
        String sql = "SELECT COUNT(id) AS id FROM student";
        return JdbcTemplate.query(sql, rs -> {
            if (rs.next()) {
                return rs.getLong(1);
//                    return rs.getLong("id");
            }
            return 0L;
        });
    }

}
