package com.wong.jdk9;

/**
 * java 9 接口改進
 *
 * 抽像類、接口異同
 * 異： *二者的定義
 * * 聲明的方式
 * * 內部的結構（java 7, java 8, java 9分別說明）
 * * 抽像類單重繼承，接口可以多重繼續
 * 同：都不能實例化，以多態的方式使用
 *
 *
 *
 */
public interface MyInterface {
    // java 7: 只能聲明全局常量(public static final修飾) 和抽象方法(public abstract默認修飾)
    void methodAbstract();

    // java 8: 可以聲明默認方法、靜態方法
    default void methodDefault() {
        System.out.println("MyInterface 默認方法");
        methodPrivate();
    }

    static void methodStatic() {
        System.out.println("MyInterface static方法");
    }

    // java 9:​​ 可以聲明私有方法
    private void methodPrivate() {
        System.out.println("MyInterface private方法");
    }

}