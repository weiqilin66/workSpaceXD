package com.lwq.spring.spel;

import com.lwq.spring.bean.Car;

public class Persion {
	private String name;

	private Car car;
	private String city;
	//car 30 金  否则 农民
	private String info;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	@Override
	public String toString() {
		return "Persion [name=" + name + ", car=" + car + "]";
	}

	
	
}
