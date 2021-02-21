package com.wong.jvm.string;

public class StringTableTest2 {
	static final int MAX_COUNT = 1000 * 10000;
	static final String[] arr = new String[MAX_COUNT];
	
	public static void main(String[] args) {
		Integer[] data = new Integer[] {1,2,3,4,5,6,7,8,9,10};
		
		long start = System.currentTimeMillis();
		for(int i=0; i<MAX_COUNT; i++) {
			//arr[i] = new String(String.valueOf(data[i % data.length])); // Time cost: 4724
			arr[i] = new String(String.valueOf(data[i % data.length])).intern(); // Time cost: 1331
		}
		long end = System.currentTimeMillis();
		System.out.println("Time cost: " + (end - start));
		try {
			Thread.sleep(1000000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.gc();
	}
}
