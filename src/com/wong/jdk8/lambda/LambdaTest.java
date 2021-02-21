package com.wong.jdk8.lambda;

import java.util.Comparator;
import java.util.TreeSet;

import org.junit.Test;


/**
 * lambda表達式舉例
 */

public class LambdaTest {
    /**
     * 實現接口的匿名內部類實例，之前的方式
     */
    @Test
    public void test1() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("匿名內部類實例");
            }
        };

        runnable.run();
        // or
        Thread t = new Thread(runnable);
        t.start();

    }

    /**
     * Lambda表達式
     */
    @Test
    public void test2() {
//    	Runnable runnable = () -> {
//                System.out.println("匿名內部類實例");
//        };
    	// Runnable runnable = () -> System.out.println("Hello Lambda");
        Runnable runnable = () -> System.out.println(String.format("%s: Hello Lambda", Thread.currentThread().getName()));

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.setName("Thread1");
        t2.setName("Thread2");
        t1.start();
        t2.start();

    }

    /**
     * 原來使用匿名內部類實例作為參數傳遞
     */
    @Test
    public void test3() {
        // TreeSet使用定制排序
        Comparator<String> comparator = new Comparator<String>() { // 創建一個 實現了Comparator接口的匿名內部類的 對象
            @Override
            public int compare(String o1, String o2) {
                return -Integer.compare(o1.length(), o2.length());
            }
        };

        TreeSet<String> treeSet = new TreeSet<>(comparator);
        
        Comparator<Integer> comparator2 = new Comparator<>() {
        	public int compare(Integer o1, Integer o2) {
        		return Integer.compare(o1, o2);
        	}
		};
		int compare = comparator2.compare(43, 76);
		System.out.println(compare);

    }

    /**
     * Lambdai表達式實現的內部類對象
     */
    @Test
    public void test4() {
        Comparator<String> comparator = (o1, o2) -> {
            return -Integer.compare(o1.length(), o2.length());
        };
        // 上面的lambda表達式也可以簡寫成這樣的
        Comparator<String> comparator1 = (o1, o2) -> -Integer.compare(o1.length(), o2.length());

        TreeSet<String> treeSet = new TreeSet<>(comparator);
        TreeSet<String> treeSet1 = new TreeSet<>(comparator1);

        System.out.println(comparator1.compare("abc", "abdf"));
        
        Comparator<Integer> comparator2 = (o1, o2) -> {
        		return Integer.compare(o1, o2);
		};
		int compare = comparator2.compare(43, 76);
		System.out.println(compare);
		
		// 方法引用
		Comparator<Integer> comparator3 = Integer::compare;
		System.out.println(comparator3.compare(43, 76));
    }
}