package com.wong.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.junit.Test;
/*
+ iterator() 返回一個Iterator接口實現類對象，可用於遍歷集合
	- 內部方法：hasNext() next()
 	- 集合對象每次調用 iterator() 方法，都得到一個"全新"的迭代器對象，
 		默認游標都在集合的第一個元素之前
 	- 內部定義 remove() 方法，可以在遍歷的時候，刪除集合中的元素，此方法不同於集合中直接調用 remove()
 	- 如果還未調用 next() 或在上一次調用 next() 方法時候已經調用 remove() 方法，在調用 remove() 都會報 IllegalStateException

 */


@SuppressWarnings({ "unchecked", "rawtypes" })
public class CollectionIterator {

Collection c1 = new ArrayList();

    {
        c1.add(22);
        c1.add("MM");
        c1.add(true);
        c1.add(new Person("alisha", 18));
        c1.add(new String("Atom"));
    }

    @Test
    public void test1() {
    	// remove 集合中 "Atom"
        Iterator it = c1.iterator();
        while (it.hasNext()) {
        	// it.remove(); // 報 IllegalStateException
        	Object obj = it.next();
            if("Atom".equals(obj)) {
            	it.remove();
            	// it.remove(); // 報 IllegalStateException
            }
        }
        // 遍歷集合
        Iterator it2 = c1.iterator();
        while (it2.hasNext()) {
            System.out.println(it2.next());
        }
    }
    
    @Test
    public void test2() {
        // 從集合中移除迭代器返回的最後一個元素（可選操作）
        Iterator it = c1.iterator();
        it.next();
        it.remove();
        System.out.println(c1);
    }
    
    @Test
    public void test3() {
        // 錯誤的寫法，跳著輸出（只打印偶數位置）報 NoSuchElementException 異常
    	System.out.println("===");
        Iterator it = c1.iterator();
        while ((it.next()) != null) { 
        // 直接調.next()方法，取得第1個值：22 ===> 22!=null --> true ===> 進入while{}，再調用.next()方法，打印第2個值：MM
            System.out.println(it.next()); // MM alisha NoSuchElementException
        }
        
        // 錯誤的寫法，無限循環
        // 集合對象每次調用 iterator() 方法，都得到一個"全新"的迭代器對象，默認游標都在集合的第一個元素之前。
        System.out.println("===");
        while (c1.iterator().hasNext()) { // 每當調用 .iterator()，都得到一個"全新"的迭代器對象
            System.out.println(c1.iterator().next()); 
            // 新的迭代器對象，默認游標都在集合的第一個元素之前，調用 next() ---> 永遠只輸出第一個元素
        }
    }

    @Test
    public void test4() {
    	// jdk5.0 新增 foreach 循環，用於遍歷集合、數組
        // 增強for循環，類似 python中的for i in obj:
    	// for( 集合中元素的類型 局部變量： 集合對象)
        System.out.println("增強for循環遍歷集合");
        for (Object o : c1) { // debug --> 內部依然調用迭代器
            System.out.println(o);
        }
        System.out.println("---foreach----");
        c1.forEach(System.out::println);

    }

    @Test
    public void test5() {
        // 增強for遍歷數組
        int[] ia = new int[]{11, 22, 44, 55};
        // for( 數組中元素的類型 局部變量：數組)
        for (int i : ia) {
            System.out.println(i);
        }
    }
    
   
    @SuppressWarnings("unused")
	@Test
    public void test6() {
        
        String[] arr = new String[]{"MM", "MM", "MM"};
        // 方式一：普通 for 賦值
        /*
        for (int i=0; i<arr.length; i++) {
        	arr[i] = "GG";
        }
        */
        // 方式二：增強for遍歷arr，賦值
        // for( 數組中元素的類型 局部變量：數組)
        for(String s : arr) { // 將數組中元素個別取出，賦值給局部變量 s
        	s = "LL"; // 不改變原來數組
        }
        for (int i=0; i<arr.length; i++) {
        	System.out.println(arr[i]);
        }
    }
}
