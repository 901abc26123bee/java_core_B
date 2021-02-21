package com.wong.jdk8;

import java.util.ArrayList;
import java.util.List;

public class EmployeeData {
	public static List<Employee> getEmployees() {
		List<Employee> list = new ArrayList<>();

		list.add(new Employee(1001, "ema", 24, 30000));
		list.add(new Employee(1002, "tom", 31, 55000));
		list.add(new Employee(1003, "lisa", 17, 5000));
		list.add(new Employee(1002, "loom", 31, 55000));
		// ...
		return list;
	}
}
