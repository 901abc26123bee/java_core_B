package com.wong.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * Stream接口的終止操作
 *
 *
 */
public class StreamApiTest3 {
    private List<Employee> employeeList = EmployeeData.getEmployees();

    /**
     * 匹配與查找
     *
     * boolean allMatch(Predicate<? super T> predicate);
     * 檢查是否匹配所有的元素
     *
     * boolean anyMatch(Predicate<? super T> predicate);
     * 檢查是否至少匹配一個元素
     *
     * boolean noneMatch(Predicate<? super T> predicate);
     * 檢查是否沒有匹配所有的元素
     *
     * Optional<T> findFirst();
     * 返回第一個元素，若沒有元素則返回一個空的Optional對象
     *
     * Optional<T> findAny();
     * 返回當前流中的任意元素，若沒有元素則返回一個空的Optional對象
     * 順序流中查找的為第一個元素，
     * 並行流中查找的則不一定為第一個元素。對於同一個集合，基本上每次查找的都是同一個
     * long count();
     * 返回流中元素的個數
     *
     * Optional<T> max(Comparator<? super T> comparator);
     * 返回流中排序後的最大值元素
     *
     * Optional<T> min(Comparator<? super T> comparator);
     * 返回流中排序後的最小值元素
     *
     * void forEach(Consumer<? super T> action);
     * 內部迭代。
     *
     */
    @Test
    public void test1() {
        // boolean allMatch(Predicate<? super T> predicate)
        // 是否所有的員工年齡都大於18
        boolean b = employeeList.stream().allMatch(e -> e.getAge()>18);
        System.out.println("allMatch: ");
        System.out.println(b);
        System.out.println();

        // boolean anyMatch(Predicate<? super T> predicate);
        // 是否存有員工的工資大於6000
        boolean anyMatch = employeeList.parallelStream().anyMatch(e -> e.getSalary() > 6000);
        System.out.println("anyMatch: ");
        System.out.println(anyMatch);
        System.out.println();

        // boolean noneMatch(Predicate<? super T> predicate);
        // 是否不存在姓"雷"的員工
        boolean noneMatch = employeeList.stream().noneMatch(e -> e.getName().startsWith("雷"));
        System.out.println("noneMatch: ");
        System.out.println(noneMatch);
        System.out.println();

        // Optional<T> findFirst();
        // 查找第一個員工姓名
        Optional<Employee> employee = employeeList.stream().findFirst();
        System.out.println("Optional<T> findFirst(), 查找第一個員工姓名");
        System.out.println(employee);
        System.out.println();

        // Optional<T> findAny();
        // 查找任意一個員工
        Optional<Employee> employee1 = employeeList.parallelStream().findAny();
        System.out.println("findAny(), 查找任意一個員工: ");
        System.out.println(employee1);
        System.out.println();

        // long count();
        System.out.println("count(): ");
        System.out.println(employeeList.parallelStream().count());
        System.out.println();

        // Optional<T> max(Comparator<? super T> comparator);
        // 練習：返回最高的工資
        System.out.println("max(Comparator<? super T> comparator), 返回最高的工資: ");

        Optional<Double> maxSalary = employeeList.stream().map(Employee::getSalary).max((d1, d2) -> Double.compare(d1, d2));
        System.out.println(maxSalary.get());
        System.out.println();

        // Optional<T> min(Comparator<? super T> comparator);
        // 練習：返回工資最低的員工
        Optional<Employee> minSalaryEmployee = employeeList.parallelStream().min((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println("min(Comparator<? super T> comparator), 工資最低的員工: ");
        System.out.println(minSalaryEmployee);
        System.out.println();


        // void forEach(Consumer<? super T> action);
        employeeList.stream().forEach(System.out::println);
        System.out.println();

        // 使用集合的迭代器，這個與Stream接口的forEach方法不一樣
        employeeList.forEach(System.out::println);
    }

    /**
     * 歸約
     *
     * T reduce(T identity, BinaryOperator<T> accumulator);
     * 可以將流中的元素反复結合起來，得到一個值。返回T
     * identity: 初始值
     * sum、min、max、average和string連接都是歸約的特殊情況
     * Optional<T> reduce(BinaryOperator<T> accumulator);
     * 可以將流中的元素反复結合起來，得到一個值。返回Optinal<T>
     *
     * <U> U reduce(U identity,
     * BiFunction<U, ? super T, U> accumulator,
     * BinaryOperator<U> combiner);
     *
     */
    @Test
    public void test2() {
        // T reduce(T identity, BinaryOperator<T> accumulator);
        // 練習：計算1-10的自然數的和
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer i = list.stream().reduce(0, (i1, i2) -> i1 + i2);
        System.out.println("T reduce(T identity, BinaryOperator<T> accumulator), 計算1-10的自然數的和: ");
        System.out.println(i);
        System.out.println();

        // Optional<T> reduce(BinaryOperator<T> accumulator);
        // 練習：計算公司所有員工工資的總和
        Stream<Double> salaryStream = employeeList.parallelStream().map(Employee::getSalary);
        Optional<Double> salarySum = salaryStream.reduce((d1, d2) -> d1 + d2);
// Optional<Double> salarySum = salaryStream.reduce(Double::sum); // 也可以
        System.out.println("Optional<T> reduce(BinaryOperator<T> accumulator), 計算公司所有員工工資的總和: ");
        System.out.println(salarySum.get());

    }

    /**
     * 收集
     *
     * <R, A> Rcollect(Collector<? super T, A, R> collector);
     * 將流轉換為其他形式。接收一個Collector接口的實現實例，用於給Stream中元素做收集的方法
     * Collector接口實現實例的方法決定瞭如何對流執行收集操作（如收集到List、Set、Map）
     * 另外，Collectors實現類提供了很多靜態方法，可以方便地創建常見收集器實例
     *
     * 例如：把list中的員工信息轉換成以id為key，Employee對象為值的Map中
     * Function<Employee, Integer> keyMapper = Employee::getId;
     * Function<Employee, Employee> valueMapper = e -> e;
     * Map<Integer, Employee> employeeMap = employeeList.stream().collect(Collectors.toMap(keyMapper, valueMapper));
     * employeeMap.forEach((k, v) -> System.out.println(String.format("key:%s, value:%s", k, v)));
     *
     *
     * <R> R collect(Supplier<R> supplier,
     * BiConsumer<R, ? super T> accumulator,
     * BiConsumer<R, R> combiner);
     */
    @Test
    public void test3() {
        // <R, A> R collect(Collector<? super T, A, R> collector);
        // 練習：查找公司內工資大於6000的員工，結果返回為一個List或Set
        List<Employee> collectList = employeeList.stream().filter(e -> e.getSalary() > 6000).collect(Collectors.toList());
        System.out.println(collectList);
        System.out.println();

        Set<Employee> collectSet = employeeList.parallelStream().filter(e -> e.getSalary() > 6000).collect(Collectors.toSet());
        System.out.println(collectSet);
        System.out.println();

        // 把list中的員工信息轉換成以id為key，Employee對象為值的Map中
        Function<Employee, Integer> keyMapper = Employee::getId;
        Function<Employee, Employee> valueMapper = e -> e;
        Map<Integer, Employee> employeeMap = employeeList.stream().collect(Collectors.toMap(keyMapper, valueMapper));
        employeeMap.forEach((k, v) -> System.out.println(String.format("key:%s, value:%s", k, v)));
        System.out.println();
        for (Map.Entry entry : employeeMap.entrySet()) {
            System.out.println(entry);
        }
    }
}