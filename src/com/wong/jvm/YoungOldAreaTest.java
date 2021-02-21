package com.wong.jvm;

//測試：大對象直接進入老年代
//-Xms60m -Xmx60m -XX:NewRatio=2 -XX:SurvivorRatio=8 -XX:+PrintGCDetails
//E		s0		s1		old
//16	 	2		 	2			40
public class YoungOldAreaTest {
	public static void main(String[] args) {
		byte[] buffer = new byte[1024 * 1024 * 20]; // 20m
	}
}
