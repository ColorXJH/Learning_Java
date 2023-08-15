package com.master.chapter03;

/**
 * @ClassName: InterfaceTestA
 * @Package: com.master.chapter03
 * @Description:
 * @Datetime: 2023/8/15 20:25
 * @author: ColorXJH
 */
public class InterfaceTestA {
    public static void main(String[] args) {
        C c=new C();

        //就近原则

        System.out.println(c.x);
        E e=new E();
        //这里无法区分 类和接口是同级别的

        //System.out.println(e.x);
        e.show();

    }
}

class A{
    int x=5;
}
class B extends A{
    int x=7;
}
class C extends B{

}

interface D{
    int x=115;
}

class E extends C implements D{
    public void show(){
        //调用父类和接口同类型的属性区分
        System.out.println(super.x);
        System.out.println(D.x);
    }

}