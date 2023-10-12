package com.master.chapter14;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2023-10-12 16:39
 */
public class Student {
    private String name;
    private int age;
    public Student(){}
    public Student(String name,int age){
        this.name=name;
        this.age=age;
    }
    public void show(){
        System.out.println("i am show ing----");
    }
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
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
