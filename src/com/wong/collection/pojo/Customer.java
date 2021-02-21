package com.wong.collection.pojo;

/*
自定義泛型類
* 構造器中不能使用泛型
* */



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Customer<T> {
    private long id;
    private String name;
    private T t;
    private List<T> list = new ArrayList<>();

    // 構造器
    public Customer() {
        super();
    }

    /*
    構造器不能使用泛型，錯誤寫法
    public Customer<T>(long id, String name) {
        this.id = id;
        this.name = name;
    }
    */

    public Customer(long id, String name) {
        this.id = id;
        this.name = name;
    }

    // 方法
    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public List<T> getList() {
        return list;
    }

    public void add(T t) {
        list.add(t);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
/*
        泛型錯誤用法
        try {
            System.out.println("test");
        } catch (T e) { // 泛型不能用在catch中
        }
*/

        this.name = name;
    }
   /*
   靜態方法中不能使用泛型
    public static void show(T t) {
    }
*/
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", t=" + t +
                ", list=" + list +
                '}';
    }

    // 聲明泛型方法
    public <E> List<E> arrayToList( E[] e) { // e不能為null
        /*
        數組轉換成List並返回
        * */
        List<E> list = Arrays.asList(e);
        return list;
    }

    // 泛型方法
    public <F> void showSelf(F f) {
        System.out.println(f.toString());
    }
}




