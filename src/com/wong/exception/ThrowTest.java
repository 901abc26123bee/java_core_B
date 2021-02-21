package com.wong.exception;

/*
手動拋出異常
* 格式：throw 異常類的對象，如：throw new RuntimeException("類型不一樣");
* throw 語句行後不能有其他語句。
* 拋出的異常類型，若是RuntimeException 可以不用顯式的處理
* 若是Exception異常，必須顯式的處理，因為Exception異常包含了編譯時異常
* */

public class ThrowTest {
    @SuppressWarnings("deprecation")
	public static void main(String[] args) {
        Circle c1 = new Circle(3.1);
        Circle c2 = new Circle(3.1);
        System.out.println(c1.compareTo(c2));
        System.out.println(c1.compareTo(new Integer(3)));

    }
}

class Circle {
    // 實例變量
    private double radius;

    // 構造器
    public Circle() {
        super();
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    // 方法
    public int compareTo(Object obj) {
        /*
        0: 相等
        1: 當前對像大於obj
        -1:當前對像小於obj
        * */

        if (this == obj) {
            return 0;
        }
        if (obj == null) {
            throw new RuntimeException("比較的對像不能為null");
        }
        if (this.getClass() != obj.getClass()) {
            throw new RuntimeException("類型不一樣"); // 手動拋出異常
        }
        if (obj instanceof Circle) {
            Circle o = (Circle) obj;
            if (radius > o.radius) {
                return 1;
            } else if (radius == o.radius) {
                return 0;
            } else {
                return -1;
            }
        } else {
// return -2;
            throw new RuntimeException("類型不一樣"); // 手動拋出異常對象
        }
    }
}