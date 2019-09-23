package com.lwq.spring.instancefactory;

import java.util.Map;

import com.lwq.spring.bean.Car;

public class InstanceCarFactory {
	private Map<String, Car> cars = null;
	public InstanceCarFactory() {
		cars.put("audi", new Car("audi",200));
		
	}
	public Car getCar(String brand) {
		return cars.get(brand);
		
	}
}
