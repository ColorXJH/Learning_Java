package com.master.chapter03;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName: DynamicProxy
 * @Package: com.master.chapter03
 * @Description:
 * @Datetime: 2023/8/16 19:33
 * @author: ColorXJH
 */
public class DynamicProxy {
    public static void main(String[] args) {
        Person person=new PersonImpl();
        //只能代理接口，不能代理类
        Person proxy = (Person) Proxy.newProxyInstance(person.getClass().getClassLoader(), person.getClass().getInterfaces(), new MyInvocationHandler(person));
        proxy.show();
    }
}


class MyInvocationHandler implements InvocationHandler{
    private Object target;
    public MyInvocationHandler(Object target){
        this.target=target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("--before--");
        Object invoke = method.invoke(target, args);
        System.out.println("--after--");
        return invoke;
    }
}
interface Person{
    public void show();
}

class PersonImpl implements Person{
    private String name="color-xjh";
    @Override
    public void show() {
        System.out.println("this is my show:"+name);
    }
}