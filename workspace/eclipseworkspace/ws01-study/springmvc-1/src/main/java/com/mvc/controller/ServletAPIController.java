package com.mvc.controller;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 接收servlet原生API作为目标方法的参数
 * 
 * HttpServletRequest,HttpServletResponse,HttpSession,
 * Locale InputStream,outputstream,
 * reader,writer
 * 
 * @author wayne
 * @date
 * @Description
 *
 */
@Controller
public class ServletAPIController {

	public static final String SUCCESS = "success";
	
	@RequestMapping("/servlet")
	public void name(HttpServletRequest request,HttpServletResponse response,Writer out) throws IOException {
		
		out.write("hello");
		System.out.println(request+","+response);
		//return SUCCESS;
	}
	
}
