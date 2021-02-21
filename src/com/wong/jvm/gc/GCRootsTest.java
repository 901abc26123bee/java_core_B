package com.wong.jvm.gc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class GCRootsTest {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		List<Object> numList = new ArrayList<>();
		Date birth = new Date();
		
		for(int i=0; i<100; i++) {
			numList.add(String.valueOf(i));
		}
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("data loading completed");
		
		new Scanner(System.in).next();
		
		numList = null;
		birth = null;
		
		System.out.println("numList, birth already release, pointing to null");
		
		new Scanner(System.in).next();
		System.out.println("end");
	}
}
