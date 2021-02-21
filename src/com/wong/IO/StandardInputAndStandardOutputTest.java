package com.wong.IO;

/*
標準輸入、輸出流 System.in, System.out，都為字節流
題目：
    從鍵盤輸入字符串，要求將讀取到的整行字符串轉成大寫輸出。
然後繼續進行輸入操作，直至當輸入“e”或者“exit”時，退出程序。
* */


import org.junit.Test;

import java.io.*;

public class StandardInputAndStandardOutputTest {
    @Test
    public void test1() {
        BufferedReader br = null;

        try {
            // 接受標準輸入流
            // InputStream is = System.in;
            // 輸入流字節流 轉 字符流
            InputStreamReader isr = new InputStreamReader(System.in);

            br = new BufferedReader(isr);

            while (true) {
                System.out.println("請從輸入任意字符串（e或exit退出）：");
                String s = br.readLine();
                if ("e".equalsIgnoreCase(s) || "exit".equalsIgnoreCase(s)) {
                	System.out.println("exit the program");
                    break;
                }
                String s2 = s.toUpperCase();
                System.out.println(s2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
