package com.example.service;

import com.example.config.PageRequest;
import com.example.config.PageResult;
import com.example.config.PageUtils;
import com.example.mapper.StudentMapper;
import com.example.pojo.Student;
import com.example.service.Impl.IStudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
        return del > 0 ? "success" : "fail";

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

    public PageInfo<Student> getByPage(int PageNum, int PageSize) {
        PageHelper.startPage(PageNum, PageSize);
        List<Student> list = studentMapper.getByPage();
        return new PageInfo<>(list);
    }

    /**
     * 调用分页插件完成分页
     */

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest));
    }
    private PageInfo<Student> getPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Student> sysMenus = studentMapper.selectPage();
        return new PageInfo<Student>(sysMenus);
    }
}
