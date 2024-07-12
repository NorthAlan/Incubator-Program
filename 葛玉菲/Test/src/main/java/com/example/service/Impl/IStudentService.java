package com.example.service.Impl;

import com.example.config.PageRequest;
import com.example.config.PageResult;
import com.example.pojo.Student;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IStudentService {

    // 添加
    String add(Student student);

    // 修改
    String update(Student student);

    // 删除
    String delete(Integer id);

    // 查询
    //通过主键查询
    Student getStudentById(Integer id);

    //查询所有
    List<Student> getAll();

    // 分页查询
    //List<Student> getByPage(int PageNum, int PageSize);
    PageInfo<Student> getByPage(int PageNum, int PageSize);

    PageResult findPage(PageRequest pageRequest);
}
