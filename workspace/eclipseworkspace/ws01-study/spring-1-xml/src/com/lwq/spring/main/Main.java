package com.lwq.spring.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lwq.spring.bean.Car;
import com.lwq.spring.bean.NewPersion;

public class Main {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		//HelloWorld helloWorld = (HelloWorld) ctx.getBean("helloWorld");
		Car car = (Car) ctx.getBean("car2");
		Car car2 = (Car) ctx.getBean("car");
		System.out.println(car);
		System.out.println(car2);
		/*
		 * Persion persion = (Persion) ctx.getBean("persion1");
		 * System.out.println(persion);
		 */
		NewPersion p1 = (NewPersion) ctx.getBean("newPersion");
		System.out.println(p1);
	}
}
