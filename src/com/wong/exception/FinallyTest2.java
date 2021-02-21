package com.wong.exception;

import org.junit.Test;

public class FinallyTest2 {
	@Test
	public void test() {
	  int i = result3(); 
	  System.out.println(i);
	  System.out.println("--------------");
	  int a = result2(); 
	  System.out.println(a);
	}

	public int result3() {
	  try {
	    int[] arr = new int[10]; //  "我是finally塊"  1
	    //System.out.println(arr[10]); //error -->  java.lang.ArrayIndexOutOfBoundsException:  "我是finally塊"  3
	    return 1;
	  } catch(Exception e) {
	    e.printStackTrace();
	    return 3;
	  } finally {
	    System.out.println("我是finally塊");
	  }
	}

	@SuppressWarnings("finally")
	public int result2() {
	  try {
	    int[] arr = new int[10]; 
	    System.out.println(arr[10]); //error --> java.lang.ArrayIndexOutOfBoundsException: "我是finally塊" 2
	    return 1;
	  } catch(Exception e) {
	    e.printStackTrace();
	    return 3;
	  } finally {
	    System.out.println("我是finally塊");
	    return 2;
	  }
	}
}
