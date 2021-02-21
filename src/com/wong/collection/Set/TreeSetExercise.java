package com.wong.collection.Set;

import java.util.Comparator;
import java.util.TreeSet;

import org.junit.Test;

import com.wong.collection.pojo.Employee;
import com.wong.collection.pojo.Employee2;
import com.wong.collection.pojo.MyDate;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class TreeSetExercise {
	/*
	 * 題目: 1. 定義一個Employee類， 該類包含：private成員變量name,age,birthday，其中 birthday 為 MyDate
	 * 類的對象； 並為每一個屬性定義 getter, setter 方法； 並重寫 toString 方法輸出 name, age, birthday
	 * MyDate類包含: private成員變量month,day,year；並為每一個屬性定義 getter, setter 方法； 創建該類的 5
	 * 個對象，並把這些對象放入 TreeSet 集合中（下一章：TreeSet 需使用泛型來定義） 分別按以下兩種方式對集合中的元素進行排序，並遍歷輸出：
	 * 1). 使Employee 實現 Comparable 接口，並按 name 排序 2). 創建 TreeSet 時傳入
	 * Comparator對象，按生日日期的先後排序。 提示：Employee類是否需要重寫equals()方法？ MyDate類呢？
	 */

	@Test
	public void test1() {
		TreeSet tset1 = new TreeSet();
		tset1.add(new Employee("Lucy", 19, new MyDate(2000, 1, 3)));
		tset1.add(new Employee("Harry", 18, new MyDate(2001, 12, 3)));
		tset1.add(new Employee("Eric", 23, new MyDate(1996, 6, 1)));
		tset1.add(new Employee("Epson", 15, new MyDate(2004, 10, 1)));
		tset1.add(new Employee("Leida", 11, new MyDate(2008, 1, 3)));

		for (Object o : tset1) {
			System.out.println(o);
		}
		
		System.out.println("=========================");
	}
	
	@Test
	public void test2() {
		Comparator comparator = new Comparator() {
			public int compare(Object o1, Object o2) {
				if (o1 instanceof Employee2 && o2 instanceof Employee2) {
					Employee2 e1 = (Employee2) o1;
					Employee2 e2 = (Employee2) o2;
					int minusYear = e1.getBirthday().getYear() - e2.getBirthday().getYear();
					if(minusYear != 0) {
						return minusYear;
					}
					int minusMonth = e1.getBirthday().getMonth() - e2.getBirthday().getMonth();
					if(minusMonth != 0) {
						return minusMonth;
					}
					return e1.getBirthday().getDay() - e2.getBirthday().getDay();
				}
				return 0;
			}
		};

		TreeSet tset2 = new TreeSet(comparator);
		tset2.add(new Employee2("Lucy", 19, new MyDate(2000, 1, 3)));
		tset2.add(new Employee2("Harry", 18, new MyDate(2001, 12, 3)));
		tset2.add(new Employee2("Eric", 23, new MyDate(1996, 6, 1)));
		tset2.add(new Employee2("Epson", 15, new MyDate(2004, 10, 1)));
		tset2.add(new Employee2("Leida", 11, new MyDate(2008, 1, 3)));
		tset2.add(new Employee2("Linda", 11, new MyDate(2008, 1, 3)));

		for (Object o : tset2) {
			System.out.println(o);
		}

	}

}
