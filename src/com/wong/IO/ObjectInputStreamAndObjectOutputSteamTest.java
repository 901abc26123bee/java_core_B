package com.wong.IO;

/*
對象流
ObjectInputStream, ObjectOutputStream
要求
* 類要可序列化
* 類的屬性可序列化
* 提供一個版本號，private static final long serialVersionUID = 354126654L;
* static, transient修飾的性能不能被序列化，取到的值為 null
writeObject(p1); // 寫入的對象p1所屬類要序列化，屬性也要可序列化，未實現序列化報 NotSerializableException 異常
類實現序列化，實現 Serializable類即，該類沒有方法。
對象的序列化過程：將內存中的對象通過ObjectOutputStream轉換為二進制數據流，存儲到硬盤中文件中
對象的反序列化過程：將硬盤中文件通過ObjectInputStream轉換為相應的對象
String, Integer等常用類已經實現序列化
* */

import org.junit.Test;

import java.io.*;

public class ObjectInputStreamAndObjectOutputSteamTest {
    @Test
    public void test1() {
        // 將對象寫入文件

        Person p1 = new Person("atom", 23, new Pet("dog"));
        Person p2 = new Person("emac", 22, new Pet("bird"));

        ObjectOutputStream oos = null;
        try {
            File file = new File("object.dat");
            FileOutputStream fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(new String("使用對象流序列化String對象"));
            oos.flush();
            
            oos.writeObject(p1); // 寫入的對象p1所屬類要序列化，未實現序列化報 NotSerializableException 異常
            oos.flush();
            oos.writeObject(p2);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    @Test
    public void test2() {
        // 將對像從文件中讀取
        ObjectInputStream ois = null;
        try {
            // 1. 創建File對象
            File file = new File("object.dat");
            // 2. 創建FileInputStream對象
            FileInputStream fis = new FileInputStream(file);
            // 3. 創建ObjectInputStream對象
            ois = new ObjectInputStream(fis);
            Object obj = ois.readObject();
            String string = (String)obj;
            System.out.println(string);
            
            Person p1 = (Person)ois.readObject();
            Person p2 = (Person) ois.readObject();
            System.out.println(p1);
            System.out.println(p2);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}

class Person implements Serializable {
    private static final long serialVersionUID = 354126654L;
    private String name;
    private int age;
    private Pet pet; // Pet 也需要可序列化

    // 構造器
    public Person() {
        super();
    }

    public Person(String name, int age, Pet pet) {
        this.name = name;
        this.age = age;
        this.pet = pet;
    }

    // 方法


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (age != person.age) return false;
        return name != null ? name.equals(person.name) : person.name == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", pet=" + pet +
                '}';
    }
}


class Pet implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;

    // 構造器
    public Pet() {}
    public Pet(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                '}';
    }
}