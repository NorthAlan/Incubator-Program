package com.example.controller;

import com.example.common.Result;
import com.example.entity.Admin;
import com.example.entity.User;
import com.example.service.UserService;
import com.github.pagehelper.PageInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.Mockito.*;

public class UserControllerTest {
    @Mock
    UserService userService;
    @InjectMocks
    UserController userController;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAdd() throws Exception {
        // 创建预期的Result对象
        Result expectedResult = Result.success();
        Result result = userController.add(new User());
        verify(userService).add(any(User.class));
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void testDeleteById() throws Exception {
        // 创建预期的Result对象
        Result expectedResult = Result.success();
        Result result = userController.deleteById(Integer.valueOf(0));
        verify(userService).deleteById(anyInt());
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void testDeleteBatch() throws Exception {
        // 创建预期的Result对象
        Result expectedResult = Result.success();

        // 使用 Arrays.asList 创建包含单个元素的列表
        List<Integer> ids = Arrays.asList(Integer.valueOf(0));
        Result result = userController.deleteBatch(ids);

        // 使用 ArgumentCaptor 来捕获参数
        ArgumentCaptor<List> captor = forClass(List.class);
        verify(userService).deleteBatch(captor.capture());

        // 验证捕获的参数
        List<Integer> capturedArgument = captor.getValue();
        Assert.assertEquals(ids, capturedArgument);

        // 验证返回结果
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void testUpdateById() throws Exception {
        Result result = userController.updateById(new User());
        verify(userService).updateById(any(User.class));
        Assert.assertEquals(Result.success(), result);
    }

    @Test
    public void testSelectById() throws Exception {
        // 创建一个示例Admin对象
        User user = new User();
        user.setId(1);
        user.setName("John Doe");
        when(userService.selectById(anyInt())).thenReturn(user);

        Result result = userController.selectById(Integer.valueOf(0));
        Assert.assertEquals(Result.success(user), result);
    }

    @Test
    public void testSelectAll() throws Exception {
//        when(userService.selectAll(any(User.class))).thenReturn(List.of(new User()));

        Result result = userController.selectAll(new User());
        Assert.assertEquals(new Result(), result);
    }

    @Test
    public void testSelectPage() throws Exception {
//        when(userService.selectPage(any(User.class), anyInt(), anyInt())).thenReturn(new PageInfo<User>(List.of(null), 0));

        Result result = userController.selectPage(new User(), Integer.valueOf(0), Integer.valueOf(0));
        Assert.assertEquals(new Result(), result);
    }

    @Test
    public void testCharge() throws Exception {
        when(userService.charge(anyInt(), any(BigDecimal.class))).thenReturn(new BigDecimal(0));

        Result result = userController.charge(Integer.valueOf(0), new BigDecimal(0));
        Assert.assertEquals(new Result(), result);
    }
}
