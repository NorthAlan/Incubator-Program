package com.example.mapper;
import com.example.pojo.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentMapper {

    //添加
    int add(Department department);

    //删除
    int del(Integer id);

    //通过主键查询
    Department getById(Integer id);

    //查询所有
    List<Department> getAll();
}
