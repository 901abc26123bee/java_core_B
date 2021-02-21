package com.wong.jvm;

public class StaticObjTest {
	static class Test {
		static ObjectHolder staticobj = new ObjectHolder();
		ObjectHolder instanceObj = new ObjectHolder();

		void foo() {
			ObjectHolder localobj = new ObjectHolder();
			System.out.println("done");
		}
	}

	private static class ObjectHolder {
	}

	public static void main(String[] args) {
		Test test = new StaticObjTest.Test();
		test.foo();
	}
}