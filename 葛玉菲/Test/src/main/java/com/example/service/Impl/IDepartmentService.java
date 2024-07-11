package com.example.service.Impl;

import com.example.pojo.Department;
import com.example.pojo.Student;

import java.util.List;

public interface IDepartmentService {
    // 添加
    String add(Department department);

    // 修改
    String update(Department department);

    // 删除
    String delete(Integer id);

    // 查询
    //通过主键查询
    Department getById(Integer id);

    //查询所有
    List<Department> getAll();
}
