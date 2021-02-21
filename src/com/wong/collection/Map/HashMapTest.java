package com.wong.collection.Map;

/*
 *   
HashMap
Collection接口
Map接口 雙列數據 y=f(x) ---> x不可重複、無序， y 可重複、無序
    |-- HashMap: Map接口的主要實現類，存儲順序由key的hash值決定
      						線程不安全的，效率高，允許使用null鍵和null值
        |-- LinkedHashMap: 保證遍歷 Map 元素時，可以按照添加順序實現遍歷，原因：在原有的 HashMap 底層結構基礎上，添加一對指針，指向前一個和後一個元素。對於頻繁的遍歷操作，效率比 HashMap 高
          
    |-- TreeMap: 保證按照添加進Map中元素的key指定屬性進行排序。所有元素key的對象必須是同一個類，key對象為自定義類對象時要求實現Comparable接口並重寫int compareTo(Object obj)方法
      	底層使用紅黑樹
      
    |-- Hashtable：古老的實現類，線程安全的，效率低，不建議使用
      					不允許使用null鍵和null值 --> NullPointerException
        |-- Properties: 常用來處理屬性文件。健和值都為String類型

		HashMap的底層: 數組 + 鍊錶 (JDK7 before)
          				數組 + 鍊錶 + 紅黑樹 (JDK8)
          
Map:
    - Map 中的 key：無序、不可重複的，使用Set存儲 ---> key 所在的類要重寫 equals() 和 hashCode() --> 以 HashMap 為例
    - Map 中的 value：無序、可以重複，使用Collection存儲 ---> value 所在的類要重寫 equals()
    - 一個鍵值對： Key-Value構成一個 entry 對象
    - Map 中的 entry：無序、不可重複的，使用Set存儲
      
		- put()添加元素到map時，如果前面已經存在一個相同的key，那麼新的key對應的value將覆蓋舊的value
		
		
Map常用方法
### 添加、刪除操作方法
Object put(Object key, Object value) 添加一個元素到HashMap中
Object remove(Object key) 刪除指定key的元素
void putAll(Map t) 把Map t中所有元素添加到當前Map中
void clear() 清除當前map中所有元素
### 元素查詢操作方法
Object get(Object key) 獲取指定key的元素，若key不存在則返回null
boolean containsKey(Object key) 當前map所有的key中是否包含指定key，是返回true,否則false
boolean containsValue(Object value) 當前map所有的value中是否包含指定的value，是返回true,否則false
int size() 返回map元素個數
boolean isEmpty() 當前map是否為空，是返回true,否則false
boolean equals(Object obj) 當前map與指定的obj map是否相等，即所有entry相等
### 元視圖操作方法
Set keySet() 獲取當前map所有的key，值為Set
Collection values() 獲取當前map所有的value，值為Collection
Set entrySet() 獲取當前map所有的entry，值為Set
### Map特點
* Map的key、value都可以為null
* key使用Set存儲，不可重複，value使用Collection存儲，可以重複
* put()添加元素到map時，如果前面已經存在一個相同的key，那麼新的key對應的value將覆蓋舊的value
### HashMap特點
* entry順序存儲順序與key的hash值有關，與put添加的順序無關
* */


import org.junit.Test;

import com.wong.collection.pojo.Person;

import java.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class HashMapTest {
   
	@Test
    public void test1() {
        // Object put(Object key, Object value) 添加一個元素到HashMap中
        HashMap hmap1 = new HashMap();
        hmap1.put("k1", 11);
        hmap1.put("k2", 22);
        System.out.println(hmap1);
        hmap1.put("k2", 33); // 將覆蓋上面的"k2"對應的值＝修改
        hmap1.put(33, 33);
        hmap1.put(null, null);
        hmap1.put(true, 3.14F);
        hmap1.put(new Person("小張", 23), "阿里山人");


        System.out.println(hmap1.size());
        System.out.println(hmap1);

        // Object remove(Object key) 刪除指定key的元素
        hmap1.remove(true);
        System.out.println(hmap1);

        // void putAll(Map t) 把Map中所有元素添加到當前Map中
        HashMap hmap2 = new HashMap();
        hmap2.put("A", "America");
        hmap2.put(86, "Japan");
        hmap1.putAll(hmap2);

        System.out.println(hmap1);

        // void clear() 清除當前map中所有元素
        hmap1.clear();
        System.out.println(hmap1.size());
        System.out.println(hmap1);

    }

    @Test
    public void test2() {
        HashMap hmap1 = new HashMap();
        hmap1.put("k1", 11);
        hmap1.put("k2", 22);
        hmap1.put(33, 33);
        hmap1.put(null, null);
        hmap1.put(true, 3.14F);
        hmap1.put(new Person("小張", 23), "阿里山人");

        // Object get(Object key) 獲取指定key的元素，若key不存在則返回null
        Object obj = hmap1.get("k2");
        System.out.println(obj);
        System.out.println(hmap1.get("kaka"));

        // boolean containsKey(Object key) 當前map所有的key中是否包含指定key，是返回true,否則false
        boolean ckey = hmap1.containsKey("k1");
        System.out.println(ckey);
        System.out.println(hmap1.containsKey("kaka"));

        // boolean containsValue(Object value) 當前map所有的value中是否包含指定的value，是返回true,否則false
        boolean cvalue = hmap1.containsValue(33);
        System.out.println(cvalue);

        // int size() 返回map元素個數
        System.out.println(hmap1.size());

        // boolean isEmpty() 當前map是否為空，是返回true,否則false
        boolean ise = hmap1.isEmpty();
        System.out.println(ise);

        // boolean equals(Object obj) 當前map與指定的obj map是否相等，即所有entry相等
        HashMap hmap2 = new HashMap();
        hmap2.put("k1", 11);
        hmap2.put("k2", 22);
        hmap2.put(33, 33);
        hmap2.put(null, null);
        hmap2.put(true, 3.14F);
        hmap2.put(new Person("小張", 23), "阿里山人");

        boolean eq = hmap2.equals(hmap1);
        System.out.println(eq); // true
    }
    @Test
    public void test3() {
        HashMap hmap1 = new HashMap();
        hmap1.put("k1", 11);
        hmap1.put("k2", 22);
        hmap1.put(33, 33);
        hmap1.put(null, null);
        hmap1.put(true, 3.14F);
        hmap1.put(new Person("小張", 23), "阿里山人");

        System.out.println("-------------------------");
        // Set keySet() 獲取當前map所有的key，值為Set
        Set kset1 = hmap1.keySet();
        System.out.println(kset1);

        // Collection values() 獲取當前map所有的value，值為Collection，順序與keySet()對應
        Collection vs = hmap1.values();
        System.out.println(vs);

        // Set entrySet() 獲取當前map所有的entry，值為Set
        Set entrys = hmap1.entrySet();
        System.out.println(entrys);

        // 遍歷key
        Set kset2 = hmap1.keySet();
        for (Object k : kset2) {
            System.out.println(k);
        }
        System.out.println();

        // 遍歷values
        Collection vs2 = hmap1.values();
        Iterator ite = vs2.iterator();
        while (ite.hasNext()) { // 也可以用增強for循環（foreach）
            System.out.println(ite.next());
        }
        System.out.println();

        // 遍歷 key-value，即遍歷entrys

        // 方式一:通過key遍歷entrys
        Set kset3 = hmap1.keySet();
        for (Object key: kset3) {
            System.out.println(key + "-->" + hmap1.get(key));
        }
        System.out.println();

        // 方式二：通過 entrySet()遍歷
        // entrySet 集合中的元素都是 entry
        Set entryset2 = hmap1.entrySet();
        for (Object o : entryset2) {
            Map.Entry entry = (Map.Entry) o;
            System.out.println(entry.getKey() + "-->" + entry.getValue());
        }

        Iterator it = entryset2.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next(); // entry 直接打印為 key=value
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
       
    }

}