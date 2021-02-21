package com.wong.jdk9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;




public class TryTest {

	public static void main(String[] args) {
		// in jdk8: you can write in this way

//		InputStreamReader reader = null;
//		try {
//			reader = new InputStreamReader(System.in);
//			char[] cbuf = new char[20];
//			int len;
//			if ((len = reader.read(cbuf)) != -1) {
//				String str = new String(cbuf, 0, len);
//				System.out.println(str);
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			if (reader != null) {
//				try {
//					reader.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
		
		
		
		/**
	     * java 8可以這麼寫
	     */
		// java 8要自動關閉流的話，必須寫在try後的()裡聲明流對象
		// 這時聲明的流對象為final修飾的，不能再修改
		try(InputStreamReader reader = new InputStreamReader(System.in)){
			char[] cbuf = new char[20];
			int len;
			if ((len = reader.read(cbuf)) != -1) {
				String str = new String(cbuf, 0, len);
				System.out.println(str);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	
	/**
     * java 8可以這麼寫
     */
    @Test
    public void test2() {
        File file = new File("a.txt");
        // java 8要自動關閉流的話，必須寫在try後的()裡聲明流對象
        // 這時聲明的流對象為final修飾的，不能再修改
        try (
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr)
        ) {
// br = null; // final為常量了，不能再重新賦值
            String line = "";
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
