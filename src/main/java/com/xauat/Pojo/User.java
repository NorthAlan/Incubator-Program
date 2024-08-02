package com.xauat.Pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

//通过@Data注解，
// Lombok会自动生成toString()、equals()、hashCode()方法，以及每个字段的getter和setter方法，
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private short gender;
    private String image;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
