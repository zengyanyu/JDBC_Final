package com.zengyy.dao.impl;

import com.zengyy.dao.IStudentDAO;
import com.zengyy.domain.Student;
import com.zengyy.handler.BeanHandler;
import com.zengyy.handler.BeanListHandler;
import com.zengyy.util.JdbcTemplate;

import java.util.List;

/**
 * @author zengyanyu
 */
public class StudentDAOImpl implements IStudentDAO {

    @Override
    public void save(Student student) {
        JdbcTemplate.update("INSERT INTO student(name,age) VALUES(?,?)", student.getName(), student.getAge());
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

}
