package com.mvc.controller;

import java.util.Map;

import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mvc.entity.User;
/**
 * 如果一个模型数据要多个页面可用 
 * 放入sessionScope中
 * 类上加上@SessionAttributes() 
 * 参数value 里面可以放数组
 * 参数type 放class类型
 * @author wayne
 * @date
 * @Description
 *
 */
//@SessionAttributes({"user"})
@SessionAttributes(value = {"user"},types = String.class)
@Controller
public class ModelController_2 {
	
	private static final String SUCCESS="success";
	@RequestMapping("sessionAttributes")
	public String testsessionAttributes(Map<String, Object> map) {
		User user = new User("wayne","18");
		//使用value属性值
		map.put("user", user);
		//使用type属性值
		map.put("school", "ZR");
		return SUCCESS;
	}
	
	
}
