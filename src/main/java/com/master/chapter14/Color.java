package com.master.chapter14;

import java.io.IOException;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2023-10-12 8:45
 */
@MyAnnotation(value = "ColorXJH")
public class Color extends Base<String> implements Comparable<String>,MyInterface{
    private static int weight=220;
    private String name;
    int age;
    public int id;
    public Color(){}
    @MyAnnotation(value = "abc")
    private Color(String name){
        this.name=name;
    }
    Color(String name,int age){
        this.name=name;
        this.age=age;
    }
    @MyAnnotation
    private String show(String nation){
        System.out.println("我的国籍是："+nation);
        return nation;
    }

    public String display(String interests) throws IOException {
        return interests;
    }
    @Override
    public void info() {
        System.out.println("我是一个人");
    }

    @Override
    public int compareTo(String o) {
        return 0;
    }

    private static String showStatic(String name){
        System.out.println("----+++++______"+name);
        return name;
    }

    @Override
    public String toString() {
        return "Color{" +
                "weight=" + weight +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}
