package com.master.chapter05;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 线程间通信：两个线程交替打印1-100的数字
 * @date 2023-08-24 14:33
 */
/**
 * 这里设计三个方法
 * wait() 释放锁，释放该对象监视器，使当前线程进入waiting状态
 * notify() 唤醒在该对象监视器上的某个线程，使其进入runnable状态，后续可以和其他线程一起竞争对象监视器（锁）
 * notifyAll() 唤醒所有在该对象监视器上waiting的线程，是他们进入runnable状态以便后续竞争锁
 * 以上三个方法必须使用在同步代码块或者同步方法中,且他们的调用者必须是同步代码块/方法中的同步监视器（唯一的，或者this对象），否则会出现
 * IllegalMonitorStateException异常
 *
 * 这三个方法是定义在Object类中的，同步监视器任何一个对象都可以充当，则任何一个对象必须有这三个方法，所以在Object类中定义
 *
 * sleep和wait方法的异同：
 * 1：相同 一旦执行方法，
 * 2：不同 wait方法调用是让线程进入waiting状态，释放锁，sleep是让线程进入blocked状态,它可以持有锁也可以不持有锁，只是让出cpu时间片，不释放锁（如果有的话）
 * 同时他们的对象也不一样，sleep属于Thread方法，wait属于Object方法，sleep可以在任何地方使用，因为他不是必须持有锁，wait必须在同步代码块中使用，
 * sleep使线程进入阻塞状态（线程睡眠），wait使线程进入等待队列，（线程挂起）
 */
public class ThreadCommunicationTest {
    public static void main(String[] args) throws InterruptedException {
        Number number=new Number();
        Thread t1=new Thread(number);
        Thread t2=new Thread(number);
        t1.setName("甲");
        t2.setName("乙");
        t1.start();
        t2.start();
        System.out.println("----------------------------");
        Thread t3=new Thread();
        Thread t4=new Thread();
        t3.join();
        t4.join();
        t3.start();
        t4.start();
        System.out.println("t3"+t3.getState());
        System.out.println("t4"+t4.getState());
    }
}
//waiting 状态会释放锁，blocked状态为抢占锁（获取同步监视器失败）而阻塞

class Number implements Runnable{
    private int number=1;
    @Override
    public void run() {
        while (true){
            synchronized (this){
                //唤醒这个同步监视器上的一个被waiting的另一个线程，因为此时已经获得锁，所以另一个线程抢不到锁，进入阻塞的状态
                this.notify();
                if(number<=100){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+":"+number);
                    number++;
                    //使得调用如下wait()方法的线程进入阻塞状态：1 先释放锁，进入waiting,然后被唤醒之后（notifyAll/notify）准备重新进入同步代码块，此时属于阻塞Blocked状态
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    break;
                }
            }
        }
    }
}