package com.JavaOOPS;

interface A453 {
	void sum();

	/*
	 * private void b() { } static void c() { }
	 */
	void mul();
	void div();
	abstract void sub();
	//int sum(int a , int b);
	  
}

interface B953 extends A453{
	void abc();
}

abstract class B9873948 implements B953 {
	public void div() {
		System.out.println("Inside div");
	}
	public void sum() {
		System.out.println("Inside sum in parent class");
	}
	
	//abstract void def();
	
}

class M extends B9873948 {
	public void sum() {
		System.out.println("Inside sum in child class");
	}

	public void mul() {
		System.out.println("Inside mul");
	}

	public void sub() {
		System.out.println("Inside sub");
	}


	public void abc() {
		System.out.println("inside abc");
		
	}
}

class AbstractClassRealTimeScenario {
	public static void main(String args[]) {
		//A453 obj = new A453(); cannot instantiate interface
		A453 a = new M();//upcasting
		a.sum();
		a.mul();
		a.div();
		a.sub();
		B953 b = new M();
		b.abc();
	}
}