package com.master.chapter05;

/**
 * @ClassName MyRunable
 * @Package com.master.chapter05
 * @Description
 * @Datetime 2023/8/21 21:07
 * @author ColorXJH
 */
public class MyRunnable{
    public static void main(String[] args) {
        MyThreads thread1=new MyThreads();
        MyThreads thread2=new MyThreads();
        MyThreads thread3=new MyThreads();
        thread1.start();
        thread2.start();
        thread3.start();

        System.out.println("xixixixi---------------------------");

//        MyRun r1=new MyRun();
//        Thread s1=new Thread(r1);
//        Thread s2=new Thread(r1);
//        Thread s3=new Thread(r1);
//        s1.start();
//        s2.start();
//        s3.start();

    }
}

class MyThreads extends Thread {
    //待解决的线程安全问题
    //方法1：同步代码块 synchronized(同步监视器){被同步的代码：操作共享数据的代码，即为需要被同步的代码}
    //方法2：同步方法
    //同步监视器：俗称：锁，任何一个类的对象都可以充当锁
    //共享数据：多个线程共同操作的变量

    //Java语言规范（Java Language Specification，JLS）规定，Java语言中针对long/double型以外的任何变量（包括基础类型变量和引用型变量）进行的读、写操作都是原子操作
    //一个long/double型变量的读/写操作在32位Java虚拟机下可能会被分解为两个子步骤（比如先写低32位，再写高32位）来实现
    private static volatile double ticket = 1000;
    static Object objects=new Object();
    private static int count=0;

    @Override
    public void run() {
//        while (true) {
//            //必须要保证使用的是同一把锁
//            synchronized (objects) {
//                if (ticket > 0) {
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println(Thread.currentThread().getName() + ":ticket=" + ticket);
//                    ticket--;
//
//                } else {
//                    break;
//                }
//            }
//        }
        while (true) {
//            try {
//                //true循环时，一般都是一直抢占资源直到cpu时间段执行完毕，有可能其他线程没有执行的机会，
//                //才需要在这里sleep使线程进入timed-waiting状态,让其他被blocked的线程有机会执行,或者加大次数
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName()+"ssssss");
            doSomeThing();//同步方法思路类似，大致是该方法的锁是同一个
            if(ticket<=0){break;}
        }
        System.out.println("count=:"+count);
    }

    //这里的同步监视器为Class类对象
    public static synchronized void doSomeThing(){
            //必须要保证使用的是同一把锁
            //synchronized (MyThreads.class) {
                if (ticket > 0) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":ticket=" + ticket);
                    ticket--;
                    count++;
                }
            //}
    }
}

class MyRun implements Runnable{
    //待解决的线程安全问题
    //无需static 多个线程共享一个

    //待解决的线程安全问题
    //方法1：同步代码块 synchronized(同步监视器){被同步的代码：操作共享数据的代码，即为需要被同步的代码}
    //方法2：同步方法
    //同步监视器：俗称：锁，任何一个类的对象都可以充当锁
    //共享数据：多个线程共同操作的变量
    private int ticket1=100;
    Object object=new Object();
    @Override
    public void run() {
        while (true) {
            //synchronized (this){
            synchronized (object){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (ticket1 > 0) {
                System.out.println(Thread.currentThread().getName() + ":ticket=" + ticket1);
                ticket1--;
            } else {
                break;
            }
        }
        }
    }
}