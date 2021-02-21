package com.wong.collection.Set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

/*
+ 集合
- Collection接口
    |--Set接口，存儲無序、不可重複的元素,常用方法都是Collection接口裡定義的
        |--HashSet: HashSet 是 Set 接口的主要實現類，線程不安全，集合元素可以是null
        	|--LinkedHashSet: LinkedHashSet 是 HashSet 的子類
        |--TreeSet: TreeSet是SortedSet接口的實現類，可以確保集合中元素處於排序狀態
        			向TreeSet集合中添加的元素必須是同一種類型的數據,不包含自動轉換的過來的數據

+ Set特點
	- 存儲的元素是無序,不可重複的.
	1. 無序性 : 不等於隨機性。指元素在底層存儲的位置並非按照數組索引的順序添加，而是跟據數據的哈希值.
 			隨機性：是指元素在底層存儲的位置是無序
	2. 不可重複性 : 保證添加的元素按照 equals 判斷時，不能返回 true ---> 相同的元素只能有一個

+ Set要求(包括HashSet、LinkedHashSet、TreeSet)
	- 添加到Set中的元素所在類,一定要重寫equals()、hashCode()方法
	- 當向HashSet添加元素時,首先調用此對象所在類的int hashCode()方法,計算此對象的哈希值,此哈希值決定了此對像在HashSet中的存儲位置.
		- 若此位置還沒有存儲對象,則此對象直接存儲到這個位置.
		- 若這個位置有存儲了對象,那麼在通過調用該對象的boolean equals()方法比較這兩個對像是否相同,如果equals()返回false則添加後面這個元素到HashSet中,否則不添加
	- hashCode()方法與equals()方法返回值方向要求一致.即返回表示相同或是不相同
* 
*/
@SuppressWarnings({ "rawtypes", "unchecked" })
public class SetTest {
	@Test
    public void test1() {
        Set hset1 = new HashSet();
        hset1.add(11);
        hset1.add(22);
        hset1.add(33);
        hset1.add(new String("AA"));
        hset1.add("BB");
        hset1.add("BB");
        hset1.add(null);
        Dog d1 = new Dog("Lizaza", 2);
        Dog d2 = new Dog("Binli", 1);
        Dog d3 = new Dog("Binli", 1); // 在未重寫Dog類的hashCode()方法時,可以添加進去
        hset1.add(d1);
        hset1.add(d2);
        hset1.add(d3);

        System.out.println(hset1.size());
        System.out.println(hset1);

        Iterator ite = hset1.iterator();
        while (ite.hasNext()) {
            System.out.println(ite.next());
        }

        HashSet hset2 = new HashSet();
        hset2.add(66);
        hset2.add(77);
        hset1.addAll(hset2);
        System.out.println(hset1);

        System.out.println(hset1.equals(hset2));
    }
	
	@Test
	public void test() {
		System.out.println("**********************");
		HashSet set = new HashSet();
		Dog dog1 = new Dog("AA", 1001);
		Dog dog2 = new Dog("BB", 1002);
		
		set.add(dog1); // hashCode ....
		set.add(dog2); // hashCode ....
		System.out.println(set); // [Dog{ name='AA', age=1001}, Dog{ name='BB', age=1002}]
		
		dog1.setName("CC");
		set.remove(dog1); // hashCode ....
		System.out.println(set); // [Dog{ name='CC', age=1001}, Dog{ name='BB', age=1002}]
		set.add(new Dog("CC", 1001)); // hashCode ....
		System.out.println(set); // [Dog{ name='CC', age=1001}, Dog{ name='CC', age=1001}, Dog{ name='BB', age=1002}]
		set.add(new Dog("AA", 1001)); // hashCode ....
									  // equals .... 
		System.out.println(set); // [Dog{ name='CC', age=1001}, Dog{ name='CC', age=1001}, Dog{ name='AA', age=1001}, Dog{ name='BB', age=1002}]
								 
		System.out.println("*********************");
	}

}

class Dog {
    private String name;
    private int age;
    static private int init = 100;

    // 构造器
    public Dog() {
        super();
    }

    public Dog(String name, int age) {
        super();
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

    public String toString() {
        return "Dog{ " +
                "name='" + name + '\'' +
                ", age=" + age +
                "}";
    }

    @Override
    public boolean equals(Object o) {
    	System.out.println("equals ....");
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dog dog = (Dog) o;

        if (age != dog.age) return false;
        return name != null ? name.equals(dog.name) : dog.name == null;

    }

    @Override
    public int hashCode() {
    	System.out.println("hashCode ....");
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;

//        return ++init;

//        return 3;

    }

}
