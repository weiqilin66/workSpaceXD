package com.crud.test;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.crud.bean.Department;
import com.crud.bean.Employee;
import com.crud.dao.DepartmentMapper;
import com.crud.dao.EmployeeMapper;
import com.crud.service.EmployeeService;
/**
 * 
 * 
 * @author wayne
 * 1 先创建ioc容器
 * 2 容器获取bean
 * 3---1 2都不用
 * !!!使用spring的单元测试
 * a.导入spring的 springTest模块
 * b.@ContextConfiguration 注解类
 * c.可以autowired Bean
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestMapper {
	
	
	  @Autowired 
	  DepartmentMapper  departmentMapper;
	  @Autowired 
	  EmployeeMapper  employeeMapper;
	  @Autowired
	  SqlSession SqlSession;
	  @Autowired
	  EmployeeService emp;
	
	@Test
	public void testSpringJunit() {
		
		System.out.println(departmentMapper);
		
		//插入
		//departmentMapper.insertSelective(new Department(null, "Jerry"));
		//employeeMapper.insertSelective(new Employee(null, "Jerry", "M", "Jerry@lwq.com", 1));
		
		/**
		 *使用sqlsession的批量插入 ,也可以在mapper中使用colection去插入集合,显然sqlsession更通用 这样连接一次的会话讲插入1000条数据,如果使用for(){注入的employeeMapper} 将使用1000个sqlsession
		 1 考虑会经常使用 在spring的 applicationContext中定义一个 
		 */
		EmployeeMapper employeeMapper = SqlSession.getMapper(EmployeeMapper.class);
		for(int i =0 ; i<1000;i++) {
			String uid = UUID.randomUUID().toString().substring(0,5)+i;
			employeeMapper.insertSelective(new Employee(null, uid, "M", uid+"@lwq.com", 1));
			
		};
		System.out.println("批量插入结束");
	}
	
	
	@Test
	public void testUpdate() {
		System.out.println(emp.getAll().size());
		//departmentMapper.updateByPrimaryKey(new Department(1, "Tom"));
		/*
		 * Map map = new LinkedHashMap<Integer, String>(); map.put(1, "Jerry");
		 * departmentMapper.updateByExample(map,null);
		 */
	}
	
	
}
