package com.wong.jvm;

public class OpreationStackTest {
	public static void main(String[] args) {
		
	}
	
	public void add() {
		// 1.
		int i1 = 10;
		i1++;
		
		int i2 = 10;
		++i2;
		
		// 2.
		int i3 = 10;
		int i4 = i3++;
		
		int i5 = 10;
		int i6 = ++i5;
		
		// 3.
		int i7 = 10;
		i7 = i7++;
		
		int i8 = 10;
		i8 = ++i8;
		
		// 4.
		int i9 = 10;
		int i10 = i9++ + ++i9;
		
	}
	
}
