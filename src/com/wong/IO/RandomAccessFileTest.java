package com.wong.IO;

/*
RandomAccessFile， 支持文件隨機訪問（即可以跳轉文件的任意位置開始讀取）
* 即可以充當輸入流，又可以充當輸出流，也可以同時充當輸入流、輸出流
* 支持從文件的開頭讀取、寫入
* 支持從文件任意位置讀取、寫入（覆蓋）
* */

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.LinkedList;

public class RandomAccessFileTest {

    public void copyFile(String src, String dest) {
        /*
        RandomAccessFile 實現複製文件
        * */
        File f1 = new File(src),
                f2 = new File(dest);
        RandomAccessFile raf1 = null,
                raf2 = null;
        try {
            raf1 = new RandomAccessFile(f1, "r");
            raf2 = new RandomAccessFile(f2, "rw");

            byte[] b = new byte[1024];
            int len;
            while ((len = raf1.read(b)) != -1) {
                raf2.write(b, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (raf1 != null) {
                    raf1.close();
                }
                if (raf2 != null) {
                    raf2.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void test1() {
        String s1 = "hello.txt",
                s2 = "hello5.t​​xt";
        copyFile(s1, s2);
    }

    @Test
    public void test2() {
        // 在指定位置開始寫入(插入不覆蓋)
        File file = new File("hello.txt");
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(file, "rw");
            // 移動文件指針，保存指針3之後的所有數據到 StringBuilder 中
            raf.seek(3);
            StringBuilder builder = new StringBuilder((int) new File("helo.txt").length());
            byte[] buffer = new byte[20];
            int len;
            while((len=raf.read(buffer)) != -1) {
            	builder.append(new String(buffer, 0, len));
            }
            // 調回指針3，寫入 insert
            raf.seek(3);
            raf.write("insert".getBytes());
            // 繼續將 StringBuilder 中數據寫到文件中
            raf.write(builder.toString().getBytes());
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (raf != null) {
                try {
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }


    @Test
    public void test3() {
        // 實現插入效果，在3後插入"--"，適合單行情況
        RandomAccessFile raf = null;
        try {
            String filePath = "hello.txt";
            raf = new RandomAccessFile(filePath, "rw");
            //System.out.println("默認文件指針: " raf.getFilePointer());
            raf.seek(4);
            // 把要插入位置後面的內容先保存起來，這時候調用raf.readLine()後，文件指針移到最後去了。
            //System.out.println("文件指針：" raf.getFilePointer());
            String s = raf.readLine();
// System.out.println("操作後文件指針" raf.getFilePointer());
            // 再把文件指針移動到要插入的位置
            raf.seek(4);
            raf.write("--".getBytes());
            raf.write(s.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void fileInsert(String filePath, String s, long lon) {
        // 在指定位置插入內容
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(filePath, "rw");
            raf.seek(lon);

            // 方法1
// byte[] b = new byte[1024];
// int len;
// StringBuffer sb = new StringBuffer();
// while ((len = raf.read(b)) != -1) {
// sb.append(new String(b, 0, len));
// }
// raf.seek(lon);
// raf.write(s.getBytes());
// raf.write(sb.toString().getBytes());

            // 方法2
            // 思路：把插入點後面的內容先存放起來，插入完內容後，再把存放起來的內容添加上來
            byte[] b = new byte[1024];
            int len;
            LinkedList<Byte> list = new LinkedList<>();
            while ((len = raf.read(b)) != -1) {
                for (int i = 0; i < len; i++) {
                    list.add(b[i]);
                }
            }
            raf.seek(lon);
            raf.write(s.getBytes());
            for (Byte by : list) {
                raf.write(by);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (raf != null) {
                    raf.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test5() {
        String filePath = "hello.txt";
        String s = "我是插入的內容";
        fileInsert(filePath, s, 4);
    }
}

