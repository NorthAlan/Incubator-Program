package com.study.springbootrestfulpract.controller;

import com.study.springbootrestfulpract.domain.Student;
import com.study.springbootrestfulpract.mapper.StudentsMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentsController {

    @Autowired
    private StudentsMapper studentsMapper;

    // 获取单个学生信息
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        return studentsMapper.getStudentById(id);
    }

    // 获取所有学生信息
    @GetMapping
    public List<Student> getAllStudents() {
        return studentsMapper.getAllStudents();
    }

    // 创建新的学生记录
    @PostMapping
    public int insertStudent(@RequestBody Student student) {
        return studentsMapper.insertStudent(student);
    }

    // 更新学生信息
    @PutMapping("/{id}")
    public int updateStudent(@PathVariable int id, @RequestBody Student student) {
        student.setId(id); // 确保更新操作使用正确的ID
        return studentsMapper.updateStudent(student);
    }

    // 删除学生记录
    @DeleteMapping("/{id}")
    public int deleteStudent(@PathVariable int id) {
        return studentsMapper.deleteStudent(id);
    }
}