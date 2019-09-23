package com.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.aop.helloworld.ArithmeticCalculator;

public class Main {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-xml.xml");
		ArithmeticCalculator ari = ctx.getBean(ArithmeticCalculator.class);
		int i = ari.add(1, 2);
		System.out.println(i);
		 //i = ari.div(10, 0);
		//System.out.println(i);
	}
}
