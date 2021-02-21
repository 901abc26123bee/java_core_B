package com.wong.jvm;
/**
 * 棧上分配 close
 * -Xmx1G -Xms1G -XX:-DoEscapeAnalysis -XX:+PrintGCDetails
 * 
 * 棧上分配 on
 * -Xmx1G -Xms1G -XX:+DoEscapeAnalysis -XX:+PrintGCDetails
 */
class User {
    private String name;
    private String age;
    private String gender;
    private String phone;
}
public class StackAllocation {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            alloc();
        }
        long end = System.currentTimeMillis();
        System.out.println("花費的時間為：" + (end - start) + " ms");

        // 為了方便查看堆內存中對像個數，線程sleep
        Thread.sleep(10000000);
    }

    private static void alloc() {
        User user = new User();
    }
}