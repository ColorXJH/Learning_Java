package com.master.chapter02;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2023-08-08 14:13
 */
public class Order {
    public static void main(String[] args) {
        Order order=new Order();
        //获取父类方法
        System.out.println(order.getClass().getSuperclass());
    }
}
