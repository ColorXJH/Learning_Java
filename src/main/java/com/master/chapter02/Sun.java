package com.master.chapter02;

import com.master.chapter01.Father;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2023-08-02 15:31
 */
public class Sun extends Father {
    public static void main(String[] args) {
        Father father=new Father();
        Sun sun=new Sun();
        //被保护的方法在不同的包中可以由继承调用
        sun.sayHello();
    }
}
