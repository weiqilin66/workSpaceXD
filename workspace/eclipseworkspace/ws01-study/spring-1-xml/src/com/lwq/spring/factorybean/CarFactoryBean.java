package com.lwq.spring.factorybean;

import org.springframework.beans.factory.FactoryBean;

import com.lwq.spring.bean.Car;

public class CarFactoryBean implements FactoryBean<Car> {
	private String brand;
	public void setBrand(String brand) {
		this.brand = brand;
	}
	//返回对象
	@Override
	public Car getObject() throws Exception {
		// TODO Auto-generated method stub
		return new Car(brand,400);
	}
	//返回bean的类型
	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return Car.class;
	}
	//是否是单实例
	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return false;
	}

}
