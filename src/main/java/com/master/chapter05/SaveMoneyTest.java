package com.master.chapter05;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 多储户存钱问题：两个储户分别向同一个账户存3000，每次存1000，存3此，每次存完打印账户余额
 * @date 2023-08-24 13:28
 */
public class SaveMoneyTest {
    public static void main(String[] args) {
        Account account=new Account(0);
        Customer c1=new Customer(account);
        Customer c2=new Customer(account);
        c1.setName("甲");
        c2.setName("乙");
        c1.start();
        c2.start();
    }
}
//是否是多线程问题：是 两个储户
//是否有共享数据：有 共享一个账户余额
//是否存在线程安全问题：两个用户操作同一个共享数，
//需要考虑如何解决： 同步机制 三种方式 synchronized方法、代码块 Lock锁

class Account{
    private double balance;
    public Account(double balance){
        this.balance=balance;
    }
    Lock lock=new ReentrantLock();
    /**
     * 同步方法或者代码块synchronized 继承方式要慎用this
     * 或者使用Lock锁：try-finally
     */
    public  void addMoney(double money){
        lock.lock();
        try {
            if(money>0){
                balance+=money;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"存钱成功，余额为："+balance);
            }
        }finally {
            lock.unlock();
        }

    }
}
class Customer extends Thread{
    private Account account;
    public Customer(Account account){
        this.account=account;
    }
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            account.addMoney(1000);
        }
    }
}
