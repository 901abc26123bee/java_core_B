package com.wong.annotation;


import java.util.ArrayList;
import java.util.List;

public class AnnotationTest {
	public static void main(String[] args) {
		Student s1 = new Student("Linda", 22);
		s1.walk();
		s1.show();

		Person p1 = new Person("小八路", 18);

		// "rawtypes" = didn't specify geeneratic type <E>
		@SuppressWarnings({ "rawtypes", "unused" })
		List list = new ArrayList();

		@SuppressWarnings({ "unused" })
		boolean b = true;

	}
}

@Deprecated
class Person {
	protected String name;
	protected int age;

	// 構造器
	public Person() {
		super();
	}

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	// 方法
	public void eat() {
		System.out.println("人吃東西");
	}

	public void walk() {
		System.out.println("人走路");
	}

	@Deprecated
	public void show() {
		System.out.println("name: " + name + ", age: " + age);
	}

}

@MyAnnotation(value = { "well" })
// before jdk8
// @MyAnnotation2({@MyAnnotation(value = { "1" }), @MyAnnotation(value = { "2" })})

// after jdk8
@MyAnnotation(value = { "a" })
@MyAnnotation(value = { "f" })
class Student extends Person {

	// 構造器
	public Student() {
		super();
	}

	public Student(String name, int age) {
		super(name, age);
	}

	// 方法
	/*
	 * // 快速發現有錯誤的地方
	 * 
	 * @Override public void walk(String how) { }
	 */

	@Override
	public void walk() {
		System.out.println("學生走路");
	}

	@Override
	public String toString() {
		return "Student{" + "name='" + name + '\'' + ", age=" + age + '}';
	}
}
