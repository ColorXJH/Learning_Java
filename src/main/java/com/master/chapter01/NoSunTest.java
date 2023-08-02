package com.master.chapter01;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2023-08-02 15:32
 */
public class NoSunTest {
    public static void main(String[] args) {
        Father father=new Father();
        //被保护的方法只能在本包中实例调用
        father.sayHello();
    }
}
