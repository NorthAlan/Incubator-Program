package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//部门
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Department {
    private Integer department_id;
    private String departmentName;
}
