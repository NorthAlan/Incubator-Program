package com.example.controller;

import com.example.pojo.Student;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping   // post +  /student
    public String add(@RequestBody Student student){
        return studentService.add(student);
    }

    @PutMapping  // put +  /student
    public String update(@RequestBody Student student){
        return studentService.update(student);
    }

    @DeleteMapping("/{id}")   // delete + /emp/1115
    public String del(@PathVariable("id") Integer id){
        return studentService.delete(id);
    }

    @GetMapping("/{id}")      //  get + /emp/1115
    public Student getById(@PathVariable("id") Integer id){
        return studentService.getStudentById(id);
    }

    @GetMapping("/all")    //   get  +  /emp/all
    public List<Student> getAll(){
        return studentService.getAll();
    }





}

/*
* {
    "id" : 1,
    "name": "张三",
    "email" : "1111@qq.com",
    "gender" : 1,
    "departmentId" : 1,
    "birth" : "2000-01-01"
}
* */
