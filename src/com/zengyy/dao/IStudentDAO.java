package com.zengyy.dao;

import com.zengyy.domain.Student;

import java.util.List;

/**
 * @author zengyanyu
 */
public interface IStudentDAO {

    /**
     * 保存
     *
     * @param student
     */
    void save(Student student);

    /**
     * 批量保存
     */
    void batchSave();

    /**
     * 删除
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 修改
     *
     * @param id
     * @param student
     */
    void update(Long id, Student student);

    /**
     * 查询
     *
     * @param id
     * @return
     */
    Student get(Long id);

    /**
     * 查询
     *
     * @return
     */
    List<Student> listAll();

    /**
     * 查询总条数
     * @return
     */
    Long total();
}
