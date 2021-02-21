package com.wong.collection;

/*
* List、Set、Map在創建對象時指定初始值
* 格式: new List/Set/Map實現類() {{}};
 * 外面的這個 {} 為匿名類，裡面的{}為靜態代碼塊。
 * 這種方式只有jdk >= 9 才支持
 *
* 注意： 慎用， 非靜態內部類/ 匿名內部類包含了外圍實例的引用， 如果擁有比外部類更長的生命週期，有內存洩露隱患。
* 另外這種方式生成的對像沒有被序列化，正常方式是已經序列化的
* */

import org.junit.Test;

import java.util.*;
@SuppressWarnings("serial")
public class ListSetMap_SpecifyInitialValuesAtNewBuildTime {
    /**
     * List在創建對象時指定初始值
     */
    @Test
    public void test1() {
		List<String> list = new ArrayList<>() { // 創建一個繼承於ArrayList 匿名子類對象
            // 靜態代碼塊，在加載類的時候就執行
            {
                add("我的樓蘭--雲朵"); // this.add("我的樓蘭--雲朵");
                add("你還是從前的你嗎--天籟天");
                add("我是你的格桑花--欣寶兒");
            }
        };

        System.out.println(list);
    }

    /**
     * Set在創建對象時指定初始值
     */
    @Test
    public void test2() {
        Set<Integer> set1 = new LinkedHashSet<>() {{
            add(3);
            add(1);
            add(4);
        }};
        System.out.println(set1);
    }

    /**
     * Map在創建對象時指定初始值
     */
    @Test
    public void test3() {
        Map<String, Boolean> map1 = new TreeMap<>() {{
            put("apple", true);
            put("juice", false);
            put("tasty", true);
        }};

        System.out.println(map1);
    }

}