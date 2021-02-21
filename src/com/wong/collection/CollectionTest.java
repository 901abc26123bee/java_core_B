package com.wong.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

/*
Collection
向 Collection 接口的實現類的對象中添加 obj 時，要求所在的類要重寫 equals()
+ 存儲對象可以考慮：數組、集合
+ 數組存儲對象特點
    - 一旦創建，其長度不可變
    - 真實的數組存放的對像個數不可知
+ 集合
    - Collection接口
        |--List接口，存儲有序的、可以重複的元素
            |--ArrayList（元素內存中存儲是連續的）、LinkedList（鍊式列表，應用場景：頻繁插入/刪除）、Vector(比較古老、線程安全，比較慢)
        |--Set接口，存儲無序、不可重複的元素
            |--HashSet、LinkedHashSet、TreeSet
    - Map接口，存儲"鍵-值"對數據
        |--HashMap、LinkedHashMap、TreeMap、Hashtable(子類：Properties)
* */
/*
+ contains(Object obj) 判斷集合中是否包含指定的元素obj，如果包含返回true，否則false。
	- 判斷方法：用到了類中的 equals(Object o) 方法。若使用了自定義類要重寫 equals() 方法
	- 如果沒有重寫對象 Object 中的 equals 方法，默認如下：

	    @Override
	    public boolean equals(Object o) {
	    	return super.equals(o);
	    }
	   
	- 繼續查看Object類中的equals方法，源碼如下：
	
	    public boolean equals(Object o) {
	        return this == o;
	    }

	- 使用“==”比較對象的地址，如果是同一對象即地址相同的情況下，才會返回true，
	  而對於對象屬性值相同但地址不同的不同對象，始終返回false！
*/

/*
	List<String> array1 = Arrays.asList("Welcome", "to","Java", "world");
  	array1.add("Cool~~~");
  	---> java.lang.UnsupportedOperationException
  	
	- Arrays的asList方法使用的ArrayList類是一個內部定義的類，而不是java.util.ArrayList類。 
	- 從這個內部類ArrayList的實現可以看出，它繼承了抽象類java.util.AbstractList<E>, 
	  但是沒有重寫 add 和 remove 方法，沒有給出具體的實現。
	- 預設情況下，java.util.AbstractList類在add、set以及remove方法中，
	  直接會丟擲UnsupportedOperationException異常。
	- 正是因為java.util.Arrays類的內部類ArrayList ---> 沒有重寫add和remove方法，
	  所以，當我們呼叫其 add , remove 方法時，其實就是呼叫了AbstractList類的add方法，
	  結果就是直接丟擲UnsupportedOperationException異常。
	  由於Arrays的內部類ArrayList ---> 重寫了set方法，所以 set 正常執行，
	  不會再丟擲UnsupportedOperationException異常。
	  
+ Arrays工具類提供了一個方法asList, 使用該方法可以將一個變長引數或者陣列轉換成List 。
    - 生成的List的長度是固定的；
    - 能夠進行修改操作（比如，修改某個位置的元素）；
    - 不能執行影響長度的操作（如add、remove等操作）。會丟擲UnsupportedOperationException異常。

    Arrays.asList比較適合那些已經有陣列資料或者一些元素，而需要快速構建一個List，只用於讀取操作，而不進行新增或刪除操作的場景。
+ 如果，想要根據已知陣列資料，快速獲取一個可進行增刪改查的列表List，一個比較簡單的方法如下：

    - 重新使用java.util.ArrayList包裝一層。
		
		// 使用變長引數
		List<String> array1 = new ArrayList<>(Arrays.asList("Welcome", "to", "Java", "world"));
		System.out.println(array1);	// [Welcome, to, Java, world]
		array1.add("Cool~~~");
		System.out.println(array1);	// [Welcome, to, Java, world, Cool~~~]
	

*/


public class CollectionTest {
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
    public void test1() {
    	System.out.println("============ test 1 .size.add.addAll.isEmpty.clear===========");
		Collection c1 = new ArrayList();

        // size() 返回集合中元素的個數
        System.out.println(c1.size());

        // add(Object obj) 向集合中添加一個元素
        c1.add(123);
        c1.add("AA");
        c1.add(new Date());
        c1.add("BB");
        System.out.println(c1.size());

        // addAll(Collection coll) 將一個集合所有元素添加到當前集合中
        Collection c2 = Arrays.asList(1, 2, 3);
        c1.addAll(c2);
        System.out.println(c1.size());

        // 查看所有元素
        System.out.println(c1);
        System.out.println(c2);

        // isEmpty() 判斷集合是否為空，是返回true，否則false
        System.out.println(c1.isEmpty());

        // clear() 清空合集所有元素
        c1.clear();
        System.out.println(c1);
        System.out.println(c1.isEmpty());

    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
    public void test2() {
    	System.out.println("============ test 2 .contains.containsAll.remove.retainAll===========");
        Collection c1 = new ArrayList();
        c1.add(123);
        c1.add("AA");
        c1.add(new Date());
        c1.add(new String("BB"));
        c1.add(new Person("AA", 23));
        Person p59 = new Person("CC", 44);
        c1.add(p59);
        c1.add(new Dog("Tofu", "King"));

        // contains(Object obj) 判斷集合中是否包含指定的元素obj，如果包含返回true，否則false。
        // 判斷方法：用到了類中的 equals(Object o) 方法。若使用了自定義類要重寫 equals() 方法
        // 如果沒有重寫對象 Object 中的 equals 方法，默認如下：
        /*
	        @Override
	        public boolean equals(Object o) {
	        	return super.equals(o);
	        }
	       
	        繼續查看Object類中的equals方法，源碼如下：
	
	        public boolean equals(Object o) {
	            return this == o;
	        }

		使用“==”比較對象的地址，如果是同一對象即地址相同的情況下，才會返回true，
		而對於對象屬性值相同但地址不同的不同對象，始終返回false！
		*/
        boolean b60 = c1.contains(123);
        System.out.println(b60);
        System.out.println(c1.contains("AA"));
        System.out.println(c1.contains(p59)); // true
        System.out.println(c1.contains(new Person("CC", 44))); // false
        // c1中每個對象都調用一次 Dog.equals() 進行比較，七個對象共七次
        System.out.println(c1.contains(new Dog("Tofu", "King"))); // true，Dog 已重寫了equal(Object o) 方法
        System.out.println(c1.contains("BB"));

        // containsAll(Collection coll) 判斷當前集合是否包含coll集合中所有的元素。即判斷一個集合是否為當前集合的子集
        // Collection c2 = Arrays.asList(123, "AA");
        Collection c2 = new ArrayList();
        c2.add(123);
        c2.add("AA");
        System.out.println(c1.containsAll(c2)); // true

        // retainAll(Collectoin coll) 保留一個 集合coll與當前集合的交集，把結果交集交給當前集合，會覆蓋當前集合
        Collection c3 = Arrays.asList("AA", 123, "other"); // 這種方式的集合元素不能被刪除
        c1.retainAll(c3);
        System.out.println(c1);
        

        // remove(Object obj) 刪除集合中的obj元素。
        // 若刪除成功返回true,否則返回false(代表該值不存在)
        System.out.println(c2.remove(123)); // true
        System.out.println(c2); // 打印：[AA]
        System.out.println(c1.remove(new Person("CC", 44)));

    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void test3() {
    	System.out.println("============ test 3 removeAll.equals.hashCode.toArray.iterator===========");
        Collection c1 = new ArrayList();
        c1.add(123);
        c1.add("AA");
        c1.add(new Date());
        c1.add("BB");
        System.out.println(c1);

        Collection c2 = new ArrayList();
        c2.add(123);
        c2.add("AA");
        c2.add(3.14);

        // removeAll(Collection coll) 從當前集合中刪除與另外一個集合coll的交集所有元素。即 當前集合 - coll集合的差集。
        c1.removeAll(c2);
        System.out.println(c2);
        System.out.println(c1);

        // equals(Object obj) 判斷一個集合obj與當前集合兩者所有的元素是否都相等，對象值和順序都要一樣才會true。
        Collection c111 = new ArrayList();
        Collection c112 = new ArrayList();
        c111.add(108);
        c111.add("aux");

        c112.add(108);
        c112.add("aux");

        System.out.println(c111.equals(c112)); //true

        // hashCode() 獲取當前集合的hash值
        System.out.println(c111.hashCode());
        System.out.println(c112.hashCode());
        
        System.out.println("======================");
        // 數組 ---> 集合：調用Arrays類靜態方法asList()
        List<String> list = Arrays.asList(new String[] {"dd", "yy", "uu"});
        System.out.println(list); // [dd, yy, uu]
        // becarefull
        List<int[]> list2 = Arrays.asList(new int[] {123,456,789});
        System.out.println(list2); // [[I@1ffe63b9]
        System.out.println(list2.size()); // 1
        List list3 = Arrays.asList(123,567);
        System.out.println(list3); // [123, 567]
        List list4 = Arrays.asList(new Integer[] {123,456,789});
        System.out.println(list4); // [123, 456, 789]
        System.out.println(list4.size()); // 3
        System.out.println("******************");
        Object[] arr = list4.toArray();
        for(int i=0; i<arr.length; i++) {
            System.out.println(arr[i]);
        }

        
        // toArray() 將集合轉化成數組
        Collection c126 = new ArrayList();
        c126.add(new Person("王大", 12));
        c126.add(33);
        c126.add("haha");
        System.out.println(c126);

        Object[] obj = c126.toArray();
        for (int i = 0; i < obj.length; ++i) {
            System.out.println(obj[i]);
        }

        // iterator() 返回一個Iterator接口實現類對象，可用於遍歷集合
        // 內部方法：hasNext() next()
        System.out.println();

        // 方式1，
        System.out.println("集合遍歷方式1");
        Iterator iter = c126.iterator(); // [Person{name='王大', age=12}, 33, haha]
        System.out.println(iter.next()); // Person{name='王大', age=12}
        System.out.println(iter.next()); // 33
        System.out.println(iter.next()); // haha
        // System.out.println(iter.next()); NoSuchElementException

        // 方式2 not recommand
        System.out.println("集合遍歷方式2");
        Iterator iter2 = c126.iterator();
        for (int i = 0; i < c126.size(); ++i) {
            System.out.println(iter2.next());
        }

        // 方式3 recommand
        System.out.println("集合遍歷方式3");
        Iterator ite3 = c126.iterator();

        while (ite3.hasNext()) {
            System.out.println(ite3.next());
        }
        

        for (; ite3.hasNext(); ) {
            System.out.println(ite3.next());
        }
    }
}

class Person {
    private String name;
    private int age;

    // constructor
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class Dog {
    private String name;
    private String master;

    // constructor
    public Dog() {
    }

    public Dog(String name, String master) {
        this.name = name;
        this.master = master;
    }

    // 方法
    public void setName(String name) {
        this.name = name;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", master='" + master + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
    	System.out.println("Dog.equals()...");
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dog dog = (Dog) o;

        if (name != null ? !name.equals(dog.name) : dog.name != null) return false;
        return master != null ? master.equals(dog.master) : dog.master == null;

    }

}