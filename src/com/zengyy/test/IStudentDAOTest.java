package com.zengyy.test;

import com.zengyy.dao.IStudentDAO;
import com.zengyy.dao.impl.IStudentDAOImpl;
import com.zengyy.domain.Student;
import org.junit.Test;

import javax.lang.model.SourceVersion;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author zengyanyu
 */
public class IStudentDAOTest {

    private IStudentDAO dao = new IStudentDAOImpl();

    @Test
    public void save() {
        Student student = new Student();
        student.setName("Will");
        student.setAge(17);
        dao.save(student);
    }

    @Test
    public void delete() {
        dao.delete(2L);
    }

    @Test
    public void update() {
        Student student = new Student();
        student.setName("Stef");
        student.setAge(18);
        dao.update(3L, student);
    }

    @Test
    public void get() {
        Student student = dao.get(1L);
        System.out.println("student = " + student);
    }

    @Test
    public void listAll() {
        List<Student> students = dao.listAll();
        for (Student student : students) {
            System.out.println("student = " + student);
        }
    }
}