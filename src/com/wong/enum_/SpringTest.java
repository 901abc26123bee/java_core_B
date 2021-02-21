package com.wong.enum_;
/*
 * 一、枚舉類使用
 * 1. 枚舉類理解：類的對象只有有限個、確定的。稱之枚舉類
 * 2. 當需要定義一組常量時，強烈建議使用枚舉類
 * 3. 如果枚舉類中只有一個對象，則可以作為單例模式的實現方式
 * 
 * 二、如何定義枚舉類
 * 方式一：jdk5.0 之前，自定義枚舉類
 * 方式二：jdk5.0，可以使用 enum 關鍵字定義枚舉類
 * */
public class SpringTest {
	public static void main(String[] args) {
		Season season = Season.AUTUMN;
		System.out.println(season);
	}
}
// 自定義枚舉類，方式一
class Season{
	
	// 1.聲明對象屬性：private final修飾
	private final String seasonName;
	private final String seasonDesc;
	
	// 2.私有化類的構造器（final修飾需定義時復值）
	private Season(String seasonName, String seasonDesc) {
		this.seasonName = seasonName;
		this.seasonDesc = seasonDesc;
	}
	
	// 3.提供當前枚舉類的多個對象：public static final 的
	public static final Season SPRING = new Season("spring", "flower blossom");
	public static final Season SUMMER = new Season("summer", "ice cream");
	public static final Season AUTUMN = new Season("autumn", "yellow leaf");
	public static final Season WINTER = new Season("winter", "do tou want to build a snowman");
	
	// 4.其他訴求，獲取枚舉對象屬性
	public String getSeasonName() {
		return seasonName;
	}
	public String getSeasonDesc() {
		return seasonDesc;
	}
	
	@Override
	public String toString() {
		return "Season [seasonName=" + seasonName + ", seasonDesc=" + seasonDesc + "]";
	}
}