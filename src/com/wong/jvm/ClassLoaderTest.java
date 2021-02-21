package com.wong.jvm;

public class ClassLoaderTest {
	public static void main(String[] args) {
		// 獲取 系統類加載器 System ClassLoader
		ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
		System.out.println(systemClassLoader);

		// 獲取其上層的：擴展類加載器 Extension ClassLoader
		ClassLoader extClassLoader = systemClassLoader.getParent();
		System.out.println(extClassLoader);

		// 試圖獲取 根加載器：引導類加載器 Bootstrap ClassLoader
		ClassLoader bootstrapClassLoader = extClassLoader.getParent();
		System.out.println(bootstrapClassLoader);

		// 獲取自定義加載器(當前類 ClassLoaderTest)
		ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
		System.out.println(classLoader);

		// 獲取String類型的加載器
		ClassLoader classLoader1 = String.class.getClassLoader();
		System.out.println(classLoader1);

	}
}
