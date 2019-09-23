package com.spring.annotation;

import org.springframework.stereotype.Component;

@Component
public class TestObject {

	@Override
	public String toString() {
		return "TestObject [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
}
