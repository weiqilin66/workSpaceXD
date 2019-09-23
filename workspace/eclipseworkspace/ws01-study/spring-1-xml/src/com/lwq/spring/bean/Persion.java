package com.lwq.spring.bean;

import java.util.List;

public class Persion {
	private String name;
	private int age;
	
	private List<Car> cars;
	public void setAge(int age) {
		this.age = age;
	}
	

	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}

	public String getName() {
		return name;
	}
	public Persion() {
		// TODO Auto-generated constructor stub
	}
	public List<Car> getCars() {
		return cars;
	}
	public void setCars(List<Car> cars) {
		this.cars = cars;
	}


	@Override
	public String toString() {
		return "Persion [name=" + name + ", age=" + age + ", cars=" + cars + "]";
	}
	
	
}
