package com.AccessModifier2;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import com.AccessModifiers.accessModifier_Protected;

class accessModifier_protected1 extends accessModifier_Protected {
	
	accessModifier_protected1(){
		System.out.println("inside child class constructor");
	}
	
	public static void main(String args[]) {
		accessModifier_protected1 obj = new accessModifier_protected1();
		obj.msg123();
		System.out.println(obj.a);
	
	}
}