package com.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/test")
@Controller
public class SpringMVCTest {

	private static final String SUCCESS="success";
	/**
	 * @pathvariable可以映射url的占位符到目标方法参数中
	 * @return
	 */
	@RequestMapping("/pathvariable/{id}")
	public String test1(@PathVariable("id") Integer id) {
		System.out.println(id);
		return SUCCESS;
	}
	
	/**
	 * 了解requestmapping的另外两参数 params 和 headers更加精确的
	 */
	@RequestMapping(value = "params" ,params = {"username","age!=10"})
	public String tt() {
		return SUCCESS;
	}
	@RequestMapping(value = "headers" ,headers = {"Accept-Language=zh-CN,zh;q=0.9,en;q=0.8"} )
	public String tt2() {
		return SUCCESS;
	}
	/**
	 * a标签是get请求  测试使用post方式表单提交
	 * @return
	 */
	@RequestMapping(value = "helloworldpost",method = RequestMethod.POST)
	public String t1() {
		return SUCCESS;
	}
	@RequestMapping("helloworld")
	public String t() {
		return SUCCESS;
		
	}
}
