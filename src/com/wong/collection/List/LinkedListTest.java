package com.wong.collection.List;

/*
LinkedList
## 基於Collectoin新增方法
boolean add(E e) // 在最後一個位置插入一個元素，成功則返回true,否則返回false
void add(int index, E element) // 在指定位置插入給定元素
void addFirst(E e) // 在第一個位置插入一個元素
void addLast(E e) // 在最後一個位置插入一個元素
E remove(int index) // 移除指定下標的元素，並返回該元素
E removeFirst() // 移除第一個元素，並返回該元素
E removeLast() // 移除最後一個元素，並返回該元素
E set(int index, E element) // 更新指定位置的元素，並返回該元素(未更新之前的)
E get(int index) // 獲取下標元素
E getFirst() // 獲取第一個元素
E getLast() // 獲取最後一個元素
Object clone() // 深度克隆當前集合，但集合元素的內部並未克隆，Returns a shallow copy of this {@code LinkedList}. (The elements themselves are not cloned.)
* */


import org.junit.Test;


import java.util.Date;
import java.util.LinkedList;


@SuppressWarnings({ "unchecked", "rawtypes" })
public class LinkedListTest {
    @Test
    public void test1() {
        LinkedList lis1 = new LinkedList();
        lis1.add(11);
        lis1.add(22);
        lis1.add(33);
        lis1.add("AA");
        lis1.add("BB");
        lis1.add(true);
        lis1.add(new Date());
        lis1.addLast(null);

        System.out.println(lis1);

        // E get(int index) // 獲取下標元素
        System.out.println(lis1.get(3));

        // E getFirst() // 獲取第一個元素
        System.out.println(lis1.getFirst());

        // E getLast() // 獲取最後一個元素
        System.out.println(lis1.getLast());

        // void add(int index, E element) // 在指定位置插入給定元素
        lis1.add(2, "我要插入下標2的位子");
        System.out.println(lis1);

        // void addFirst(E e) // 在第一個位置插入一個元素
        lis1.addFirst("我要插隊到第一個");

        // void addLast(E e) // 在最後一個位置插入一個元素
        lis1.addLast("我往最後插入");

        System.out.println(lis1);
        

        // E remove(int index) // 移除指定下標的元素
        System.out.println(lis1.remove(8));
        System.out.println(lis1);
        // E removeFirst() // 移除第一個元素
        System.out.println(lis1.removeFirst());
        System.out.println(lis1);
        // E removeLast() // 移除最後一個元素
        System.out.println(lis1.removeLast());
        System.out.println(lis1);
    }

    @Test
    public void test2() {
        LinkedList lis1 = new LinkedList();
        lis1.add(11);
        lis1.add(22);
        lis1.add(33);
        lis1.add("AA");
        lis1.add(true);
        lis1.add(new Date());
        lis1.addLast(new int[3]);

        System.out.println(lis1);
        // E set(int index, E element) // 更新指定位置的元素，並返回該元素
        System.out.println(lis1.set(4,"balabala"));
        System.out.println(lis1);

        // Object clone() // 深度克隆當前集合，但集合元素的內部並未克隆
        LinkedList lis2 = (LinkedList) lis1.clone();
        System.out.println("lis1 hashCode=" + lis1.hashCode());
        System.out.println("lis2 hashCode=" + lis2.hashCode());
        System.out.println(lis1.equals(lis2));
        System.out.println(lis1.set(0, "MM"));
        System.out.println(lis1);
        System.out.println(lis2);
        System.out.println("lis1:");
        for (int i: (int[]) lis1.getLast()) {
            System.out.println(i);
        }

        System.out.println("lis2: ");
        for (int i: (int[]) lis2.getLast()) {
            System.out.println(i);
        }

        int[] iarr = (int[]) lis1.getLast();
        iarr[0] = 999;
        for (int i: (int[]) lis1.getLast()) {
            System.out.println(i);
        }

        System.out.println("lis2: ");
        for (int i: (int[]) lis2.getLast()) {
            System.out.println(i);
        }
    }
}
