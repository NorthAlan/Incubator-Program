package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

//学生
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {
    private Integer student_id;
    private String name;
    private String email;
    private Integer gender;  //0:女 1:男
    private Integer department_id;

    // 使用@DateTimeFormat注解
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date birth;
}

/*
* create table mybatis.student(id integer, name varchar(50), email varchar(50), gender integer, );
* */