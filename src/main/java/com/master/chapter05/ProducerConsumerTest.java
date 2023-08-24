package com.master.chapter05;

/**
 * @ClassName: ProducerConsumerTest
 * @Package: com.master.chapter05
 * @Description: 线程生产者消费者问题  lock方法可以完美取代synchronized方法。
 * @Datetime: 2023/8/24 18:54
 * @author: ColorXJH
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者生产交由服务员 消费者找服务员消费
 * 服务员只能保存20个数据 超过的生产者等等 没有的消费者等等
 * 生产者消费者：多线程
 * 共享商品数据
 * 涉及线程之间的通信
 * 使用同步机制解决线程安全问题
 */
public class ProducerConsumerTest {
    public static void main(String[] args) {
        Clerk clerk=new Clerk();
        Producer producer=new Producer(clerk);
        producer.setName("生产者");
        Consumer consumer=new Consumer(clerk);
        consumer.setName("消费者");
        producer.start();
        consumer.start();

    }
}
/**
 * 服务员
 *
 */
class Clerk{
    private int num=0;
    private Lock lock=new ReentrantLock();
    /**
     * 通过lock得到一个监视器condition。
     */
    Condition condition=lock.newCondition();

    /**
     * synchronized结合wait和notifyAll方法。
     * lock结合await和signalAll方法
     */
    public void produceSomething() {
        lock.lock();
        try{
            if(num<20){
                num++;
                System.out.println(Thread.currentThread().getName()+" 开始生产产品。。。产品数："+num);
                //每生产一个产品就可以 唤醒消费者消费
                //notify();//这里使用必须配合synchronized
                condition.signal();
            }else{
                //等待
                //wait(); //这里使用必须配合synchronized
                condition.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void consumeSomething() {
        lock.lock();
        try {
            if(num>0){
                System.out.println(Thread.currentThread().getName()+" 开始消费产品。。。产品数："+num);
                num--;
                //每消费一个产品就可以唤醒生产者生产
                //notify();//这里使用必须配合synchronized
                condition.signal();
            }else{
                //等待
                //wait();//这里使用必须配合synchronized
                condition.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
/**
 * 生产者
 *
 */
class Producer extends Thread{
    private Clerk clerk;
    public Producer(Clerk clerk){
        this.clerk=clerk;
    }

    @Override
    public void run() {
        System.out.println(getName()+" 开始生产产品。。。。");
        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.produceSomething();
        }
    }
}
/**
 * 消费者
 *
 */
class Consumer extends Thread{
    private Clerk clerk;
    public Consumer(Clerk clerk){
        this.clerk=clerk;
    }

    @Override
    public void run() {
        System.out.println(getName()+" 开始消费产品。。。");
        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.consumeSomething();
        }
    }
}