package com.crud.test;

import static org.hamcrest.CoreMatchers.nullValue;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class TestJunit {

	ApplicationContext ctx=null;
	{
		 ctx =new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	@Test
	public void test() {
		System.out.println(ctx);
	}
}
