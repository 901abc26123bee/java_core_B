package com.wong.collection.Map;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/*
TreeMap
* 按照添加進Map中元素的key指定屬性進行排序。所有元素key的對象必須是同一個類，key對象為自定義類對象時要求實現Comparable接口並重寫int compareTo(Object obj)方法
    * 自然排序，用對像中的int compareTo(Object obj)方法進行比較排序
    * 定制排序，TreeMap中傳入Comparator接口實現實例，要求重寫Comparator接口中的int compare(Object o1, Object o2)
* */



import org.junit.Test;

import com.wong.collection.pojo.Car;
import com.wong.collection.pojo.Person;
@SuppressWarnings({ "rawtypes", "unchecked" })
public class TreeMapTest {
    @Test
    public void test1() {
        // TreeMap自然排序
        TreeMap tmap1 = new TreeMap();
        tmap1.put(new Person("EE", 22), "1");
        tmap1.put(new Person("GG", 21), "2");
        tmap1.put(new Person("AA", 20), "3");
        tmap1.put(new Person("BB", 18), "4");
        tmap1.put(new Person("HH", 18), "5");

        Set kset1 = tmap1.keySet();
        for (Object k : kset1) {
            System.out.println(k + " : " + tmap1.get(k));
        }
        System.out.println("-----------------");
        Set kset2 = tmap1.entrySet();
        Iterator it1 = kset2.iterator();
        while (it1.hasNext()) {
        	Object object = it1.next();
        	Map.Entry entry = (Map.Entry) object;
            System.out.println(entry.getKey() + " --> " + entry.getValue());
        }

    }

   
	@Test
    public void test2() {
        // TreeMap定制排序
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Car && o2 instanceof Car) {
                    Car c1 = (Car) o1;
                    Car c2 = (Car) o2;
                    int i = c1.getId() - c2.getId();
                    return i;
                    // return Integer.compare(c1.getId(), c2.getId());
                    /*
                     * Integer.compare :
                      	public static int compare(int x, int y) {
					        return (x < y) ? -1 : ((x == y) ? 0 : 1);
					    }
                     * */
                }
                return 0;
            }
        };

        TreeMap tmap2 = new TreeMap(comparator);
        tmap2.put(new Car(1003, "林肯MKX 2018款 2.0T"), 37.88);
        tmap2.put(new Car(1001, "林肯MKZ 2018款 2.0T"), 27.88);
        tmap2.put(new Car(1002, "奧迪Q5L 2018款 40 TFSI 榮享進取型"), 32.92);
        tmap2.put(new Car(1000, "奧迪A8 2018款 A8L 55 TFSI quattro投放版精英型"), 66.83);

        Set kset1 = tmap2.keySet();
        for (Object k : kset1) {
            System.out.println(k + " | 價格: " + tmap2.get(kset1) + "萬");
        }

    }
}

