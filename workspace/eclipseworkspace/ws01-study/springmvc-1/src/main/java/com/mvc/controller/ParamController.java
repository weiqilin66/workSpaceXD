package com.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mvc.entity.User;
/**
 * 接收前台参数
 * @author wayne
 * @date
 * @Description
 *
 */
@Controller
public class ParamController {

	private static final String SUCCESS = "success";
	/**
	 * @requestParam的三个重要参数  
	 * 1.value
	 * 2.required 默认true  false的话可不传这个参数
	 * 3.defaultValue  设置默认值
	 * 一个请求，只有一个RequestBody，可以有多个RequestParam
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/param1")
	public String param1(@RequestParam(value = "username",required = false,defaultValue = "0") String username) {
		System.out.println(username);
		return SUCCESS; 
	}
	/**
	 * 属性过多时候,直接使用pojo 一个对象来接收,支持级联属性 ,例如user对象中的address对象属性
	 */
	@RequestMapping(value = "/pojo",method = RequestMethod.GET)
	public String pojo( User user) {
		System.out.println(user);
		return SUCCESS;
	}
	@RequestMapping(value = "/testRequestBody",method = RequestMethod.POST)
	public String testRequestBody(@RequestBody User user) {
		System.out.println(user);
		
		return SUCCESS;
	}
	
	/**
	 * 了解,映射请求头信息
	 * 
	 */
	@RequestMapping("/testRequestHeader")
	public String t(@RequestHeader(value = "Accept-Language") String al) {
		System.out.println("al:"+al);
		
		return SUCCESS;
		
	}
	
}
