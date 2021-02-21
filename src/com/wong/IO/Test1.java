package com.wong.IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

public class Test1 {
	@Test
	public void test1() throws IOException {

		// 1. 創建一個File類對象
		// File file1 = new File("/Users/wengzhenyuan/Desktop/RHEL 8.md");
		File file1 = new File("hello.txt");
		System.out.println(file1);
		// 2.創建一個FileInputStream類的對象
		FileReader fr = new FileReader(file1);
		 
//		int b = fr.read();
//		while (b != -1) {
//			System.out.print((char) b);
//			b = fr.read();
//		}
//		int data;
//		while((data = fr.read()) != -1) {
//			System.out.println((char) data);
//		}
		char[] cbuff = new char[7];
		int len;
		// 錯誤寫法
		// 返回每次讀入 cbuff 數組中的字符個數，如果達到文件末尾，返回 -1
//		while((len = fr.read(cbuff)) != -1) {
//			for(int i=0; i< cbuff.length; i++) {
//				System.out.println(cbuff[i]);
//			}
//		}
		// 正確寫法： 每次打印讀入長度
//		  while((len = fr.read(cbuff)) != -1) {
//		    for(int i=0; i< len; i++) {
//		      System.out.println(cbuff[i]);
//		    }
//		  }
		  while((len = fr.read(cbuff)) != -1) { 
			  String str = new String(cbuff, 0, len);
			  System.out.println(str);
		  }
		System.out.println("--------------");

		// 關閉輸入流
		fr.close();
	}
	
	@Test
	public void test2() {
		// 1. 創建一個File對象，指定要寫入的文件路徑 ---> 指名寫出道的文件
		File file2 = new File("hello.txt");
		
		FileWriter fw = null;
		try {
			
			// 2. 創建一個 FileWriter 的對象，將File的對像作為形參傳給 FileWriter 創建對象構造器中 
			// ---> 用於數據寫出
			// second parameter --> append to the file or 覆蓋 file
			// FileWriter(file2) / FileWriter(file2,flase) --> 文檢存在：覆蓋
			// FileWriter(file2,true) ---> 追加至文件尾
			fw = new FileWriter(file2,true);
			
			// 寫入操作 : 文檢存在：覆蓋，文件不存在：創建 ---> new FileWriter(file2,false);
			fw.write("I have a dream");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 關閉輸出流
			if(fw != null)
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}	
	}
	
	@Test
	public void test3(){
		// 圖片加密操作 
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream("image.png");
			fos = new FileOutputStream("imagesecrete.png");
			
			byte[] buffer = new byte[1024];
			int len;
			while((len = fis.read(buffer)) != -1) {
				// wrong : buffer remain unchange, only change local b
//			for(byte b : buffer) {
//				b = (byte) (b^5);
//			}
				for(int i=0; i<len; i++) {
					buffer[i] = (byte)(buffer[i]^5);
				}
				fos.write(buffer, 0 ,len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fos != null)
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if(fis != null)
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	@Test
	public void test4(){
		// 圖片解密操作 = fis fos 輸入的文件對調
	      // 同一個值,做兩次異或運算＝原本的值 ---> m^n^n = m 
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream("imagesecrete.png");
			fos = new FileOutputStream("imagedecode.png");
			
			byte[] buffer = new byte[1024];
			int len;
			while((len = fis.read(buffer)) != -1) {
				// wrong : buffer remain unchange, only change local b
//			for(byte b : buffer) {
//				b = (byte) (b^5);
//			}
				for(int i=0; i<len; i++) {
					buffer[i] = (byte)(buffer[i]^5);
				}
				fos.write(buffer, 0 ,len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fos != null)
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if(fis != null)
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}
