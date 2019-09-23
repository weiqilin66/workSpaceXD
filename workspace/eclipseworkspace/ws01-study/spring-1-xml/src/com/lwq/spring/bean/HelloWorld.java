package com.lwq.spring.bean;

public class HelloWorld {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		System.out.println("setname"+name);
	}
	public HelloWorld() {
		System.out.println("hello:"+name);
	}
}
