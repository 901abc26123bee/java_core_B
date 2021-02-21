package com.wong.collection.pojo;
/*
泛型接口
* */

public interface USB<T> {
    public abstract void start(T t);
    void stop(T t);
}