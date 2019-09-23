package com.spring.annotation;

import org.springframework.stereotype.Controller;

@Controller
public class UserControl {
	public void excute() {
		System.out.println("usercontrol excute...");
	}
	private UserService userService;
}
