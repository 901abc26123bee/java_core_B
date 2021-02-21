package com.wong.exception;


import java.util.ArrayList;
import java.util.List;

public class ErrorTest2 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
//        for (int i = 0; ;++i) {
//            list.add(i);
//        }
        // OutOfMemoryError: Java heap space
        long[] lon = new long[1024 * 1024 * 1024];
    }
}
