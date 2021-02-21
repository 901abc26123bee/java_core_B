package com.wong.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/*
java反射功能展示
Class.getField(fieldName) 獲取運行時類中public修飾的名為fieldName的屬性
Class.getDeclaredField(fieldName) 獲取運行時類聲明了的且名為fieldName的屬性，包括private修飾的
* */

import org.junit.Test;

@SuppressWarnings({"rawtypes", "unchecked", "deprecation"})
public class ReflectionTest2 {
    private Class clas = Person.class;
	@Test
    public void test1() {
        // 獲取構造器

        String className = "com.wong.reflection.Person";
        Class clazz = null;
        try {
            clazz = Class.forName(className);
            Object obj = clazz.newInstance();
            Person p1 = (Person) obj;
            System.out.println(p1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();

        Constructor[] constructors = clazz.getDeclaredConstructors();
        System.out.println("獲取所有構造器");
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }
    }

    @Test
    public void test2() {
        // 獲取父類

        Class<Person> clazz = Person.class;
        Class superClass = clazz.getSuperclass();
        System.out.println(superClass); // class com.java.www.Biology

    }

    
	@Test
    public void test3() {
        // 獲取帶泛型的父類

        Class clazz = Person.class;
        Type type1 = clazz.getGenericSuperclass();
        System.out.println(type1);

    }

    @Test
    public void test4() {
        // 獲取父類的泛型

        Class clazz = Person.class;
        Type type1 = clazz.getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) type1;
        Type[] args = parameterizedType.getActualTypeArguments();
        Class firstArg = (Class) args[0];
        System.out.println(firstArg.getName());
        // System.out.println(((Class) args[0]).getName());
        System.out.println(args[0].getTypeName());
// for (Type t : args) {
// System.out.println(t);
// }
    }

    @Test
    public void test5() {
        // 獲取運行時類實現的接口
        Class[] interfaces = clas.getInterfaces();
        for (Class i : interfaces) {
            System.out.println(i);
        }
        System.out.println();
        // 獲取運行時類的父類實現的接口
        Class[] superinterfaces = clas.getSuperclass().getInterfaces();
        for (Class c : superinterfaces ) {
            System.out.println(c);
        }
    }
    @Test
    public void test6() {
        // 獲取類所在的包
        Package pack = clas.getPackage();
        System.out.println(pack);

    }

    @Test
    public void test7() {
        // 獲取註解

        Annotation[] annotations = clas.getAnnotations(); // 只能獲取到 RetentionPolicy.RUNTIME 這個生命週期的註解
        for (Annotation anno : annotations) {
            System.out.println(anno);
        }

    }

    @Test
    public void test8() {
        // 獲取屬性字段

        Field[] fields = clas.getDeclaredFields();
        System.out.println("修飾符 類型 屬性名");
        for (Field f : fields) {
            // 獲取屬性修飾符
            int i = f.getModifiers();
            String modifier = Modifier.toString(i);
            System.out.print(modifier + " ");

            // 獲取屬性的類型
            Class type = f.getType();
            String[] typeStr = type.toString().split("\\.");
            System.out.print(typeStr[typeStr.length - 1] + " ");

            // 獲取屬性名
            System.out.print(f.getName());
            System.out.println();
        }

    }

    @Test
    public void test9() {
        // 獲取方法

        // 獲取此類以及父類的所有public 方法
        Method[] methods = clas.getMethods();
        for (Method m : methods) {
            System.out.println(m);
        }
        System.out.println();

        // 獲取此類本身聲明的所有方法，包括private方法
        Method[] methods2 = clas.getDeclaredMethods();
        for (Method m : methods2) {
            // 獲取方法的異常信息
            System.out.println(m);
            Class[] exceptionArr = m.getExceptionTypes();
            if (exceptionArr.length > 0) {
                System.out.println("方法的異常信息：");
                for (Class e : exceptionArr) {
                    System.out.println(e);
                }
                System.out.println();
            }

        }
    }

    @Test
    public void test10() {
        // 獲取內部類
        Class[] innerClazz = clas.getDeclaredClasses();
        for (Class c : innerClazz) {
            System.out.println(c);
        }
    }

    @Test
    public void test12() {
        // 獲取指定的屬性，並操作該屬性

        Person p1 = null;
        try {
            p1 = (Person) clas.newInstance();

            Field name = clas.getField("name");
            name.set(p1, "Leon");

            // 獲取並做操私有屬性
            Field age = clas.getDeclaredField("age");
            age.setAccessible(true); // 設置此屬性可操作
            age.set(p1, 22);

            System.out.println(p1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
	@Test
    public void test13() {
        // 獲取指定的方法，並調用該方法

        Method m1 = null;
        try {
            Person p1 = (Person) clas.newInstance();
            p1.setName("wong");
            p1.setAge(32);

            // public 無參方法
            m1 = clas.getMethod("getAge");
            Object ret = m1.invoke(p1);
            int age = (Integer) ret;
            System.out.println(age);

            // public 有參方法
            Method m2 = clas.getMethod("speak", String.class);
            Object ret191 = m2.invoke(p1, "saying Hello");

            // private 有參方法
            Method m3 = clas.getDeclaredMethod("see", int.class, String.class);
            m3.setAccessible(true);
            Object returnValue = m3.invoke(p1, 10, "站著");
            // String returnValueString = (String) returnValue;
            System.out.println(returnValue);

            // 靜態方法 private static void info()
            Method m4 = clas.getDeclaredMethod("info");
            m4.setAccessible(true);
            m4.invoke(Person.class); // 調用的運行時類中的方法沒有返回值，invoke() 方法返回 void
            m4.invoke(null); // 對象 null 也可以


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test14() {
        // 調用指定的構造器

        try {
            // 空參構造器
            Constructor constructor = clas.getConstructor();
            Object obj = constructor.newInstance();
            System.out.println(obj);

            // 獲取帶參的構造器
            Constructor constructor2 = clas.getDeclaredConstructor(String.class);
            constructor2.setAccessible(true);
            Person p2 = (Person) constructor2.newInstance("鬼穀子");
            System.out.println(p2);

            // 獲取帶參的構造器
            Constructor constructor3 = clas.getDeclaredConstructor(String.class, int.class);
            Person p3 = (Person) constructor3.newInstance("孫臏", 33);
            System.out.println(p3);

        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("null")
	@Test
    public void test15() {
      	// 獲取此類以及父類的所有public 方法
        Method[] methods = clas.getDeclaredMethods();
        for (Method m : methods) {
          // 獲取註解
          Annotation[] annotations = m.getAnnotations(); // 只能獲取到 RetentionPolicy.RUNTIME 這個生命週期的註解
          for (Annotation anno : annotations) {
            System.out.println(anno);
          }
          // 獲取權限修飾符
          System.out.print(Modifier.toString(m.getModifiers()) + "\t");
          // 返回值類型
          System.out.print(m.getReturnType().getName() + "\t");
          // 方法名
          System.out.print(m.getName());
          System.out.print("(");
          //形參列表
          Class[] parameterTypes = m.getParameterTypes();
          if(!(parameterTypes==null && parameterTypes.length==0)){
            for(int i=0; i<parameterTypes.length; i++){
            	if(i == parameterTypes.length-1) {
            		System.out.print(parameterTypes[i].getName() + " args_" + i);
            		break;
            	}
              System.out.print(parameterTypes[i].getName() + " args_" + i + ", ");
            }
          }   
          System.out.print(")");
          // 拋出異常
          Class[] exceptionTypes = m.getExceptionTypes();
          if(exceptionTypes.length > 0){
        	  System.out.print("throws ");
            for(int i=0; i<exceptionTypes.length; i++){
              if(i == exceptionTypes.length-1) {
                System.out.print(exceptionTypes[i].getName());
                break;
              }
              System.out.print(parameterTypes[i].getName() +  ", ");
            }
          }
          
          System.out.println();
        }
    }
}

