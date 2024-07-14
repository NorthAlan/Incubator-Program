package com.study.springbootrestfulpract;

import com.study.springbootrestfulpract.domain.Student;
import com.study.springbootrestfulpract.mapper.StudentsMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.PrivateKey;

@SpringBootTest
public class StudentsTest {
    @Resource
    private StudentsMapper studentsMapper;

    @Test
    public void test() {
        System.out.println(studentsMapper.getStudentById(1));
    }

    @Test
    public void test1() {
        System.out.println(studentsMapper.getAllStudents());

    }

    @Test
    public void test2() {
        Student student1 = new Student();
        student1.setName("ajxk");
        student1.setAge(18);

        System.out.println(studentsMapper.insertStudent(student1));

    }

    @Test
    public void test3() {
        Student student2 = new Student();
        student2.setId(3);
        student2.setName("apdja");
        student2.setAge(20);

        System.out.println(studentsMapper.insertStudent(student2));
    }
    @Test
    public void test4() {
        System.out.println(studentsMapper.deleteStudent(12));
    }
}