package com.lwq.serializable;

import java.util.Scanner;

import javax.management.loading.PrivateClassLoader;

public class Test {

	public static void main(String[] args) {
		
		int a= sub(5,1);
		
		System.out.println(a);
	}
	public static int sub(int i, int j) {
		int res = i-j;
		return res;
	}
	
	

}
