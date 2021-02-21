package com.wong.IO;

/*
字符流
Reader, Writer
只適合操作文本文件
* */



import org.junit.Test;

import java.io.*;

public class FileReaderAndWriterTest {

	
    @Test
    public void test0() {
        /*
        FileReader.read() 一次一個字符讀取文件，返回值為字符個數，讀取到文件末尾時，返回-1
        * */
    	
        File file1 = new File("hello.txt"); //relative pth
        System.out.println("--------------------");
        System.out.println(file1);
        FileReader fr = null;
        try {
            fr = new FileReader(file1);
            int c;
            while ((c = fr.read()) != -1) { // 一次讀取一個字符
                System.out.print((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Test
    public void test1() {
        /*
        File.Reader(char[] c) 一次多個字符從文件讀取，返回值為字符個數，讀取到文件末尾時，返回-1
        * */
        File file1 = new File("hello.txt");

        FileReader fr = null;
        try {
            fr = new FileReader(file1);
            char[] c = new char[32];
            int len;
            while ((len = fr.read(c)) != -1) {
                String s = new String(c, 0, len);
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Test
    public void test2() {
        /*
        * */
        File file = new File("hello.txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            String s = "a hole new worl";
            fw.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void copyFile(String src, String dest) {
        /*
        使用字符流形式 複製文本文件，只適用於復製文本文件
        * */
        File f1 = new File(src);
        File f2 = new File(dest);

        FileReader fr = null;
        FileWriter fw = null;
        try {
            fr = new FileReader(f1);
            fw = new FileWriter(f2);

            char[] c = new char[32];
            int len;
            while ((len = fr.read(c)) != -1) {
                fw.write(c, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test3() {
        String s1 = "hello.txt";
        String s2 = "hello2.txt";

        copyFile(s1, s2);
    }

    @Test
    public void test99() {
        /*
        字符串轉換成 字符數組
        * */
        String s = new String("sea you late.");
        char[] c = new char[s.length()];
        s.getChars(0, s.length(), c, 0);

        for (char cc : c) {
            System.out.println(cc);
        }
        // System.out.println(c);
    }

}