package com.master.chapter14;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 反射的应用，动态代理
 * @date 2023-10-13 12:07
 */
public class ProxyTest {
    //静态代理:代理类和被代理类在编译期间就确定了
    @Test
    public void staticProxy(){
        StaticProxy proxy=new StaticProxy(new StaticImpl());
        proxy.produceCloth();
    }
    //动态代理
    @Test
    public void dynamicProxy(){
        ClothFactory proxyInstance = (ClothFactory) DynamicProxy.getProxyInstance(new StaticImpl());
        proxyInstance.produceCloth();

    }
}
//静态代理共同实现的抽象接口
interface ClothFactory{
    void produceCloth();
}
//静态代理类
class StaticProxy implements ClothFactory{
    private  ClothFactory factory;
    public StaticProxy(ClothFactory factory){
        this.factory=factory;
    }
    @Override
    public void produceCloth() {
        System.out.println("------代理工厂做一些准备工作------");
        factory.produceCloth();
        System.out.println("------代理工厂做一些收尾工作------");
    }
}
//被代理类
class StaticImpl implements ClothFactory{

    @Override
    public void produceCloth() {
        System.out.println("耐克工厂生产了一批衣服");
    }
}

//动态代理类:管理所有的被代理类的操作
class DynamicProxy{
    /**
     * 想要实现动态代理需要解决的问题：
     *      1：如何根据加载到内存中的被代理类，动态的创建一个代理类，及其对象
     *      2：当通过代理类的对象调用方法时，如何动态的去调用被代理类的同名方法
     */

    //obj:被代理类的对象，调用此方法，返回一个代理类的对象，解决问题1
    public static Object getProxyInstance(Object obj){
        MyInvocationHandler handler=new MyInvocationHandler();
        handler.setTarget(obj);
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(),handler);

    }
}
//自定义调用类，解决问题2
class MyInvocationHandler implements InvocationHandler{
    private Object target;//需要使用被代理类对象进行实例化
    public void setTarget(Object target){
        this.target=target;
    }
    //当我们通过代理类的对象，调用a方法时，就会自动调用如下方法：invoke
    //将被代理类要执行的方法a的功能就声明在invoke()中
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("---动态代理开始搞事情了---");
        return method.invoke(target,args);
    }
}

/**
 * 代理设计模式的原理
 *      使用一个代理将对象包裹起来，然后使用该代理对象取代原始对象，任何对原始对象的调用都将通过代理，代理对象
 *      决定是否以及何时调用调用原始对象
 *   静态代理：代理类和目标对象的类都是在编译时期确定下来的，不利于程序的扩展，同没一个代理类只能为一个接口服务，这样
 *   程序中将产生很多代理对象，最好使用一个代理类完成全部的代理功能
 *   动态代理：客户通过代理类来调用其他对象的方法，并且是在程序运行时根据需要动态创建目标类的代理对象
 *      使用场景：调试，远程方法调用
 *      优点：抽象角色中（接口）声明的所有方法都被转移到调用处理器一个集中的方法中处理，这样我们可以更加灵活的同意处理众多方法
 *
 *
 */