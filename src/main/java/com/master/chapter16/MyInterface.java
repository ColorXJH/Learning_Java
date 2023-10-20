package com.master.chapter16;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: jdk9的私有方法
 * @date 2023-10-20 10:50
 */
public interface MyInterface {
    void show();
    //default表示关键字，并不是表示默认权限，他的权限还是public
    public default void playDefault(){
        System.out.println("jdk8的默认方法");
    }
    static void sayStaticHi(){
        System.out.println("jdk8的静态方法");
    }
    private static void displayPrivate(){
        System.out.println("这是jdk9的私有方法，抽象方法不能私有");
    }
}
