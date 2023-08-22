package com.master.chapter05;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 多线程案例
 * @date 2023-08-18 16:42
 */
public class ThreadTest {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getPriority());
        MyThread myThread=new MyThread();
        //方式1
        myThread.setPriority(10);
        myThread.start();
        System.out.println("main start---");
        //方式二
        new Thread(){
            @Override
            public void run() {
                System.out.println("创建类的匿名子类的匿名对象执行线程");
                System.out.println(Thread.currentThread().getPriority());
            }
        }.start();
        for (int i=0;i<100;i++){
            System.out.println(Thread.currentThread().getName());
        }
    }
}

class MyThread extends Thread{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getPriority());
        System.out.println("thread start---");
        for (int i=0;i<100;i++){
            System.out.println(Thread.currentThread().getName());
        }
    }
}
