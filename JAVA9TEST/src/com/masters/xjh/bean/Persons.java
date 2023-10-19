package com.masters.xjh.bean;

/**
 * @ClassName: Person
 * @Package: com.master.xjh.bean
 * @Description:
 * @Datetime: 2023/10/19 23:13
 * @author: ColorXJH
 */
public class Persons {
    private String name;
    private int age;
    public Persons() {}
    public Persons(String name, int age) {
        this.name = name;
        this.age = age;
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
        return "Persons{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
