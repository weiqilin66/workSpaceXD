package com.lwq.spring.autowire;

public class Car {
	private String brand;
	private double balance;
	private  float pay;
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public double getBalance() {
		return balance;
	}
	public float getPay() {
		return pay;
	}
	public void setPay(float pay) {
		this.pay = pay;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public Car(String brand, double balance) {
		super();
		this.brand = brand;
		this.balance = balance;
		System.out.println("brand"+brand+"balance"+balance);
	}
	public Car(String brand, float pay) {
		super();
		this.brand = brand;
		this.pay = pay;
	}
	@Override
	public String toString() {
		return "Car [brand=" + brand + ", balance=" + balance + ", pay=" + pay + "]";
	}
	public Car() {
		super();
	}

}
