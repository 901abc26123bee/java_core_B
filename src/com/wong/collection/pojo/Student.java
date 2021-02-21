package com.wong.collection.pojo;

/*
學生
假設其中的一張表
字段有：
long id,
String name,
String email,
Date birthday,
URI photo
* */

import java.net.URI;
import java.util.Comparator;

public class Student implements Comparator<Object> {
    private long id = 0;
    private String name;
    private String email;
    private MyDate birthday;
    private URI photo;
    private static long count = 1;

    // 構造器
    public Student() {
        super();
        idIncrease();
    }

    public Student(String name, String email, MyDate birthday) {
        this.name = name;
        this.email = email;
        this.birthday = birthday;
        idIncrease();
    }

    // 方法
    public void idIncrease() {
        id = count;
        ++count;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MyDate getBirthday() {
        return birthday;
    }

    public void setBirthday(MyDate birthday) {
        this.birthday = birthday;
    }

    public URI getPhoto() {
        return photo;
    }

    public void setPhoto(URI photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (id != student.id) return false;
        return name != null ? name.equals(student.name) : student.name == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", photo=" + photo +
                '}';
    }

    @Override
    public int compare(Object o1, Object o2) {
        if (o1 instanceof Student && o2 instanceof Student) {
            Student s1 = (Student) o1;
            Student s2 = (Student) o2;
            long i = s1.id - s2.id;
            return (int) i;
        }
        return 0;
    }
}

