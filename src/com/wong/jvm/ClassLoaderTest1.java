package com.wong.jvm;


public class ClassLoaderTest1 {
  public static void main(String[] args) {
    System.out.println("*********啟動類加載器************");
    // 獲取BootstrapClassLoader 能夠加載的API的路徑
//    URL[] urls = jdk.internal.loader.ClassLoaders
//    for (URL url : urls) {
//      System.out.println(url.toExternalForm());
//    }

    System.out.println(System.getProperty("sun.boot.class.path")); // after JDK 9/10 this returns null 

    // 從上面路徑中，隨意選擇一個類，來看看他的類加載器是什麼：得到的是null，說明是 根加載器
    //ClassLoader classLoader = Provider.class.getClassLoader();

    System.out.println("*********擴展類加載器************");
    String exDir = System.getProperty("java.ext.dirs");
    for(String path : exDir.split(";")) {
    	System.out.println(path);
    }
  }
}