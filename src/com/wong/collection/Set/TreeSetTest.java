package com.wong.collection.Set;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/*
TreeSet
* 向TreeSet集合中添加的元素必須是同一種類型的數據,不包含自動轉換的過來的數據
* String、int、float、long等已經重寫int compareTo(Object o)方法,這些類型數據組成的TreeSet默認從小到大排序
* 向TreeSet集合添加元素時有兩種排序方法:
    * 自然排序
    * 定制排序
* 自然排序(使用compareTo方法進行比較是否相同)
    * 自定義的來要 實現java.lang.Comparable接口並重寫int compareTo(Object o)方法,compareTo()方法返回值為0時,表示這兩個對象相同
        在此方法中,指定該類按照哪些屬性排序.
    * 向TreeSet集合中添加元素時,首先調用對象的int compareTo(Object o)方法進行比較,若返回值為0,則認為這兩個對像是相同的.
        這通情況下該元素就添加不進來
    * int compareTo(Object o)、int hashCode()、boolean equals()三個方法取值方向要求同時一致,
* 定制排序(使用與不能更改類的場景)
    * set1:創建一個實現了Comparator接口的對象,重寫int compare(Object o1, Object o2)方法
    * set2:把set1中創建的comparator對像以形參傳入TreeSet構造器
    * 使用定制排序是不需要實現自定義類的Comparable接口,如果有實現,則定制排序優先
* */


import org.junit.Test;

import com.wong.collection.pojo.Car;
import com.wong.collection.pojo.Person;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class TreeSetTest {
    
	@Test
    public void treeSet() {
        TreeSet tset1 = new TreeSet();
        tset1.add(66.1F);
        tset1.add(22F);
        tset1.add(33.3F);
// tset1.add("BB"); // ClassCastException異常,cannot be cast to class java.lang.Comparable

        System.out.println(tset1); // [22.0, 33.3, 66.1]

        //
        TreeSet tset2 = new TreeSet();
        tset2.add("FF");
        tset2.add("AA");
        tset2.add("GG");
        tset2.add("DD");


        System.out.println(tset2); // [AA, DD, FF, GG]
    }

    @Test
    public void treeSet2() {
        /*
        TreeSet自然排序
        * */
        TreeSet tset3 = new TreeSet();
        tset3.add(new Person("GG", 12)); // 當Person未實現Comparable接口,報:java.lang.ClassCastException: class com.java.www.Person cannot be cast to class java.lang.Comparable
        tset3.add(new Person("CC", 18));
        tset3.add(new Person("BB", 10));
        tset3.add(new Person("MM", 10));

        Iterator ite = tset3.iterator();
        for (;ite.hasNext() ;) {
            System.out.println(ite.next());
        }

    }

    @Test
    public void treeTest3() {
        /*
        TreeSet定制排序
        * */

        // set1:創建一個實現了Comparator接口的對象,重寫int compare(Object o1, Object o2)方法
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Car && o2 instanceof Car) {
                    Car c1 = (Car) o1;
                    Car c2 = (Car) o2;
                    int i = c1.getId() - c2.getId();
                    if (i == 0) {
                        i = c1.getModel().compareTo(c2.getModel());
                    }
                    return i;
                }
                return 0;
            }
        };

        // set2:把set1中創建的comparator對像以形參傳入TreeSet構造器
        TreeSet tset4 = new TreeSet(comparator);
        tset4.add(new Car(103, "寶馬X3"));
        tset4.add(new Car(103, "寶馬X6"));
        tset4.add(new Car(101, "奧迪Q5L"));
        tset4.add(new Car(104, "瑞虎8"));

        for (Object i : tset4) {
            System.out.println(i);
        }

    }
    
    @Test
    public void treeTest4() {
        /*
        TreeSet定制排序
        * */

        // set1:創建一個實現了Comparator接口的對象,重寫int compare(Object o1, Object o2)方法
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Person && o2 instanceof Person) {
                	Person p1 = (Person) o1;
                	Person p2 = (Person) o2;
                	return Integer.compare(p1.getAge(), p2.getAge());
                }else {
                	throw new RuntimeException("輸入的數據類型不匹配");
                }
                
            }
        };
        System.out.println("========================");
        // set2:把set1中創建的comparator對像以形參傳入TreeSet構造器
        TreeSet tset4 = new TreeSet(comparator);
        tset4.add(new Person("GG", 12)); // 當Person未實現Comparable接口,報:java.lang.ClassCastException: class com.java.www.Person cannot be cast to class java.lang.Comparable
        tset4.add(new Person("CC", 18));
        tset4.add(new Person("BB", 10));
        tset4.add(new Person("MM", 10));

        Iterator ite = tset4.iterator();
        while (ite.hasNext()) {
            System.out.println(ite.next());
        }
    }

}

