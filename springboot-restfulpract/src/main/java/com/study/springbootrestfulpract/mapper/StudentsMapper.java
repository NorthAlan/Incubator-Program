package com.study.springbootrestfulpract.mapper;

import com.study.springbootrestfulpract.domain.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentsMapper {
    @Select("select * from students where id=#{id}")
    public Student getStudentById(int id);
    @Select("select * from students")
    public List<Student> getAllStudents();
    @Insert("insert into students values(null,#{name},#{age})")
    public int insertStudent(Student student);
    @Update("update students set name=#{name},age=#{age} where id=#{id}")
    int updateStudent(Student student);
    @Delete("delete from students where id=#{id}")
    int deleteStudent(int id);


}
