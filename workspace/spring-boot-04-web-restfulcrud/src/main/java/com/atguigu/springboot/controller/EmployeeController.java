package com.atguigu.springboot.controller;

import com.atguigu.springboot.dao.DepartmentDao;
import com.atguigu.springboot.dao.EmployeeDao;
import com.atguigu.springboot.entities.Department;
import com.atguigu.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;
import java.util.List;

@Controller
public class EmployeeController {
    //查询所有员工返回列表页面
    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;
    @GetMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees =employeeDao.getAll();
        //放在请求域中
        model.addAttribute("emps",employees);
        return "/emp/list";
    }

    @GetMapping("/emp")
    public String toAddPage(Model model){
        //先查出部门
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "/emp/add";
    }
    //springmvc 自动封装  要求属性名一致
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        //redirect:重定向  forward转发    /代表当前项目路径
        return "redirect:/emps";
    }

    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model){
        model.addAttribute("emp",employeeDao.get(id));
        return "emp/add";
    }
}
