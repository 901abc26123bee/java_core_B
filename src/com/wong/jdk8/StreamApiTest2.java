package com.wong.jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

public class StreamApiTest2 {
    private List<Employee> employeeList = EmployeeData.getEmployees();

    /**
     * 篩選與切片
     *
     * Stream<T> filter(Predicate<? super T> predicate);
     * 接收一個判定型的lambda表達式，從流中篩選出符合條件的元素。
     *
     * Stream<T> limit(long maxSize);
     * 截斷流，截斷流後的長度不超過maxSize（包含等於），相當於返回取流中的前maxSize個元素組成的流，元素不夠不報錯
     *
     * Stream<T> skip(long n);
     * 跳過流前面的n個元素，返回由剩下的元素組成的流。如果元素個數<=n，則返回一個空元素Stream對象
     * 與Stream<T> limit(long maxSize)互為把相反操作
     *
     * Stream<T> distinct();
     * 去重，返回去除了重複元素之後組成的流
     */
    @Test
    public void test1() {
        Stream<Employee> stream = employeeList.stream();

        // Stream<T> filter(Predicate<? super T> predicate)
        // 查詢員工表中薪資大於7000的員工信息
        System.out.println("Stream<T> filter(Predicate<? super T> predicate): ");
        stream.filter(e -> e.getSalary() > 7000).forEach(System.out::println);
        System.out.println();

        // Stream<T> limit(long maxSize)
        // 取員工列表中的前3個員工
        System.out.println("\nStream<T> limit(long maxSize): ");
        employeeList.stream().limit(3).forEach(System.out::println);
        System.out.println();

        // Stream<T> skip(long n)
        // 取員工列表中第三名員工之後的所有員工信息
        System.out.println("\nStream<T> skip(long n): ");
        employeeList.stream().skip(3).forEach(System.out::println);


        //
        System.out.println("\nStream<T> distinct(): ");
        List<Employee> list = new ArrayList<>();
        list.add(new Employee(1001, "atom", 34, 6000.38));
        list.add(new Employee(1002, "tina", 12, 9876.12));
        list.add(new Employee(1003, "jerry", 33, 3000.82));
        list.add(new Employee(1004, "atom", 34, 6000.38));
        list.add(new Employee(1005, "tina", 12, 9876.12));

        System.out.println(list);
        list.stream().distinct().forEach(System.out::println);

    }

    /**
     * 映射
     *
     * <R> Stream<R> map(Function<? super T, ? extends R> mapper);
     * 接收一個函數作為參數，將元素轉換成其他形式或提取信息。 map方法會自動遍歷所有的元素，該函數會被應用到每個元素上，並將其映射成一個新的元素，一一對應
     * 類似於test3()中的list1.add(list2);
     *
     * <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);
     * 接收一個函數作為參數，將流中的每個值都換成另外一個流，然後把所有流連接成一個流
     * 類似於下面 test4()中的list1.addAll(list2);
     */
    @Test
    public void test2() {
        // <R> Stream<R> map(Function<? super T, ? extends R> mapper);
        // 把列表中的每個元素轉成大寫
        List<String> list = Arrays.asList("aa", "bb", "cc");
        list.stream().map( str -> str.toUpperCase()).forEach(System.out::println);
        System.out.println();

        // 練習：獲取員工姓名長度大於3的員工姓名
        Stream<String> namesSteam = employeeList.stream().map(e -> e.getName());
// Stream<String> namesSteam = employeeList.stream().map(Employee::getName); // 也可以這樣寫，使用方法引用
        namesSteam.filter(e -> e.length() > 3).forEach(System.out::println);
        System.out.println();

        // 練習：map(Function mapper) 與 flatMap(Function mapper) 對比
        System.out.println("map(Function mapper): ");
        Stream<Stream<Character>> streamStream = list.stream().map(s -> stringToStream(s));
// Stream<Stream<Character>> streamStream = list.stream().map(StreamApiTest2::stringToStream); 也可以這樣寫
        streamStream.forEach(s -> s.forEach(System.out::println));
        System.out.println();

        // Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);
        System.out.println("flatMap(Function mapper): ");
        Stream<Character> characterStream = list.stream().flatMap(s -> stringToStream(s));
        characterStream.forEach(System.out::println);
    }

    /**
     * 將字符串的多個字符構成的集合List轉換成對應的Stream實例
     * @param str
     * 字符串
     * @return 返回指定的字符串組成的List集合的Stream實例
     */
    public static Stream<Character> stringToStream(String str) {
        ArrayList<Character> list = new ArrayList<>();
        for (char c : str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }

    @Test
    public void test3() {
        ArrayList list1 = new ArrayList();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        ArrayList list2 = new ArrayList();
        list2.add(5);
        list2.add(6);
        list2.add(7);

        list1.add(list2);
        System.out.println(list1); // [1, 2, 3, [5, 6, 7]]
    }

    @Test
    public void test4() {
        ArrayList list1 = new ArrayList();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        ArrayList list2 = new ArrayList();
        list2.add(5);
        list2.add(6);
        list2.add(7);

        list1.addAll(list2);
        System.out.println(list1); // [1, 2, 3, 5, 6, 7]
    }


    /**
     * 排序
     *
     * Stream<T> sorted();
     * 自然排序。
     * 只對順序流有效，對於parallelStream並行流無效
     *
     * Stream<T> sorted(Comparator<? super T> comparator);
     * 定制排序
     * 只對順序流有效，對於parallelStream並行流無效
     *
     */
    @Test
    public void test5() {
        List<Integer> list = Arrays.asList(22, 11, 33, 78, 99, 60, -1);
        list.stream().sorted().forEach(System.out::println);
        System.out.println();
        list.parallelStream().sorted().forEach(System.out::println); // sorted()方法對並行流無效。
        System.out.println();

        // 將員工按年齡從小到大排序，如果年齡相等，則按工資從小到大排序
        employeeList.stream().sorted((e1, e2) -> {
            int i = e1.getAge() - e2.getAge();
            if (i == 0) {
                return Double.compare(e1.getSalary(), e2.getSalary());
            }
            return i;
        }).forEach(System.out::println);
    }
}