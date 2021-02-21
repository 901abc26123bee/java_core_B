package com.wong.jvm;

import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.Scanner;

/*
 * IO				NIO(new IO/ Non-Blocking IO)
 * byte[]/char[]	Buffer
 * Stream			Channel
 * */
public class BufferTest {
	private static final int BUFFER = 1024 * 1024 * 1024; // 1GB

	public static void main(String[] args) {
		/*
		 * public static ByteBuffer allocateDirect(int capacity) { 
		 * 	return new DirectByteBuffer(capacity); 
		 * }
		 */
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(BUFFER);
		System.out.println("直接分配內存完畢");

		Scanner scanner = new Scanner(System.in);
		scanner.next();

		System.out.println("開始釋放內存");
		byteBuffer = null;
		System.gc();
		scanner.next();

	}	
}
