package com.wong.IO;

/*
InputStreamReader, OutputStreamWriter 轉換流
作用：實現 字節流與字符流的相互轉換
InputStreamReader: 字節流 --> 字符流，解碼
OutputerStreamWriter: 字符流 --> 字節流，編碼
適用於需要同時操作到 字節流 和 字符流
* */


import org.junit.Test;


import java.io.*;


public class InputStreamReaderAndOutputStreamWriterTest {
    public void copyFile(String src, String dest) {
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            // 解碼
            File f1 = new File(src);
            FileInputStream fis = new FileInputStream(f1);
            // 字節流 轉 字符流，轉換時要指定字符集 --> 使用哪個字符集，根據要讀取的文件保存時所用的字符集
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            br = new BufferedReader(isr);

            // 編碼，這裡到著看比較容易理解，從bw.write(str)往上看
            File f2 = new File(dest);
            FileOutputStream fos = new FileOutputStream(f2);
            // 字符流 轉 字節流，轉換時要指定字符集
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            // 字符寫(輸出)緩衝流
            bw = new BufferedWriter(osw);
            String str = null;
            while ((str = br.readLine()) != null) {
                bw.write(str);
                bw.newLine();
                bw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void test1() {
        String s1 = "hello.txt";
        String s2 = "hello3.txt";
        copyFile(s1, s2);
    }
    
    @Test
    public void test2() throws IOException {
    	File file1 = new File("hello.txt");
    	File file2 = new File("hello_gbk.txt");
    	
    	FileInputStream fis = new FileInputStream(file1);
    	FileOutputStream fos = new FileOutputStream(file2);
    	
    	InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
    	OutputStreamWriter osw = new OutputStreamWriter(fos, "gbk");
    	
    	char[] cbuf = new char[20];
    	int len;
    	while((len = isr.read(cbuf)) != -1) {
    		osw.write(cbuf, 0 ,len);
    	}
    	
    	isr.close();
    	osw.close();
    }
    
}
