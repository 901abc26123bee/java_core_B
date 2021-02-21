package com.wong.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.junit.Test;
/*
Collections：操作集合的工具類
可以操作List、Set、Map等的工具類
## 排序操作
reverse(List l) 反轉List l中元素的順序
shuffle(List l) 對List l集合元素進行隨機排序
sort(List l) 根據元素的自然順序對指定List l集合元素按升序排序
sort(List l, Comparator c) 根據指定的Comparator c生產的順序對List l集合元素進行排序
swap(List l, int i, int j) 將指定List l集合中i與j處的元素進行交換
## 查找、替換
Object max(Collection c) 根據元素的自然順序，返回給定集合中位置最大的元素
Object max(Collection c, Comparator com) 根據Comparator com指定的順序，返回給定集合中位置最大的元素
Object min(Collection) 根據元素的自然順序返，回給定集合中位置最小的元素
Object min(Collection c, Comparator com) 根據Comparator com指定的順序，返回給定集合中位置最小的元素
int frequency(Collection c, Object o) 返回指定命令中指定元素出現的次數
void copy(List dest, List src) 將List src中的元素複製到 List dest中，dest的長度必須>= src的長度
boolean replaceAll(List l, Object oldVal, Object newVal) 使用新值newVal替換List l中所舊值oldVal
## 同步控制，可以解決多線程並發訪問集合時的線程安全問題
static<T> Collection<> synchronizedList(Collection<T> c)
static<T> List<> synchronizedList(List<T> l)
static<T> Map<K,V> synchronizedList(Map<K,V> m)
static<T> Set<T> synchronizedList(Set<T> s)
static<T> SortedMap<K,V> synchronizedList(SortedMap<K,V> m)
static<T> SortedSet<T> synchronizedList(SortedSet<T> s)
* */


@SuppressWarnings({ "rawtypes", "unchecked" })
public class Collections_Test {
    
	@Test
    public void test1() {
        ArrayList lis1 = new ArrayList();
        lis1.add(33);
        lis1.add(22);
        lis1.add(44);
        lis1.add(11);
        lis1.add(55);

        System.out.println(lis1);

        // reverse(List l) 反轉List l中元素的順序
        Collections.reverse(lis1);
        System.out.println(lis1);

        // sort(List l) 根據元素的自然順序對指定List l集合元素按升序排序
        Collections.sort(lis1);
        System.out.println(lis1);

        // sort(List l, Comparator c) 根據指定的Comparator c生產的順序對List l集合元素進行排序
        Comparator comparator = new getComparator();
        Collections.sort(lis1, comparator);
        System.out.println("定制排序：" + lis1);


        // swap(List l, int i, int j) 將指定List l集合中i與j處的元素進行交換
        Collections.swap(lis1, 0, 2);
        System.out.println(lis1);

        // shuffle(List l) 對List l集合元素進行隨機排序
        Collections.shuffle(lis1);
        System.out.println(lis1);

    }

    @Test
    public void test2() throws Exception{
        LinkedList lis1 = new LinkedList();
        lis1.add(33);
        lis1.add(22);
        lis1.add(44);
        lis1.add(22);
        lis1.add(11);
        lis1.add(55);
        System.out.println(lis1);

        // Object max(Collection c) 根據元素的自然順序，返回給定集合中位置最大的元素
        Object i100 = Collections.max(lis1);
        System.out.println("max1: " + i100);

        // Object max(Collection c, Comparator com) 根據Comparator com指定的順序，返回給定集合中位置最大的元素
        Comparator com1 = new getComparator();
        Object max2 = Collections.max(lis1, com1);
        System.out.println("max2: " + max2);
        System.out.println(lis1);

        // Object min(Collection) 根據元素的自然順序返，回給定集合中位置最小的元素
        System.out.println(Collections.min(lis1));


        // Object min(Collection c, Comparator com) 根據Comparator com指定的順序，返回給定集合中位置最小的元素
        System.out.println(Collections.min(lis1, new getComparator()));

        // int frequency(Collection c, Object o) 返回指定命令中指定元素出現的次數
        System.out.println(Collections.frequency(lis1, 22));

        // void copy(List dest, List src) 將List src中的元素複製到 List dest中
        List dest = Arrays.asList(new Object[lis1.size()]);
        System.out.println(dest);
        System.out.println(dest.size());
// List lis3 = new ArrayList(); // 複製到此List中時報IndexOutOfBoundsException
        Collections.copy(dest, lis1);
        System.out.println("lis3: " + dest);
        

        List lis2 = new ArrayList();
        lis2.add(88);
        lis2.add(77);
        lis2.add(55);
        Collections.copy(lis1, lis2); // 會覆蓋lis1中一些元素
        System.out.println(lis1);

        // boolean replaceAll(List l, Object oldVal, Object newVal) 使用新值newVal替換List l中所舊值oldVal
        boolean status = Collections.replaceAll(lis1, 55, 115);
        if (status) {
            System.out.println("更新成功");
            System.out.println(lis1);
        } else {
            System.out.println("更新失敗");
        }

        HashMap hmap1 = new HashMap();
        hmap1.put("k1", 11);
        hmap1.put("k2", 13);
        hmap1.put("k3", 12);
        hmap1.put("k4", 11);
        System.out.println(Collections.frequency(hmap1.values(), 11));

    }

    @Test
    public void test3() {
        // 同步控制
        // synchronizedXxx() 方法，線程安全
        Map hmap1 = Collections.synchronizedMap(new HashMap());
        hmap1.put("k1", 11);
        hmap1.put("k2", 13);
        hmap1.put("k3", 12);
        hmap1.put("k4", 11);

        Set set1 = Collections.synchronizedSet(new TreeSet());
        set1.add(11);
        set1.add(22);
        set1.add(44);
        set1.add(33);

        System.out.println(set1);

    }

}

class getComparator implements Comparator<Object> {
    // 構造器
    public getComparator() {
        super();
    }

    // 方法
    @Override
    public int compare(Object o1, Object o2) {
        if (o1 instanceof Integer && o2 instanceof Integer) {
            Integer i1 = (Integer) o1;
            Integer i2 = (Integer) o2;
            int i = -i1.compareTo(i2); // 降序排序
            return i;
        }
        return 0;
    }
}

