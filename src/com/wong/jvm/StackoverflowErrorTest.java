package com.wong.jvm;

public class StackoverflowErrorTest {
	private static int count = 1;
    public static void main(String[] args) {
        System.out.println(count++);
        main(args);
    }
}
