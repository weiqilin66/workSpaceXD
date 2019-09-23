package com.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crud.bean.Employee;
import com.crud.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService; //SQL逻辑在dao,service持久层中完成,程序逻辑在控制器完成
	
	@RequestMapping("/emps")
	public String getList(@RequestParam(defaultValue = "1",value = "pn") Integer pn,Model model) {
	PageHelper pageHelper = new PageHelper();
		//startpage后面紧跟需要分页的查询
	pageHelper.startPage(pn, 5);
	List<Employee> list=employeeService.getAll();
	//显示的页码为5个  例如第5页时显示34567
	PageInfo pi = new PageInfo(list, 5);
	//model会放在请求域中带给页面
	model.addAttribute("pageInfo",pi);
		return "list";
	}
}
