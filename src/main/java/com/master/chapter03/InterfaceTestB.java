package com.master.chapter03;

/**
 * @ClassName: InterfaceTestB
 * @Package: com.master.chapter03
 * @Description:
 * @Datetime: 2023/8/15 20:42
 * @author: ColorXJH
 */
public class InterfaceTestB {
    public static void main(String[] args) {
        Dplay dplay=new Dplay();
        dplay.showss();
    }
}

interface Playable{
    void play();
    public static void shows(){
        System.out.println("playable shows");
    }
    public default void showss(){
        System.out.println("show-names");
    }
}
interface  BPlayable{
    void play();
}
interface CPlayable extends Playable,BPlayable{
    Dplay dplay=new Dplay();
}
abstract class ABS{
    public void showss() {
        System.out.println("ABS-showwssss");
    }
}

class Dplay extends ABS implements CPlayable{

    @Override
    public void play() {
        //dplay为一个final属性，不能被重新赋值  public static final 被省略了
        //dplay=new Dplay();
        System.out.println("hello");
        showss();
        //调用接口中的同名的默认方法
        CPlayable.super.showss();
    }
}
//接口中的静态方法只能用接口调用     默认方法通过实现类调用  互斥
//如果子类、实现类继承的父类和实现的接口中声明了同名同参数的方法(不管接口中的是抽象方法还是默认方法)，那么子类在没有重写此方法的情况下，默认调用的是父类中的方法（类有优先原则）
