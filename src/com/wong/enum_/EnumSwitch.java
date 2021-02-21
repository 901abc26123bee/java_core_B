package com.wong.enum_;

/*
把枚舉類作為switch的表達式，枚舉類實例對象名字作為case子句值
* */

public class EnumSwitch {
	public static void main(String[] args) {
		enumSwitch(Season2.AUTUMN);
		enumSwitch(Season2.SUMMER);
	}

	public static void enumSwitch(Season2 s) {
		switch (s) {
		case SPRING:
			System.out.println("我是春節");
			break;
		case SUMMER:
			System.out.println("我是夏季");
			break;
		case AUTUMN:
			System.out.println("我是秋季");
			break;
		case WINTER:
			System.out.println("我是冬季");
			break;
		default:
			System.out.println("Season2 枚舉類中無此對象");
		}

	}

}
