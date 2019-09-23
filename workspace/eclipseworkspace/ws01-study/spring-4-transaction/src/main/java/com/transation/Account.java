package com.transation;

public class Account {
	
	private String suername;
	private int balance;
	public String getSuername() {
		return suername;
	}
	public void setSuername(String suername) {
		this.suername = suername;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Account [suername=" + suername + ", balance=" + balance + "]";
	}
	
}
