package com.wong.collection.pojo;

public class SubCustomer2<T> extends Customer<T> { // SubCustomer2仍為泛型
    //構造器
    public SubCustomer2() {
        super();
    }

    public SubCustomer2(long id, String name) {
        super(id, name);
    }
    // 方法
    public String toString() {
        return "SubCustomer2{ " +
                super.toString() +
                " }";
    }
}