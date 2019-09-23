package com.lwq.spring.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lwq.spring.relation.Persion;

public class MainAutowire {
	public static void main(String[] args) {
		ApplicationContext ctx= new ClassPathXmlApplicationContext("beans-relation.xml");
		Persion persion = ctx.getBean(Persion.class);
		System.out.println(persion);
	}
}
