package com.wong.jvm;

public class ClassLoaderTest2 {
	public static void main(String[] args) {
		ClassLoader classLoader;
		try {
			// 1.
			classLoader = Class.forName("java.lang.String").getClassLoader();
			System.out.println(classLoader); // null = bootstrapLoader
			
			// 2. 
			ClassLoader classLoader2 = Thread.currentThread().getContextClassLoader();
			System.out.println(classLoader2);
			
			// 3.
			ClassLoader classLoader3 = ClassLoader.getSystemClassLoader().getParent();
			System.out.println(classLoader3);
			
			// 4.
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
