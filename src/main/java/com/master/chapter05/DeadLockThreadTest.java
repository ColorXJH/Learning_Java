package com.master.chapter05;

/**
 * @ClassName: DeadLockThreadTest
 * @Package: com.master.chapter05
 * @Description: 线程死锁问题演示:对于两个同步监视器，两个线程拿的顺序不一致就会导致死锁，A线程先拿a再拿b B线程先拿b再拿a 导致他们互相都在等待对方
 * @Datetime: 2023/8/23 23:01
 * @author: ColorXJH
 */
public class DeadLockThreadTest {
    public static void main(String[] args) {
        StringBuffer s1=new StringBuffer();
        StringBuffer s2=new StringBuffer();
        new Thread(){
            @Override
            public void run() {//手握s1等待s2
                synchronized (s1){
                    s1.append("a");
                    s2.append("1");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (s2){
                        s1.append("b");
                        s2.append("2");
                        System.out.println(s1);
                        System.out.println(s2);
                    }
                }
            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {//手握s2等待s1
                synchronized (s2){
                    s1.append("c");
                    s2.append("3");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (s1){
                        s1.append("d");
                        s2.append("4");
                        System.out.println(s1);
                        System.out.println(s2);
                    }
                }
            }
        }).start();

    }
}
