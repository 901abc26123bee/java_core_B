package com.wong.collection.pojo;

import static org.hamcrest.CoreMatchers.nullValue;

/*
模擬DAO，database access object
* */


import java.util.ArrayList;
import java.util.List;

public class DAO<T> {
    private List<T> list = new ArrayList<>();

    // 構造器
    public DAO() {
        super();
    }

    // 方法，CRUD

    public void add(T t) {
        list.add(t);
    }

    public T get(int index) {
        return list.get(index);
    }

    public List<T> getList() {
        return list;
    }

    public void update(int index, T t) {
        list.set(index, t);
    }

    public void delete(int index) {
        list.remove(index);
    }
    // 泛型方法
    public <E> E getValue() {
    	return null;
    }
}
