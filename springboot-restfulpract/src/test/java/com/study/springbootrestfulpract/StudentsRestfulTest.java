package com.study.springbootrestfulpract;

import com.study.springbootrestfulpract.controller.StudentsController;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StudentsRestfulTest {
    @Resource
    private StudentsController studentsController;
    @Test
    public void test(){
        studentsController.getAllStudents();

    
    }
}
