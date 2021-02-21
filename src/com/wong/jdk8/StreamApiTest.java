package com.wong.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

import com.wong.jdk8.Employee;

public class StreamApiTest {
    List<Employee> employeeList = EmployeeData.getEmployees();

    /**
     * 創建Stream對象方式1：通過集合的默認方法
     */
    @Test
    public void test1() {
        // default Stream<E> stream(): 返回一個順序流
        Stream<Employee> stream = employeeList.stream();
        System.out.println(stream);

        // default Stream<E> parallelStream(): 返回一個並行流
        Stream<Employee> parallelStream = employeeList.parallelStream();
        System.out.println(parallelStream);
    }

    /**
     * 創建Stream對象方式2：通過數組Arrays類的默認方法
     * 只適用於順序流
     */
    @Test
    public void test2() {
        int[] arr = new int[]{3, 1, 4, 7};

        // 調用Arrays類的public static <T> Stream<T> stream(T[] array): 返回一個流
        IntStream intStream = Arrays.stream(arr);

        // 自定義數據類型的數組
        Employee e1 = new Employee(1001, "Xu sanduo");
        Employee e2 = new Employee(1002, "Liu shaoqi");
        Employee[] arr1 = {e1, e2};
        // Employee[] arr1 = new Employee[]{e1, e2};
        Stream<Employee> employeeStream = Arrays.stream(arr1);

        // Arrays類沒有 Stream<T> parallelStream(T[] array) 方法
// Stream<Employee> employeeStream = Arrays.parallelStream(eArr); // 錯誤

    }

    /**
     * 創建Stream對象方式3：通過Stream接口的of()方法
     *
     * public static<T> Stream<T> of(T t)
     */
    @Test
    public void test3() {
        Stream<Integer> stream = Stream.of(13, 13, 1, 3, 5);

    }

    /**
     * 創建Stream對象方式4：創建無限流
     *
     * public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
     * public static<T> Stream<T> iterate(T seed, Predicate<? super T> hasNext, UnaryOperator<T> next)
     * public static<T> Stream<T> generate(Supplier<? extends T> s)
     */
    @Test
    public void test4() {
        // 迭代
        Stream<Integer> stream = Stream.iterate(0, t -> t + 2);
// iterate.forEach(System.out::println); // 無限打印下去

        // 遍歷前10個元素
        stream.limit(10).forEach(System.out::println);
        System.out.println();


        // 生成
        Stream<Double> stream1 = Stream.generate(Math::random);
// stream1.forEach(System.out::println); // 不停遍歷下去
        stream1.limit(5).forEach(System.out::println);

    }

    /**
     * 順序流 與 並行流的互轉
     *
     * parallel()
     * 把此Stream流轉成並行流
     * sequential()
     * 把此Stream流轉成順序流
     */
    @Test
    public void test5() {
        Stream<Employee> parallel = employeeList.stream().parallel();
        Stream<Employee> sequential = employeeList.parallelStream().sequential();
    }

}