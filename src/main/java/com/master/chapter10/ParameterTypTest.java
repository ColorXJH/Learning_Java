package com.master.chapter10;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: java的范型
 * @date 2023-09-08 15:14
 */
public class ParameterTypTest {
    public static void main(String[] args) {
        ParameterTypTest test=new ParameterTypTest();
        test.test1();
    }
    public void test1(){
        ArrayList list=new ArrayList();
        list.add(123);
        list.add(234);
        list.add(345);
        list.add("ggg");
        for (Object obj: list) {
            //类型转换异常
            System.out.println((int)obj);
        }
    }

    public void test2(){
        Order order=new Order();
        //order 如果没有增加泛型则认为此泛型类型为Object类型，所以需要实例化时带上类型
        order.setT("123");
        order.setT(123);
    }
    public void test3(){
        List<? extends Person> list1=new ArrayList<>();
        List<? super Person>list2=new ArrayList<>();
        //编译错误，无法添加数据，因为Person是上限，泛型类型的子类是不确定的，无法制造多态性
        //list1.add(new Person());
        //可以读数据，泛型类型上线是Person，即父类是确定的。可以强转为该类
        Person person = list1.get(0);
        Object object = list1.get(0);
        //可以添加数据，因为下限是Person,泛型类型的子类是确定的，可以添加Person及其子类，根据多态性都能转化为Person及其父类
        list2.add(new Person());
        list2.add(new Student());
        //可以读数据，泛型类型的父类不确定，打死你hi所有类的父类都是Object，所以可以使用Object接受
        Object object1 = list2.get(0);
        //总结，都可以读数据Object 但是extend可以读取上限，对于写数据 super可以写入下限
    }
}

class Person{

}
class Student extends Person{

}

/**
 * 为什么需要范型
 * 集合中限制类型存入 防止不同类型的强制类型转换
 * 范型在底层使用的就是Object[]
 */


/**
 * 如何自定义泛型类 泛型接口 泛型方法 这些泛型结构
 *
 */

class Order<T>{
    String name;
    int orderId;
    //类的累不结构就可以使用类的泛型
    T t;
    public Order(){

    }

    public Order(String name,int orderId,T t){
        this.name=name;
        this.orderId=orderId;
        this.t=t;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}

//此时继承了父类，父类制定了泛型类型，子类不再需要指定泛型
class SubOrder extends Order<Integer>{

}
//子类不保留父类的泛型，相当于类型擦除//class SubsOrder extends Order<Object>
class SubsOrder extends Order{

}
//此时子类必须声明泛型类，因为父类无法去定泛型类型
class SubOrder1<T> extends Order<T>{

}

class Orderss<K,V>{}
//部分保留
class SubOrderss<K> extends Orderss<K,Integer>{

}
//全部保留
class SubOrdersss<K,V>extends Orderss<K,V>{}
//具体类型
class Subssss extends  Orderss<String,Integer>{}


class TETST1{
    //泛型方法和是不是泛型类没有啥关系
    public <E> E getValue(){
        return null;
    }
}
/**
 * 泛型不同的引用不能相互赋值
 * 虽然在运行的时候他们都是当作Object类型来处理
 * 但是在各自的类型时候，有类型限定，可能会有不同方法不兼容
 * 泛型结构的接口和抽象类不能造对象，因为他们是接口和抽象类。。。这个不是新特性
 * 类、接口上的泛型，在本类、接口中代表某种类型，可以作为非静态属性的类型，非静态方法的参数类型，非静态方法的返回值类型，但是在静态方法中不能使用泛型
 * 异常类不能是泛型的
 * 不能使用new T[] 但是可以 T[] element=(T[])new Object[capacity],参考ArrayList的源码声明 Object[] elementData 而非泛型参数类型的数组
 * 因为本质上来说类型参数是还没去确定的一个类型，属于运行时确定的一个类型，在我们创建对象时需要一个指定的类型，使用Object[]，然后强转成运行时需要的对象类型数组是一个可行的办法
 *
 */


/**
 * 泛型的通配符
 * ? 通配符 任意类型
 * <? extends Number> extends 上限 (无穷小, Number] 只允许泛型为Number类及Number类的子类调用
 * <? super Number> super 下限 [Number,无穷大) 只允许泛型为Number类及Number类的父类调用
 * <? extends Comparable> 只允许泛型为实现Comparable接口的实现类的引用调用
 */