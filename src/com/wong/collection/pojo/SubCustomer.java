package com.wong.collection.pojo;

import java.util.ArrayList;
import java.util.List;

public class SubCustomer extends Customer<Integer> { // 這裡SubCustomer就不為泛型了，已經指定確定的類型
	//構造器
    public SubCustomer() {
        super();
    }

    public SubCustomer(long id, String name) {
        super(id, name);
    }
    // 方法
    public String toString() {
        return "SubCustomer2{ " +
                super.toString() +
                " }";
    }
    public static<E> List<E> copyFromArrayTolist(E[] arr){
        ArrayList<E> list = new ArrayList<>();
      	for(E e : arr){
          	list.add(e);
        }
      	return list;
    }
}
