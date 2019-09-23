package com.lwq.spring.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lwq.spring.spel.Car;
import com.lwq.spring.spel.Persion;
import com.lwq.spring.bean.NewPersion;
import com.lwq.spring.relation.Address;

public class MainSpel {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-spel.xml");
	
		 Address address = (Address) ctx.getBean("address");
		 System.out.println(address);
		 
		 Car car = ctx.getBean(Car.class);
		 
	}
}
