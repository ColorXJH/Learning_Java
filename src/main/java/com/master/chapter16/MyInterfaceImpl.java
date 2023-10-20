package com.master.chapter16;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2023-10-20 10:56
 */
public class MyInterfaceImpl implements MyInterface{
    //重写抽象方法和默认方法
    @Override
    public void show() {
        System.out.println("实现类实现抽象方法");
    }

    @Override
    public void playDefault() {
        System.out.println("实现类重写默认方法");
        MyInterface.super.playDefault();
    }

    public static void main(String[] args) {
        //接口中的静态方法只能由自己调用,接口的实现类不能调用
        MyInterface.sayStaticHi();
        //实现类调用默认方法于抽象方法实现
        MyInterfaceImpl impl=new MyInterfaceImpl();
        impl.playDefault();
        impl.show();
        //实现类不能调用接口的私有方法
        //impl.displayPrivate(); //会出现错误
        //钻石操作符的升级使用
        Comparable<Object> comparable=new Comparable<>() {
            @Override
            public int compareTo(Object o) {
                return 0;
            }
        };
    }
}
