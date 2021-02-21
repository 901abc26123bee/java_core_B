package com.wong.reflection;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/*
reflection 反射
## java反射機制提供的功能
* 在運行時判斷任意一個對象所屬的類
* 在運行時判斷任意一個類的對象
* 在運行時判斷任意一個類所具有的成員變量和方法
* 在運行時判斷任意一個對象的成員變量和方法
* 生成動態代理
## 反射相關的主要API
* java.lang.Class 代表一個類，是反射的源頭
* java.lang.reflect.Method 代表類的方法
* java.lang.reflect.Field 代表類的成員變量
* java.lang.reflect.Constructor 代表類的構造器
Class類
## Class方法
public Field getField(String name)
我們新建源文件如 j.java，在裡面創建一個類，通過javac編譯該源文件件，生成對應的 類名.class文件，一個類一個class文件。
之後使用java加載（JVM的加載器完成的），class文件加載到內存中，就是一個運行時類，存在緩存區。這個運行時類就是一個
Class的實例。
每個運行時類只加載一次。
有了相應類的運行時類，即Class對象，可有下列功能
* 創建相應運行時類的對象
* 獲取相應運行時類的完整結構（屬性、構造器、方法、內部類、接口、父類、所在的包、註解、拋出的異常）
* 調用對應的運行時類的指定結構（屬性、方法、構造器）
* 反射的應用：動態代理
類加載器ClassLoader
    類加載器是用來把類(class)裝載時內存的。 JVM規範定義了兩種類型的類加載器：啟動類加載器(bootstrap)和用戶定義
加載器(user-defined class loader)。 JVM在運行時會產生3個類加載器組成的初始化加載器層次結構：
Bootstrap ClassLoader 引導類加載器：用C++編寫，是JVM自帶的類加載器，負責java平台核心庫，用來加載核心類庫。訪加載器無法直接獲取
Extension ClassLoader 擴展類加載器：負責jre/lib/ext 目錄下的jar包或-D java.ext.dirs指定目錄下的jar包載入工作庫
System ClassLoader 系統類加載器：負責java -classpath 或 -D java.class.path所指定的目錄下的類與jar包載入工作，是最常用的加載器
* */

import org.junit.Test;

@SuppressWarnings({ "rawtypes" })
public class ReflectionTest {
    @Test
    public void test1() {
        // 正常創建類的對象方法，即不使用反射
        Person p1 = new Person("atom", 30);
        System.out.println(p1);

        p1.speak("give ma an apple");

    }

    
	@SuppressWarnings("unchecked")
	@Test
    public void test2() throws IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        System.out.println("-------------------");       
       	// 1. 獲取類對應的Class對象
        Class clazz = Person.class;
        // 獲取私有帶參構造方法對象 
        // NoSuchMethodException:沒有這個方法異常 
        // 原因是我們一開始使用的方法只能獲取公共的,下面這種方式就可以了。 
        // Constructor cons = clazz.getConstructor(String.class, int.class);
        Constructor cons = clazz.getDeclaredConstructor(String.class, int.class);
        cons.setAccessible(true);

        // 2. 通過Class對象創建對應的運行時類Person類的對象
        Object obj = cons.newInstance("atom", 30); // 代用了的Person(String, int)構造器
        Person p1 = (Person) obj;
        System.out.println(p1); // Person{name='atom', age=30}
        System.out.println("-------------------");
        // 通過反射調用運行時類的指定屬性
        Field f1 = clazz.getField("name");
        f1.set(p1, "emac");
        System.out.println(p1.toString()); // Person{name='emac', age=30}

        // 訪問無權限的屬性時(如private, 不同下沒有權限的)，設置可訪問值為true
        //getField只能調用public聲明的方法，而getDeclaredField基本可以調用任何類型聲明的方法
        Field f2 = clazz.getDeclaredField("age");
        f2.setAccessible(true);
        f2.set(p1, 22); 
        System.out.println(p1.toString()); // Person{name='emac', age=22}

        // 方法
        Method m1 = clazz.getMethod("getName");
        //getMethod只能調用public聲明的方法，而getDeclaredMethod基本可以調用任何類型聲明的方法
        Object obj67 = m1.invoke(p1);
        String name = (String) obj67;
        System.out.println(obj67); // emac

        Method m2 = clazz.getMethod("speak", String.class);
        Object obj72 = m2.invoke(p1, "再來一瓶啤酒..."); // emac说：再來一瓶啤酒...
        System.out.println(obj72); // null, m2 方法是無返回值,void

    }

    @Test
    public void test3() {
        // 獲取類的Class實例

        // 方式1：通過運行時類的對象，調用 對象.getClass()
        Person p1 = new Person();
        // 獲取對象的運行時類
        Class clazz = p1.getClass();
        System.out.println(p1);
        System.out.println(clazz);

        // 方式2：通過運行時類，調用 類.class
        Class<String> clazz2 = String.class;
        System.out.println(clazz2.getName());

        // 方式3：通過Class的靜態方法，調用 public Class Class.forName(String className)
        // className必須是完整路徑的
        String className = "com.java.www.Person";
        Class clazz3 = null;
        try {
            clazz3 = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(clazz3.getName());


        Class<Person> clazz4 = Person.class;
        System.out.println(clazz == clazz4); // true
        System.out.println(clazz == clazz3); // true
        System.out.println();

        // 方式4：類的加載器
        ClassLoader loader = this.getClass().getClassLoader();
        try {
            Class clazz5 = loader.loadClass("java.lang.Math");
            System.out.println(clazz5);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4() {
        // 類加載器
        ClassLoader loader0 = this.getClass().getClassLoader();
        System.out.println(loader0);

        String className = "java.lang.Math";
        Class clazz = null;
        try {
            clazz = loader0.loadClass(className);
            System.out.println(clazz.getName());
            ClassLoader loader5 = clazz.getClassLoader(); // null
            System.out.println(loader5);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println();

        ClassLoader loader1 = ClassLoader.getSystemClassLoader();
        System.out.println(loader1);

        ClassLoader loader2 = loader1.getParent();
        System.out.println(loader2);

        ClassLoader loader3 = loader2.getParent();
        System.out.println(loader3); // BootStrap ClassLoader 無法直接獲取

        Class<Person> clazz1 = Person.class;
        ClassLoader loader4 = clazz1.getClassLoader();
        System.out.println(loader4);

    }

    @Test
    public void test5() {
        // 利用類加載器訪問包內的文件，非根路徑下的
        ClassLoader loader = this.getClass().getClassLoader();
        String filePath = "jdbc.properties";
        // jdbc.properties 在 src 目錄下
        InputStream is = loader.getResourceAsStream(filePath);

        // 或
//		InputStream fis = null;
//		try {
//			// FileInputStream相對路徑只能加載(workspace)根目錄下或絕對路徑的文件，相對包路徑下的文件加載找不到路徑
//			fis = new FileInputStream("/Users/---------/Desktop/jee-2020-06/workspace/Java_Advance/src/jdbc.properties"); 
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}

        Properties properties = new Properties();
        try {
            properties.load(is);
            String host = properties.getProperty("host"); // 等號後的""也會取出來
            String port = properties.getProperty("port");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");

            System.out.println(host);
            System.out.println(port);
            System.out.println(user);
            System.out.println(password);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

