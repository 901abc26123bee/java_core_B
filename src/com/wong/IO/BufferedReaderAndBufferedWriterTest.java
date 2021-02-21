package com.wong.IO;

/*
BufferedReader, BufferedWriter
只適合操作文本文件
BufferedReader.readLine() 讀取的內容不包括換行符
* */

import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedReaderAndBufferedWriterTest {

	public void copyFile(String src, String dest) {
		/*
		 * 利用BufferedReader、BufferedWriter實現文件複製
		 */

		// 1. 創建File對象
		File f1 = new File(src);
		File f2 = new File(dest);

		BufferedReader br = null;
		BufferedWriter bw = null;

		try {
			// 2. 創建字符流對象
			FileReader fr = new FileReader(f1);
			FileWriter fw = new FileWriter(f2);

			// 3.創建緩衝字符流對象
			br = new BufferedReader(fr);
			bw = new BufferedWriter(fw);

			// 4. 讀取、寫入操作
			// 第一種讀取方式，一次讀取多個字符
// char[] c = new char[1024];
// int len;
// while ((len = br.read(c)) != -1) {
// bw.write(c, 0, len);
// bw.flush();
// }

			// 第二種讀取方式，一次讀取一行，內容不包括換行符
			String str = null;
			while ((str = br.readLine()) != null) {
				bw.write(str);
				bw.newLine(); // 新起一行
				bw.flush();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 關閉緩衝流
			if (bw != null)
				try {
					bw.close(); // 同時會自動關閉字符流
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (br != null)
				try {
					br.close(); // 同時會自動關閉字符流
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	@Test
	public void test1() {
		String s1 = "hello.txt";
		String s2 = "hello2.txt";
		copyFile(s1, s2);
	}
}
