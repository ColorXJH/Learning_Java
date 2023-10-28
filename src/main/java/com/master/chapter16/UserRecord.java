package com.master.chapter16;

public record UserRecord(String name,UserRecord parent) {
    //重写多参数的构造器，主要用于多线程，应为参数都是不可变的
    public UserRecord{
        System.out.println("xixixi----6666");
    }
}

/**
 * 这个结构的效果就等同于User类以及其中的模板代码的效果
 * 大幅度的减少的模板代码的书写
 */