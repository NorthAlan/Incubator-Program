package com.example.service;

import com.example.mapper.StudentMapper;
import com.example.pojo.Student;
import com.example.service.Impl.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService implements IStudentService {

    @Autowired
    StudentMapper studentMapper;

    @Transactional
    public String add(Student student) {
        if(student==null || student.getName()==null || "".equals(student.getName())) {
            return "fail";
        }
        int add = studentMapper.add(student);
        return add>0 ? "success" : "fail";
    }

    @Transactional
    public String update(Student student) {
              /*
        * if (emp==null || emp.getEmpno()==null || emp.getEname()==null || "".equals(emp.getEname())){
            return "fail";
        }
        int update = empMapper.update(emp);
        return update>0?"success":"fail";


        * */
        if(student==null || student.getName()==null || "".equals(student.getName())) {
            return "fail";
        }
        int update = studentMapper.update(student);
        return update>0 ? "success" : "fail";
    }

    @Transactional
    public String delete(Integer id) {
        if (id==null){
            return "fail";
        }
        int del = studentMapper.delete(id);
        return del>0 ? "success" : "fail";

    }

    public Student getStudentById(Integer id) {
        if (id==null){
            return null;
        }
        return studentMapper.getById(id);

    }

    public List<Student> getAll() {
        return studentMapper.getAll();
    }
}
