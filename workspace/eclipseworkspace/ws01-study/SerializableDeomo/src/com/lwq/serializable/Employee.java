package com.lwq.serializable;

import java.io.Serializable;

public class Employee implements Serializable
{
   public String name;
   public String address;
   public transient int SSN;
   public int number;
   public Car car;
   public void mailCheck()
   {
      System.out.println("Mailing a check to " + name
                           + " " + address);
   }
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public int getSSN() {
	return SSN;
}
public void setSSN(int sSN) {
	SSN = sSN;
}
public int getNumber() {
	return number;
}
public void setNumber(int number) {
	this.number = number;
}
public Car getCar() {
	return car;
}
public void setCar(Car car) {
	this.car = car;
}
@Override
public String toString() {
	return "Employee [name=" + name + ", address=" + address + ", number=" + number + ", car=" + car + "]";
}
   
}