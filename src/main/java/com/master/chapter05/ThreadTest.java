package com.master.chapter05;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 多线程案例
 * @date 2023-08-18 16:42
 */
public class ThreadTest {
    public static void main(String[] args) {
        MyThread myThread=new MyThread();
        myThread.start();
        System.out.println("main start---");
    }
}

class MyThread extends Thread{
    @Override
    public void run() {
        System.out.println("thread start---");
    }
}
