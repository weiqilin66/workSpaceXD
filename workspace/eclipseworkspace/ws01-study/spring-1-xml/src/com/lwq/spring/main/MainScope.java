package com.lwq.spring.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lwq.spring.bean.Car;

public class MainScope {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-scope.xml");
		Car car = ctx.getBean(Car.class);
	
		Car car2  = ctx.getBean(Car.class);
		System.out.println(car==car2);
	}
}
