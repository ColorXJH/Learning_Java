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

}
