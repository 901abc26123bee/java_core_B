package com.wong.collection.List;

/*
List接口
* 在集合Collection類的基礎上添加下面的方法：
Object get(int index) // 獲取指定下標元素
void add(int index, Object ele) // 指定下標插入一個元素
boolean addAll(int index, Collection eles) // 把一個集合所有元素插入指定下標處
Object remove(int index) // 移除指定下標的元素
Object set(int index, Object ele) // 重置指定下標元素值(更新)
int indexOf(Object obj) // 給定對象首次出現的下標位置，沒有則返回 -1
int lastIndexOf(Object obj) // 給定對象最後一次出現的下標位置，沒有則返回 -1
List subList(int fromIndex, int toIndex) // List切片處理，截取[開始下標，結束下標)為新的List，注意是左閉右開，相當於取一個子集
* List常用方法
    - 增 add(Object obj)
    - 刪 remove(int index)
    - 改 set(int index, Object obj)
    - 查 get(int index)
    - 插 add(int index, Object obj)
    - 長度 size()
* */


import org.junit.Test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SuppressWarnings({ "unchecked", "rawtypes" })
public class ListTest {
    @SuppressWarnings("deprecation")
	@Test
    public void test1() {
        List list = new ArrayList(); // 底層創建了長度為 10 的 Object[] 數組 : elementData
        list.add(11); // elemantData[0] = new Integer(11);
        list.add(22);
        list.add(33);
        list.add(44);
        // ..add
        System.out.println(list);

        // Object get(int index)
        System.out.println(list.get(2));

        // void add(int index, Object ele)
        list.add(1, 22.22);
        System.out.println(list);

        // boolean addAll(int index, Collection eles)，插入成功返回true,否則返回false
        List lis2 = new ArrayList();
        lis2.add(88);
        lis2.add(99);
        list.addAll(1, lis2);
        System.out.println(list);

        // Object remove(int index)
        list.remove(0);
        System.out.println("remove by index --> "+list);
        // if you want to remove by a specific element rather than index
        list.remove(new Integer(33));
        System.out.println("remove by element --> "+list);

        // Object set(int index, Object ele)
        list.set(0, 777);
        System.out.println(list);

        // int indexOf(Object obj)
        System.out.println(list.indexOf(44));

        // int lastIndexOf(Object obj)
        list.add(22); // 在最後一個下標後添加一個元素
        System.out.println(list.lastIndexOf(22));

        // List subList(int fromIndex, int toIndex)
        System.out.println(list.subList(1, 3));

        System.out.println(list.size());
        List list2 = Arrays.asList(1,4,6);
        list.addAll(list2);
        System.out.println("addAll ---> "+list.size());
        list.add(list2);
        System.out.println("add ---> "+list.size());
    }

}

