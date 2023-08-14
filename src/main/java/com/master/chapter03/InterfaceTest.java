package com.master.chapter03;

/**
 * @ClassName: InterfaceTest
 * @Package: com.master.chapter03
 * @Description:
 * @Datetime: 2023/8/14 21:03
 * @author: ColorXJH
 */
public class InterfaceTest {
    public static void main(String[] args) {
        Flyable flyable=new BirdFly();
        flyable.stop();
    }
}

interface Flyable{
    public static final int MAX_SPEED=96800;
    //省略public static final

    int MIN_SPEED=97;
    public abstract void fly();
    //省略public abstract

    //注意接口中不能定义构造器

    void stop();

    //JDK8及以后的接口可以定义静态方法和默认方法

    public static void staticMethod(){
        System.out.println("这是一个静态方法");
    }
    public  default void defaultMethod(){
        System.out.println("这是一个默认方法");
    }
}
//接口之间可以多继承

interface AA{}
interface BB{}
interface CC extends AA,BB{}

interface Flyable2{
   void  dispaly();
}

class BirdFly implements Flyable{

    @Override
    public void fly() {
        System.out.println("鸟儿飞");
    }

    @Override
    public void stop() {
        System.out.println("鸟儿降落");
        defaultMethod();
    }
}

//如果没有完全重写抽象方法则为抽象类

abstract class BirdFly2 implements Flyable{

}

abstract class Action{
    abstract void play();
}

class MyFlay extends Action implements Flyable,Flyable2{

    @Override
    public void fly() {

    }

    @Override
    public void stop() {

    }
    //默认方法也可以重写

    @Override
    public void defaultMethod() {
        Flyable.super.defaultMethod();
    }

    @Override
    public void dispaly() {

    }

    @Override
    void play() {

    }
}