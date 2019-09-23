package com.lwq.sjjg;
import java.util.*;

public class StackDemo {
	
	private static void showpush(Stack<Integer> st,int i) {
		// TODO Auto-generated method stub
		st.push(i);
		System.out.println("push:"+i);
		System.out.println("Stack:"+st);
	}
	private static void showpop(Stack<Integer> st) {
		// TODO Auto-generated method stub
		st.pop();
		System.out.println("stack:"+st);
	}
	public static void main(String[] args) {
		Stack<Integer> st =  new Stack<Integer>();
		System.out.println("stack:"+st);
		showpush(st,11);
		showpush(st,12);
		showpop(st);
		showpop(st);
		
		try {
			showpop(st);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("emptystack");
		}
	}
}
