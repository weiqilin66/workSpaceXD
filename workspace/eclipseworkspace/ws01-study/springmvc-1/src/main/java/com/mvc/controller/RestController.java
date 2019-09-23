package com.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * rest风格的请求方式
 * @author wayne
 * @date
 * @Description
 *
 */
@Controller
public class RestController {
	
	private static final String SUCCESS= "success";
	
	@RequestMapping(value = "/rest/{id}",method = RequestMethod.GET)
	public String name(@PathVariable("id") Integer id) {
		
		System.out.println("test get:"+id);
		return SUCCESS;
	}
	@RequestMapping(value = "/rest",method = RequestMethod.POST)
	public String name2() {
		System.out.println("test post");
		return SUCCESS;
	}
	@RequestMapping(value = "/rest/{id}",method = RequestMethod.PUT)
	public String name3(@PathVariable("id") Integer id) {
		
		System.out.println("test put:"+id);
		return SUCCESS;
	}
	@RequestMapping(value = "/rest/{id}",method = RequestMethod.DELETE)
	public String name4(@PathVariable("id") Integer id) {
		
		System.out.println("test delete:"+id);
		return SUCCESS;
	}
}
