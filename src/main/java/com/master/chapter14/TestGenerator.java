package com.master.chapter14;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 反射测试总结
 * @date 2023-10-13 9:37
 */
public class TestGenerator {
    @Test
    public void test1() throws ClassNotFoundException {
        //反射获取Class的4种常见方式
        Class clazz=Color.class;
        Class clazz1=Class.forName("com.master.chapter14.Color");
        Color color=new Color();
        Class clazz2=color.getClass();
        Class class3=TestGenerator.class.getClassLoader().loadClass("com.master.chapter14.Color");
    }
    @Test
    public  void test2(){
        //Class的理解
        /**
         *  Class的实例对应一个加载到内存中的运行时类（数组，接口，类本身充当了Class类的实例）
         *  Class类的实例可以获取到class类的各种结构，父类，接口，枚举，注解，域，方法，构造器，创建实例，调用方法，属性，设置可见性
         */
    }
    @Test
    public void test3(){
        //获取类的属性结构
        Class clazz=Color.class;
        Field[] declaredFields = clazz.getDeclaredFields();
        Method[] declaredMethods = clazz.getDeclaredMethods();
        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
        Class superclass = clazz.getSuperclass();
        Class[] interfaces = clazz.getInterfaces();
        Annotation[] annotations = clazz.getAnnotations();
    }

}
