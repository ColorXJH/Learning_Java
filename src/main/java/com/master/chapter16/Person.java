package com.master.chapter16;

public record Person(String name,int age) {
    //还可以声明静态的属性，静态的方法，构造器，实例方法
    public static String nation;
    public static String showNation(){
        return nation;
    }
    public static void  setNation(String nations){
        nation=nations;
    }
    public Person(String name){
        //记录中包含了声明时候创造的两个参数的构造器
        this(name,0);
    }

    public String getNameToUpperCase(){
        return name.toUpperCase();
    }

    //不可以声明非静态的属性
    //private String noStatic; // X
    //不可以将Record定义的类声明为Abstract
    //不可以给Record定义的类声明显示的父类
}
