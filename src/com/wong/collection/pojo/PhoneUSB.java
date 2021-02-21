package com.wong.collection.pojo;

public class PhoneUSB<T> implements USB<T> { // 也可是指定泛型的類型，如：public class PhoneUSB implements USB<String> { }
    @Override
    public void start(T t) {
        System.out.printf("啟動手機USB連接，使用技術：%s\n", t);
    }

    @Override
    public void stop(T t) {
        System.out.printf("關閉手機USB連接，使用技術：%s\n", t);
    }
}
