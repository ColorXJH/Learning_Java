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
//        MyThreads thread1=new MyThreads();
//        MyThreads thread2=new MyThreads();
//        MyThreads thread3=new MyThreads();
//        thread1.start();
//        thread2.start();
//        thread3.start();

        MyRun r1=new MyRun();
        Thread s1=new Thread(r1);
        Thread s2=new Thread(r1);
        Thread s3=new Thread(r1);
        s1.start();
        s2.start();
        s3.start();

    }
}

class MyThreads extends Thread{
    //待解决的线程安全问题

    private static  int ticket=100;
    @Override
    public void run() {
        while (true){
            if(ticket>0){
                System.out.println(Thread.currentThread().getName()+":ticket="+ticket);
                ticket--;
            }else{
                break;
            }
        }

    }
}

class MyRun implements Runnable{
    //待解决的线程安全问题

    private int ticket1=100;
    @Override
    public void run() {
        while (true){
            if(ticket1>0){
                System.out.println(Thread.currentThread().getName()+":ticket="+ticket1);
                ticket1--;
            }else{
                break;
            }
        }
    }
}