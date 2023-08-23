package com.master.chapter05;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: LockDemoTest
 * @Package: com.master.chapter05
 * @Description: 显示定义同步锁对象来实现同步 Lock JDK5.0新增方式   synchronized:在执行完响应代码块之后自动释放锁 ，lock手动释放
 * @Datetime: 2023/8/23 23:31
 * @author: ColorXJH
 */
public class LockDemoTest {
    public static void main(String[] args) {
//        Window w=new Window();
//        Thread t1=new Thread(w);
//        Thread t2=new Thread(w);
//        Thread t3=new Thread(w);
//        t1.setName("窗口1");
//        t2.setName("窗口2");
//        t3.setName("窗口3");
//        t1.start();
//        t2.start();
//        t3.start();
// 建议使用上方形式

        Windows w1=new Windows();
        Windows w2=new Windows();
        Windows w3=new Windows();
        w1.setName("Thread1");
        w2.setName("Thread2");
        w3.setName("Thread3");
        w1.start();
        w2.start();
        w3.start();
    }
}

class Window implements Runnable{
    private int ticket=1000;
    //实例化一个lock
    private ReentrantLock lock=new ReentrantLock();
    @Override
    public void run() {
        while (true){
            lock.lock();
            try {
                if(ticket>0){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"售票，售票号为："+ticket);
                    ticket--;
                }else {
                    break;
                }
            }finally {
                lock.unlock();
            }
        }
    }
}

class Windows extends Thread{
    private static int tickets=1000;
    private static Lock locks=new ReentrantLock();
    @Override
    public void run() {
        while (true){
            locks.lock();
            try {
                if(tickets>0){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"售票，售票号为："+tickets);
                    tickets--;
                }else {
                    break;
                }
            }finally {
                locks.unlock();
            }
        }
    }
}