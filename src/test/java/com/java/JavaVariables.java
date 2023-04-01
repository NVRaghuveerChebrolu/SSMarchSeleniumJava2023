package com.java;

public class JavaVariables {
	int a =10; //instance variables : declared inside a class but outside a method
	static int b=9; // static variables declared with help of static keyword
	
	//local variables : variables declared inside a method is called as local variables
	 int add(int i, int j) {     
		//System.out.println(i+j);
		return i+j;
	}
	
	public static void main(String[] args) {
		JavaVariables obj = new JavaVariables();
		System.out.println(obj.a);
		System.out.println(b);
		System.out.println(obj.add(4,8));
	}

}
