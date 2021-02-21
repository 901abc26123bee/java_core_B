package com.wong.exception;

/*
try-catch-finally
* */


import org.junit.Test;

public class FinallyTest {
    public static void main(String[] args) {
        System.out.println(result());
    }

    @Test
    public void test1() {
        int i = result(); // 2
        System.out.println(i);
    }

    @Test
    public void test2() {
        FinallyTest f = new FinallyTest();
        System.out.println("result2()  "+ f.result2());
        // java.lang.ArithmeticException:
        // 我是finally塊
        // result2()  2

    }

    @Test
    public void Test3() {
        System.out.println("result3()  " +result3());
    }

    @SuppressWarnings("finally")
	public static int result() {
        try {
            return 1;
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("我是finally塊");
            return 2;
        }
    }

    @SuppressWarnings("finally")
	public int result2() {
        try {
            System.out.println(5 / 0);
            return 1;
        } catch(Exception e) {
            e.printStackTrace();
            return 3;
        } finally {
            System.out.println("我是finally塊");
            return 2;
        }
    }

    public static int result3() {
        try {
            System.out.println(5 / 0);
            return 1;
        } catch(ArithmeticException e) {
            e.printStackTrace();
// return 3;
        }
        System.out.println("我是finally塊");
        return 2;
    }

}
