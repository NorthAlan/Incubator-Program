package com.example.mapper;

import com.example.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {
    //添加
    int add(Student student);

    //修改
    int update(Student student);

    //删除
    int delete(Integer id);

    //通过主键查询
    Student getById(Integer id);

    //查询所有
    //@Select("select * from student")
    List<Student> getAll();
}
