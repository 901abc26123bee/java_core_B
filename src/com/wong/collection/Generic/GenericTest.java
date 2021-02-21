package com.wong.collection.Generic;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
泛型
作用：限制為指定的數據類型，但未使用之前(實例化之前)又不能確定是哪種數據類型，在實例化時指定數據類型。
* 符號：<>
* 格式：<類型列表>, 如<Boolean>, <String, Integer>，可以寫多個數據類型
## 集合中使用泛型
* 限制集合中只能添加指定類型的對象，添加其他類型的數據將報錯
* 獲取元素時，類型仍為集合限制的數據類型，不需要強制類型轉換
* 非泛型的Collection 集合中的元素類型都為Object
* 非泛型的Iterator裡的對像類型都為Object
* 非泛型的Map中key、value均為Object類型
## 自定義泛型類、泛型接口、泛型方法
* 當實例化泛型類的對象時，指明泛型類的類型，指明後，對應的類中所有使用泛型類的位置，都變為實例中指定的泛型類型
* 如果定義了泛型類，在實例化時沒有指定泛型，默認類型為Object
* 靜態方法中不能使用類的泛型，因為靜態方法在類加載時就確定了
* catch的異常類型不能為泛型
## 泛型與繼承的關係
* 子類不為泛型類：繼承時指定父類泛型中的類型，例如class SubCustomer extends Customer<Integer> { }
* 子類仍為泛型類：繼承時子類使用泛型，例如class SubCustomer2<T> extends Customer<T> { }
* 若類A是類B的子類，那麼List<A>不是List<B>的子類，List<A>與List<B>類型不一樣
## 通配符
作用：兼容多種泛型類型
* 符號：<?>
* List<A>、List<B> ... ... 都是List<?>的子類
* <? extends A> 只能存放A及其子類，元素可以增查改刪
* <? super A> 只能存放A及其父類
* 不能向聲明為通配符的集合中添加、修改元素(當元素為null除外)，但可以獲取、刪除元素
* */
import org.junit.Test;

import com.wong.collection.pojo.Customer;
import com.wong.collection.pojo.DAO;
import com.wong.collection.pojo.MyDate;
import com.wong.collection.pojo.PhoneUSB;
import com.wong.collection.pojo.Student;
import com.wong.collection.pojo.StudentDAO;
import com.wong.collection.pojo.SubCustomer2;
import com.wong.collection.pojo.SubCustomer;
@SuppressWarnings({ "rawtypes", "unchecked" })
public class GenericTest {
	@Test
    public void test1() {
        // 集合沒有使用泛型的情況
        List list1 = new ArrayList(); // 元素類型為 Object，可以添加任何類型的數據
        list1.add(11);
        list1.add(22);
        list1.add(22);
        list1.add(null);
        list1.add("AA");
        list1.add(true);
        // 問題1：類型不安全＝什麼類都能放
        for (Object o : list1) {
            System.out.println(o);
            // 11
            //22
            //22
            //null
            //AA
            //true
        }
        

        for (int i = 0; i < list1.size(); ++i) {
            Object obj = list1.get(i);
            // 問題2：需要進行類型強轉
            Integer n = (Integer) obj; // 當有元素不為int數據時(null除外)，報ClassCastException異常 System.out.println(n);
            System.out.println(n);
        }
    }

    @Test
    public void test2() throws RuntimeException {
        // 在集合中使用泛型
        List<Integer> list = new ArrayList<>(); // 此時list只能添加 Integer 元素
        List<Integer> list2 = new ArrayList<Integer>(); // 也可以這樣寫
        list.add(11);
        list.add(22);
        list.add(33);
        list.add(44);
// list.add("QQ"); // 添加其他類型的報錯 --> 會進行類型檢查 --> 類型安全

        for (Integer score : list) {
            int subScore = score;
            System.out.println(subScore);
        }

        System.out.println();
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer n = iterator.next();
            System.out.println(n);
        }

    }

    @Test
    public void test3() {
        // Map中使用泛型
        HashMap<String, Integer> hmap1 = new HashMap<>(); // 等價於 HashMap<String, Integer> hmap1 = new HashMap<String, Integer>();
        hmap1.put("李杰", 1963);
        hmap1.put("趙文", 1972);
        hmap1.put("小龍", 1940);

        Set<Map.Entry<String, Integer>> entrys = hmap1.entrySet(); // 泛型嵌套
        Iterator<Map.Entry<String, Integer>> iterator = entrys.iterator();
        while(iterator.hasNext()) {
        	Map.Entry<String, Integer> e = iterator.next();
        	String key = e.getKey();
        	Integer value = e.getValue();
        	System.out.println(key +" --> "+ value);
        }
        
        for (Map.Entry<String, Integer> entry : entrys) {
            String k = entry.getKey();
            Integer v = entry.getValue();
            System.out.printf("姓名：%s \t出生年份：%d年\n", k, v);
        }
    }

    @Test
    public void test4() {
        // 自定義泛型類 測試
        Customer<Character> c1 = new Customer<>(10001, "三星");
        c1.setT('B');
        System.out.println(c1.getT());
        c1.add('C');
        c1.add('D');
        c1.add('A');
// c1.add("GG"); // 與前面指定的類型不匹配，編譯不通過
        System.out.println(c1.getList());
        System.out.println();

        // 泛型方法 測試
        c1.showSelf(new Date());
        Integer[] iarr = new Integer[]{11, 22, 33, 44, 55};
        List<Integer> li = c1.arrayToList(iarr);
        System.out.println(li);
    }

    @Test
    public void test5() {
        // 泛型繼承關係 測試
        SubCustomer2<String> subc1 = new SubCustomer2<>();
        subc1.setT("我把泛型指定為String了");
        subc1.add("Monday");
        subc1.add("Tusday");
        subc1.add("Wednesday");
        subc1.add("Thursday");
        subc1.add("Friday");
        subc1.add("Seturday");
        subc1.add("Sunday");
// subc1.add(33);

        System.out.println(subc1);
        System.out.println();
        
        SubCustomer subc = new SubCustomer();

        Object[] arr1 = null;
        String[] arr2 = new String[]{"AA", "BB", "CC"};
        arr1 = arr2;
        System.out.println(arr1);

        List<Object> list = null;
        List<String> list2 = new ArrayList<>();
// list = list2; // 報錯


    }
    @Test
    public void test6() {
        /*
        DAO模擬測試
        * */
        DAO<Student> dao = new StudentDAO<>();
        Student s1 = new Student("Mary", "mary@gmail.com", new MyDate(1999, 2, 1));
        Student s2 = new Student("Rucy", "rucy@gmail.com", new MyDate(2008, 10, 1));
        Student s3 = new Student("Nacy", "nacy@gmail.com", new MyDate(2002, 12, 25));
        Student s4 = new Student("Farrency", "farrency@gmail.com", new MyDate(2014, 11, 3));
        dao.add(s1);
        dao.add(s2);
        dao.add(s3);
        dao.add(s4);


        List<Student> list = dao.getList();
        System.out.println(list);

        Student ss = list.get(2);
        System.out.println(ss);
    }

    @Test
    public void test7() {
        // 泛型接口 測試
        PhoneUSB<Integer> pusb = new PhoneUSB<>();
        pusb.start(99);
        pusb.start(98);
    }

    @Test
    public void test8() {
        // 通配符
        List<?> list = null;
        List<Object> list1= new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        list = list1;
        list = list2;

        show1(list1);
// show1(list2); // 不兼容的類型
        show2(list1);
        show2(list2);

        // Number 是Integer父類
        List<? extends Number> list3 = null;
        List<Integer> list4 = new ArrayList<>();
        list3 = list4;
// list3 = list1; // 類型不兼容
// list3.add(33)

        List<? super Integer> list5 = null;
        list5 = list1;
        list5.add(2);

        System.out.println();
        List<String> list6 = new ArrayList<>();
        list6.add("AA");
        list6.add("BB");
        list6.add("CC");

        List<?> list7 = list6;
        Iterator<?> ite = list7.iterator();
        while (ite.hasNext()) {
            Object obj = ite.next(); // 獲取的值類型為Object
            System.out.println(obj);
        }

        // 不能向聲明為通配符的集合中添加、修改元素(當元素為null除外)，但可以獲取、刪除元素
// list7.add("GG");
// list7.add(true);
// list7.set(0, "MM");
        list7.set(0, null);
        list7.add(null);

        list7.remove(0);
        System.out.println(list7);
        list7.get(0);
    }

    public void show1(List<Object> list) {

    }

    public void show2(List<?> list) {
        // List<?> list可以兼容多種泛型的List
    }

}
