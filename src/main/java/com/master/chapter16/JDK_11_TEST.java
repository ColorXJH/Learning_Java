package com.master.chapter16;


/**
 * @ClassName: JDK_11_TEST
 * @Package: com.master.chapter16
 * @Description:
 * @Datetime: 2023/10/23 0:04
 * @author: ColorXJH
 */
//无论类放在哪里，启动的时候都是启动public类
class Teacher{
    private String name;
    private int age;

    public static void main(String[] args) {
        System.out.println("谁放在上面就执行谁xixix---");
        //只能当前源文件的类
        Pss ps=new Pss();
        //不能引用其他源文件中的类
        //Student student=new Student();
        //System.out.println(student);
        System.out.println(ps);
    }
}
public class JDK_11_TEST {
    public static void main(String[] args) {
        System.out.println("--color-xjh--");
        Teacher teacher=new Teacher();

    }
}

class Pss{}

