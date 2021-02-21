package com.wong.jvm;

public class StackFrameTest {
	public static void main(String[] args) {
        try {
            StackFrameTest test = new StackFrameTest();
            test.method1();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("main()正常結束");
    }
    public void method1(){
        System.out.println("method1()開始執行...");
        method2();
        System.out.println("method1()執行結束...");
//        System.out.println(10 / 0);
//        return ;//可以省略
    }
    public int method2() {
        System.out.println("method2()開始執行...");
        int i = 10;
        int m = (int) method3();
        System.out.println("method2()即將結束...");
        return i + m;
    }
    public double method3() {
        System.out.println("method3()開始執行...");
        double j = 20.0;
        System.out.println("method3()即將結束...");
        return j;
    }
}
