package com.wong.IO;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

public class BufferedInputStreamAndBufferedOutputTest {
	public void copyFile(String src, String dest) {
		/*
		 * BufferedInputStream, BufferedOutputStream 實現文件的複制
		 */

		// 1. 創建File文件對象
		File f1 = new File(src);
		File f2 = new File(dest);

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			// 2. 創建輸入流、輸出流對象
			FileInputStream fr = new FileInputStream(f1);
			FileOutputStream fw = new FileOutputStream(f2);

			// 3. 創建緩衝輸入流、緩衝輸出流對象
			bis = new BufferedInputStream(fr);
			bos = new BufferedOutputStream(fw);

			byte[] b = new byte[1024]; // 一次讀取1024個字節
			int len;
			// 4. 讀取、寫入
			while ((len = bis.read(b)) != -1) { // 這裡也可以不傳，BufferedInputStream.read()有一個默認長度的字節數組來接受，默認大小為8192
				bos.write(b, 0, len);
				bos.flush(); // 刷新輸出緩衝流，防止有未寫的
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 5. 關閉緩衝流
			if (bos != null)
				try {
					bos.close(); // 會同自動關閉相應的fr流
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (bis != null)
				try {
					bis.close(); // 會同自動關閉相應的fr流
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

	}

	@Test
	public void test1() {
		String s1 = "image.png";
		String s2 = "image3.png";
		copyFile(s1, s2);
	}

}
