package com.wong.IO;

/*
FileInputStream, FileOutputStream
## 英文字符一個字節，中文字符兩個字節，UTF-8編碼的字符有1-3個字節，第一個字節會標識長度

## IO流的分類
按操作數據單位分類：字節流bytes stream(8 bit)、字符流character stream(16 bit)
按數據流的方向分類：輸入流、輸出流
按流的角色分類：節點流、處理流

## IO的類體系
流方向 			抽象基類 			節點流(文件流)實現類 				緩衝流(處理流的一種，可以提升效率) 			其他
字節流.輸入 		InputStream 	FileInputStream(read()是阻塞的) 	BufferedInputStream (read()非阻塞的)		BufferedInputStream
字節流.輸出 		OutputStream 	FileOutputStream 				BufferedOutputStream 					BufferedOutputStream.flush() 每次寫入後執行一次，保證最後的緩衝內容也被寫入
字符流.輸入 		Reader 			FileReader 						BufferedReader 							BufferedReader.readLine() 讀取的內容不包括行尾的換行符
字符流.輸出 		Writer 			FileWriter 						BufferedWriter 							BufferedWriter.flush() 每次寫入後執行一次，保證最後的緩衝內容也被寫入

FileInputStream 從硬盤讀取文件到程序(內存)
FileInputStream.read() 是阻塞的， int read(byte[] b)
BufferedInputStream.read() 非阻塞的

FileOutputStream：void write(byte[], int startIndex, int endIndex);
FileReader: int read(char[] c)
FileWriter: void write(char[], int startIndex, int endIndex)
* */


import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class
FileInputStreamAndFileOutStreamTest {
    @Test
    public void test1() {
        /*
        FileInputStream 從文件一次一個字節的讀取
        FileInputStream.read()，讀取一個字節，返回值為字節個數，到文件末尾時返回-1
        不能讀取含中文的文本，可能會有亂碼，轉為字節數與字符數不好匹配,可以讀取英文字符及非文本的文件
        * */

// File file1 = new File("E:\\dev\\java_2019\\day15\\testLab\\lab1\\QinyuanSpring.Snow.txt");
        // 1. 創建一個File類對象
        File file1 = new File("hello.txt");

        FileInputStream fis = null;
        try {
            // 2.創建一個FileInputStream類的對象
            fis = new FileInputStream(file1);
            /*
            int b = fis.read();
            while (b != -1) {
                System.out.print((char) b);
                b =fis.read();
            }
            */
            // 上面代碼片段可簡寫如下
            // 3. 調用FileInputStream對象read()方法，該方法調用一次返回一個字節(對應一個int值)，當讀取文件結尾時，返回-1
            for (int b; (b = fis.read()) != -1; ) {
                System.out.print((char) b);
            }

            System.out.println("--------------");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    // 關閉輸入流
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Test
    public void test2() {
        /*
        FileInputStream 從文件一次多個字節讀取，
        FileInputStream.read(byte[] b)，一次讀取個數為b長度，返回值為字節個數，到文件末尾時返回-1
        不能讀取含中文的文本，可以讀取英文字符及非文本的文件
        * */

// File file1 = new File("E:\\dev\\java_2019\\day15\\testLab\\lab1\\abc.txt");
        File file1 = new File("hello.txt");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file1);
            byte[] b = new byte[24]; // 讀取一個大文件，這裡會有一個最優值
            int len;
            while ((len = fis.read(b)) != -1) {
                for (int i = 0; i < len; ++i) { 
                	// 這裡的測試條件不能用b.length，因為到最後一次讀取的時候，有可能字節數是不夠填滿b這個字節數組的，這時候就會打印上一次填充的一部分內容
                    System.out.print((char) b[i]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Test
    public void test3() {
        /*
        FileOutputStream 輸出字節流到文件
        fos.write()，右文件存在則覆蓋
         * */


        // 1. 創建一個File對象，指定要寫入的文件路徑
        File file = new File("hello.txt");

        FileOutputStream fos = null;
        try {
            // 2. 創建一個FileOutputStream的對象，將File的對像作為形參傳給FileOutputStream創建對象構造器中
            fos = new FileOutputStream(file);

            // 3. 要寫入的字符串轉成 字節數組
            byte[] b = "I have a good idea!哈哈" .getBytes();

            // 寫入操作
            fos.write(b); // <==> fos.write(b, 0, b.length)
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 關閉輸出流
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Test
    public void test4() {
        /*
        複製文件操作，從硬盤讀取一個文件，並寫入到另外一個文件
        * */
        File file1 = new File("image.png");
        File file2 = new File("image2.png");

        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(file1);
            fos = new FileOutputStream(file2);
            byte[] b = new byte[24];
            int len;
            while ((len = fis.read(b)) != -1) {
                fos.write(b, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public boolean copyFile(String src, String dest) {
        /*
        複製文件，所有類型的文件
        src: 源文件路徑
        dest: 目標文件路徑
        * */
        File f1 = new File(src);
        File f2 = new File(dest);

        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(f1);
            fos = new FileOutputStream(f2);
            byte[] b = new byte[24];
            int len;
            while ((len = fis.read(b)) != -1) {
                fos.write(b, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        boolean bool = false;
        if (f1.length() == f2.length()) {
            bool = true;
        }
        return bool;
    }

    @Test
    public void test5() {
        String s1 = ".mp3";
        String s2 = ".mp3";
        boolean b = copyFile(s1, s2);
        System.out.println(b);
    }
}