package com.wong.IO;

/*
數據流
DataInputStream, DataOutputStream
適用場景：用來處理基本數據類型、String、字節數據
* */


import org.junit.Test;

import java.io.*;

public class DataInputStreamAndDataOutputStreamTest {

    public void stringToFile(String filePath, String s) {
    // DataOutputStreat 把字符寫入文件
        DataOutputStream dos = null;
        try {
            File file = new File(filePath);
            FileOutputStream fos = new FileOutputStream(file);
            dos = new DataOutputStream(fos);
            dos.writeUTF(s);
            dos.writeBoolean(true);
            dos.writeLong(363910625);
            dos.writeFloat(3.14F);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dos != null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test1() {
        String s = "BAT的智慧城市夢，該醒醒了. 技術標準未統一，政策執行不給力";
        String file = "E:\\dev\\java_2019\\day15\\testLab\\lab1\\data.txt";
        stringToFile(file, s);
    }

    public void fileToConsole(String filePath) {
        // DataInputStream 讀取文件到控制台
        DataInputStream dis = null;
        try {
            FileInputStream fis = new FileInputStream(filePath);
            dis = new DataInputStream(fis);

            // 轉成字符串，非字符串的將打印亂碼
// byte[] b = new byte[1024];
// int len;
// while ((len = dis.read(b)) != -1) {
// System.out.println(new String(b, 0, len));
// }

            // 怎麼寫就怎麼讀，讀寫順序不一樣會報異常
            System.out.println(dis.readUTF());
            System.out.println(dis.readBoolean());
            System.out.println(dis.readLong());
            System.out.println(dis.readFloat());


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dis != null) {
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Test
    public void test2() {
        String f = "hello.txt";
        fileToConsole(f);
    }
}
