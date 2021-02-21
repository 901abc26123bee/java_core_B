package com.wong.IO;

import java.io.File;

import org.junit.Test;

public class FileTest {
	@Test
	public void Test() {
		// cnstructor 1
		File file1 = new File("hello.txt"); //relative pth
		File file2 = new File("/Users/wengzhenyuan/Desktop/jee-2020-06/workspace/Java_Advance/src/com/wong/IO/hello.txt\"\n" );
        System.out.println(file1);
        System.out.println(file2);
        System.out.println("--------------------");
        System.out.println(file1.getAbsolutePath());
        System.out.println(file1.getParent());
        System.out.println(file1.lastModified());
	}
}
