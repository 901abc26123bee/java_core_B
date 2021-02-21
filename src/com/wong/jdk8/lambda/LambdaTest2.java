package com.wong.jdk8.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.junit.Test;

public class LambdaTest2 {
    /**
     * Consumer<T>
     */
    public void happyTime(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

    @Test
    public void test1() {
        happyTime(600, new Consumer<Double>() {
            @Override
            public void accept(Double aDouble) {
                System.out.println(String.format("花了￥%s吃了個晚餐", aDouble));
            }
        });

        System.out.println("\n// --------------------\n");

        // lambda寫法
        happyTime(1000, money -> System.out.println(String.format("花了￥%s吃了個晚餐", money)));

    }

    /**
     * 根據給定的規則，過濾指定集合中字符串，並返符合條件的元素組成的新的集合。具體規則有Predicate的test方法決定
     * @param list
     * 指定的集合
     * @param predicate
     * 判斷規則
     * @return 返符合條件的元素組成的新的集合
     */
    public List<String> filterString(List<String> list, Predicate<String> predicate) {
        ArrayList<String> filterList = new ArrayList<>();
        for (String s : list) {
            if (predicate.test(s)) {
                filterList.add(s);
            }
        }
        return filterList;
    }

    /**
     * 過濾list中含"a"的元素
     */
    @Test
    public void test2() {
        List<String> list = Arrays.asList("asd", "ffg", "ksa", "wav", "poc");
        List<String> list1 = filterString(list, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("a");
            }
        });
        System.out.println(list1);

        System.out.println("\n// --------------------\n");

        // lambda寫法
        List<String> list2 = filterString(list, s -> s.contains("京"));
        System.out.println(list2);
    }

}