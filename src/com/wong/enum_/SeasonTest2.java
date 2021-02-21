package com.wong.enum_;

import java.lang.Thread.State;

/*
 * 使用 enum 關鍵字定義枚舉類
 * 定義的枚舉類默認繼承於 class java.lang.Enum
 * 
 * */
public class SeasonTest2 {
	public static void main(String[] args) {
		Season2 season2 = Season2.SPRING;
		System.out.println(season2);
		System.out.println(Season2.class.getSuperclass()); // class java.lang.Enum
		
		Season2[] values = Season2.values();
		for(int i=0; i<values.length; i++) {
			System.out.println(values[i]);
		}
		System.out.println("---------------------");
		State[] states = Thread.State.values(); 
		for(int i=0; i<states.length; i++) {
			System.out.println(states[i]);
		}
		System.out.println("---------------------");
		Season2 winter = Season2.valueOf("WINTER");
		System.out.println(winter);
	}
}

// 方式二：jdk5.0，可以使用 enum 關鍵字定義枚舉類
enum Season2 {
	// 1.提供當前枚舉類的多個對象：
	SPRING("spring", "flower blossom"),
	SUMMER("summer", "ice cream"),
	AUTUMN("autumn", "yellow leaf"),
	WINTER("winter", "do tou want to build a snowman");
	
	// 1.聲明對象屬性：private final修飾
	private final String seasonName;
	private final String seasonDesc;

	// 2.私有化類的構造器（final修飾需定義時復值）
	private Season2(String seasonName, String seasonDesc) {
		this.seasonName = seasonName;
		this.seasonDesc = seasonDesc;
	}

	// 4.其他訴求，獲取枚舉對象屬性
	public String getSeasonName() {
		return seasonName;
	}

	public String getSeasonDesc() {
		return seasonDesc;
	}

}