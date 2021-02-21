package com.wong.jdk8;

import java.util.Optional;

import org.junit.Test;


public class OptionalTest {

    @Test
    public void test0() {
        Optional<String> optional = Optional.empty();
        if(!optional.isPresent()) {
        	System.out.println("no data in it");
        }
        
        System.out.println(optional);
        System.out.println(optional.isPresent());
    }

    /**
     * public static <T> Optional<T> of(T value)
     */
    @Test
    public void test1() {
        Employee e = new Employee();
        Optional<Employee> optional = Optional.of(e);
        System.out.println(optional);
        System.out.println();

        Employee e2 = null;
        Optional<Employee> optional2 = Optional.of(e2); // 報異常：java.lang.NullPointerException
        System.out.println(optional2);
    }

    @Test
    public void test2() {
        Employee e = new Employee();
        Optional<Employee> optional = Optional.ofNullable(e);
        System.out.println(optional);
        System.out.println();

        Employee e2 = null;
        Optional<Employee> optional2 = Optional.ofNullable(e2);
        System.out.println(optional2); // Optional.empty
    }

    /**
     * 獲取指定員工的姓名
     *
     * @param employee
     * 指定的Employee對象
     * @return 返回指定員工的姓名
     */
    public String getEmployeeName(Employee employee) {
        return employee.getName(); // employee有可能為null
    }

    /**
     * 獲取指定員工的姓名 優化版
     *
     * @param employee
     * @return
     */
    // under no optional condition
    public String getEmployeeName2(Employee employee) {
        if (employee != null) {
            String name =  employee.getName();
            if(name != null) {
            	// if(){...}
            	return name;
            }
        }
        return null;
    }

    @Test
    public void test4() {
        Employee e = new Employee();
        System.out.println(getEmployeeName(e));

        e = null;
        System.out.println(getEmployeeName(e));
    }

    @Test
    public void test5() {
        Employee e = null;
        System.out.println(getEmployeeName2(e));
        System.out.println();

        //
        System.out.println(getEmployeeName3(null));

    }

    /**
     * public T orElse(T other)
     */
    @Test
    public void test6() {
        Employee e = null;
        Optional<Employee> optional = Optional.ofNullable(e);
        Employee e2 = optional.orElse(new Employee("leon"));
        System.out.println(e2);
        System.out.println();

        //
        e = new Employee("jenny");
        Optional<Employee> optional2 = Optional.ofNullable(e);
        // 此時的e3一定為非空
        Employee e3 = optional2.orElse(new Employee("leon"));
        System.out.println(e3);
    }

    /**
     * Optional防止空指針的應用
     * @param employee
     * @return
     */
    public String getEmployeeName3(Employee employee) {
        Optional<Employee> optional = Optional.ofNullable(employee);
        Employee e = optional.orElse(new Employee(1009, "leon"));
        return e.getName();
    }
    
    
    @Test
    public void test7(){
    	Employee emp = new Employee();
      	emp = null;
      
      	Optional<Employee> optional = Optional.ofNullable(emp);
      	System.out.println(optional); //Optional.empty

        Employee e = optional.orElse(new Employee("atom")); // 如果此Optional容器的value屬性(元素)不為null，則直接返回value，否則返回指定的對象other
      	System.out.println(e.name);// e{name='atom'}
    }
}
