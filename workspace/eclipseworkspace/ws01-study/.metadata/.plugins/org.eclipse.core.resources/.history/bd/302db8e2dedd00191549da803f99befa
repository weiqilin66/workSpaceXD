package com.mvc.controller;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mvc.entity.User;
/**
 * @modelattribute
 * 在该控制器类下,任何一个方法执行前都会先执行@modelAtrribute标注的方法 ,
 * 运行流程:
 * 1.执行@ModelAttribute修饰的方法,从数据库取出对象,把对象放入map中 键为user
 * 2.springmvc 从map中取出user对象,并把表单的请求参数付给该user对象的对应属性
 * 3.springmvc 把上述对象传入目标方法
 * 
 * 注意:在@modelattribute修饰的方法 放入的键需要和目标方法的入参类型的一致(首字母小写)
 * @author wayne
 * @date
 * @Description
 *
 */
@Controller
public class ModelController_3 {

	private static final String SUCCESS = "success";
	
	@ModelAttribute
	public void getUser(@RequestParam(value = "id",required = false) Integer id,Map<String,Object> map) {
		if (id!=null) {
			User user = new User("joy", "18");
			System.out.println("从数据库先获取到对象user"+user);
			map.put("user", user);
		}
		
	}
	
	
	@RequestMapping("testModelAttributes")
	public String testModelAttributes(User user) {
		
		System.out.println(user);
		
		return SUCCESS;
		
	}
}
