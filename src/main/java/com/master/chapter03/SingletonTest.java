package com.master.chapter03;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 单例设计模式
 * @date 2023-08-10 14:55
 */
public class SingletonTest {
}

class Bank {
    //私有化构造器

    private Bank(){

    }
    //提供实例得静态方法

    public static Bank getInstance(){
        return INSTANCE;
    }
    //提供静态得成员变量

    private static final Bank INSTANCE=new Bank();
}

class Bank2{
    private Bank2(){}
    public static Bank2 getInstance(){
        if(INSTANCE ==null){
            INSTANCE=new Bank2();
        }
        return INSTANCE;
    }
    private static Bank2 INSTANCE;
}

//线程安全得单例方法

class Singleton{
    private Singleton(){}
    private volatile static Singleton INSTANCE;
    public static Singleton safeGetInstance(){
        if(INSTANCE==null){
            synchronized (Singleton.class){
                if(INSTANCE==null){
                    INSTANCE=new Singleton();
                }
            }
        }
        return INSTANCE;
    }
}
