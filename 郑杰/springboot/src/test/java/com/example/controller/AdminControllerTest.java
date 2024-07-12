package com.example.controller;

import com.example.common.Result;
import com.example.entity.Admin;
import com.example.exception.CustomException;
import com.example.service.AdminService;
import com.github.pagehelper.PageInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentCaptor.forClass;


import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.*;

public class AdminControllerTest {
    @Mock
    AdminService adminService;
    @InjectMocks
    AdminController adminController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testAdd() throws Exception {
        // 创建预期的Result对象
        Result expectedResult = Result.success();

        // 调用被测试的方法
        Result result = adminController.add(new Admin());

        // 验证adminService.add被调用
        verify(adminService).add(any(Admin.class));

        // 比较Result对象
        Assert.assertEquals(expectedResult, result);
        }


    @Test
    public void testDeleteById() throws Exception {
        // 创建预期的Result对象
        Result expectedResult = Result.success();
        Result result = adminController.deleteById(Integer.valueOf(1));
        verify(adminService).deleteById(anyInt());
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void testDeleteBatch() throws Exception {
        // 创建预期的Result对象
        Result expectedResult = Result.success();
        // 使用 Arrays.asList 创建包含单个元素的列表
        List<Integer> ids = Arrays.asList(Integer.valueOf(0));
        Result result = adminController.deleteBatch(ids);

        // 使用 ArgumentCaptor 来捕获参数
        ArgumentCaptor<List> captor = forClass(List.class);
        verify(adminService).deleteBatch(captor.capture());

        // 验证捕获的参数
        List<Integer> capturedArgument = captor.getValue();
        Assert.assertEquals(ids, capturedArgument);

        // 验证返回结果
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void testUpdateById() throws Exception {
        // 创建预期的Result对象
        Result expectedResult = Result.success();
        Result result = adminController.updateById(new Admin());
        verify(adminService).updateById(any(Admin.class));
        Assert.assertEquals(expectedResult, result);
    }


    @Test
    public void testSelectById() throws Exception {
        // 创建一个示例Admin对象
        Admin admin = new Admin();
        admin.setId(1);
        admin.setName("John Doe");

        // 设置mock行为
        when(adminService.selectById(1)).thenReturn(admin);

        // 调用方法
        Result result = adminController.selectById(Integer.valueOf(1));

        // 创建预期的Result对象
        Result expectedResult = Result.success(admin);

        // 验证结果
        Assert.assertEquals(expectedResult, result);
    }


    @Test
    public void testSelectAll() throws Exception {
//        when(adminService.selectAll(any(Admin.class))).thenReturn(List.of(new Admin()));
        when(adminService.selectAll(any(Admin.class))).thenReturn(Arrays.asList(new Admin()));
        Result result = adminController.selectAll(new Admin());
        Assert.assertEquals(new Result(), result);
    }

    @Test
    public void testSelectPage() throws Exception {
//        when(adminService.selectPage(any(Admin.class), anyInt(), anyInt())).thenReturn(new PageInfo<Admin>(List.of(null), 0));
        when(adminService.selectPage(any(Admin.class), anyInt(), anyInt()))
                .thenReturn(new PageInfo<Admin>(Arrays.asList((Admin) null), 0));
        Result result = adminController.selectPage(new Admin(), Integer.valueOf(0), Integer.valueOf(0));
        Assert.assertEquals(new Result(), result);
    }
}
