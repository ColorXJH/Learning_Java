package com.master.chapter14;

import org.junit.Test;

/**
 * @ClassName: ReflectTest
 * @Package: com.master.chapter14
 * @Description: java的反射
 * @Datetime: 2023/10/9 20:29
 * @author: ColorXJH
 */
public class ReflectTest {
    public static void main(String[] args) {

    }

    //反射之前对于Person类的操作
    @Test
    public void beforeReflect(){
        //1 创建Person类的对象
        Person person=new Person("Tom",12);
        //2 通过对象，调用其内部的属性和方法
        person.age=10;
        System.out.println(person.toString());
        person.show();
        //在person类外部，不可以通过其对象 调用其内部私有的结构，比如私有方法，私有变量 私有的构造器，受到封装性的限制
    }
}

/**
 * 反射被视为动态语言的关键，反射机制允许程序在执行期间借助Reflection API取得任何类的内部信息，并能直接操作任意对象的内部属性及方法
 * 加载完成类之后，在堆内存的方法区中就产生了一个Class类型的对象（一个类只有一个Class对象），这个对象就包含了完整的类的结构信息，我们可以通过
 * 这个对象看到类的结构，这个对象就像一面镜子，透过这个镜子可以看到类的结构，所以我们称之为：反射
 *  正常方式：引入需要的“包类”名称-》通过new实例化-》取得实例化对象
 *  反射方式：实例化对象-》getClass()方法-》得到完整的“包类”名称
 *      反射机制研究及应用
 *          1：在运行时判断任意一个对象所属的类
 *          2：在运行时构造任意一个类的对象
 *          3：在运行时判断任意一个类所具有的成员变量和方法
 *          4：在运行时获取泛型信息
 *          5：在运行时调用任意一个对象的成员变量和方法
 *          6：在运行时处理注解
 *          7：生成动态代理
 *              主要相关API:
 *                  java.lang.Class:代表一个类
 *                  java.lang.reflect.Method:代表类的方法
 *                  java.lang.reflect.Field:代表类的成员变量
 *                  java.lang.reflect.Constructor:代表类的构造器
 */