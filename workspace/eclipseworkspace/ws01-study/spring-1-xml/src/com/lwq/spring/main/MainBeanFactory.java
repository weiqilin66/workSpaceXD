package com.lwq.spring.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lwq.spring.bean.Car;

public class MainBeanFactory {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-beanfactory.xml");
		Car car  = (Car) ctx.getBean("car");
		System.out.println(car);
	}
}
