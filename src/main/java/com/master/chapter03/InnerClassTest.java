package com.master.chapter03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @ClassName: InnerClassTest
 * @Package: com.master.chapter03
 * @Description: 理解内部类作为类的功能，以及作为外部类成员的功能，就能体会下买你的修饰符，代码块，为什么可以执行了
 * @Datetime: 2023/8/15 21:38
 * @author: ColorXJH
 */
public class InnerClassTest {
    private Integer age;
    public static void main(String[] args) {
        //非静态内部类
        Outer outer=new Outer();
        Outer.Inner inner=outer.new Inner();
        Outer.Inner is=new Outer().new Inner();
        //静态内部类
        Outer.InnerClass innerStaticClass=new Outer.InnerClass(){
            @Override
            public void fly() {
                System.out.println("i can fly");
            }
        };
        innerStaticClass.fly();
        InnerClassTest test=new InnerClassTest();
        Comparator c1=test.getComparator();
        List<Outer> list=new ArrayList<>();
        Outer outer1=new Outer();
        outer1.age=22;
        Outer outer2=new Outer();
        outer2.age=12;
        list.add(outer1);
        list.add(outer2);
        System.out.println("before-----");
        System.out.println(Arrays.toString(list.toArray()));
        list.sort(c1);
        System.out.println("after-----");
        System.out.println(Arrays.toString(list.toArray()));

    }

    //内部类的使用 返回一个实现了Comparable接口的类的对象
    //内置比较器，需要实现接口，无法在外部使用
    public Comparable getComparable(){
        //创建一个实现Comparable接口的类:局部内部类
        //方式一
        class MyComparable implements Comparable<MyComparable>{
            private int age;
            @Override
            public int compareTo(MyComparable o) {
                return this.age-o.age;
            }
        }
        //return new MyComparable();
        //方式二
        return new Comparable() {
            @Override
            public int compareTo(Object o) {
                return 0;
            }
        };

    }
    //独立出来的比较器，可以在外部使用
    public Comparator getComparator(){
        //方式1 内部类

//        class MyComparator implements Comparator<Outer>{
//
//            @Override
//            public int compare(Outer o1, Outer o2) {
//                return o1.age-o2.age;
//            }
//        }
//        return new MyComparator();
        //方式2

        return new Comparator<Outer>() {
            @Override
            public int compare(Outer o1, Outer o2) {
                return o1.age- o2.age;
            }
        };
    }

}
class Outer{
    private String name;
    int age;
    //成员内部类

    protected final class Inner{
        private String name;
        public void display(String name){
            show();
            //Outer.this  外部类的方法
            Outer.this.display();
            System.out.println(name);
            System.out.println(this.name);
            System.out.println(Outer.this.name);
        }
        public void show(){

        }
    }
    abstract static class InnerClass{
        String name;
        int age;
        public InnerClass(){}
        public abstract  void fly();
    }

    public void show(){
        //局部内部类(方法内 代码块内，构造器内)
        int namess=10;
        class Test{
            public void showsss(){
                //在局部内部类的方法中，如果要使用外部类的变量，则这个变量需要时final的，java8 之后省略了这个final（8之前需要显示声明final）,但是她依然是final，只是没有写出来，修改它的值依然会报错
                //why？对于作用域而言是可以使用外部的变量，但是作为类而言，他们是两个字节码文件，脱离了一个文件体系，导致只能给你用不能改
                System.out.println(namess);
            }
        }
    }
    //代码块
    {
        class BB{

        }
    }
    //构造器
    public Outer(){
        class CC{

        }
    }

    public void display(){}

    @Override
    public String toString() {
        return "Outer{" +
                "age=" + age +
                '}';
    }
}