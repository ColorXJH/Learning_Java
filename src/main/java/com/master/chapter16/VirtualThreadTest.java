package com.master.chapter16;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: jdk虚拟线程-协程 有栈线程
 * @Author: ColorXJH
 * @CreateDate: 2023/10/28 15:42
 * @Version: 1.0
 */
public class VirtualThreadTest {
    public static void main(String[] args) throws InterruptedException {
        //ExecutorService executorService= Executors.newFixedThreadPool(1);
        //虚拟线程
        ExecutorService executorService= Executors.newVirtualThreadPerTaskExecutor();
        //vm -option设置：-Djdk.virtualThreadScheduler.parallelism=1  -Djdk.virtualThreadScheduler.maxPoolSize=1 保证核心线程 最大线程都是1
        executorService.execute(new MyTask());
        executorService.execute(new MyTask());
        executorService.execute(new MyTask());
        executorService.execute(new MyTask());
        executorService.execute(new MyTask());
        //使用线程执行效果来看相当于睡了5秒，应为执行前面的线程池花费了5秒
        //如果使用协程，则现象显示则表现为10秒，因为一瞬间完成了
        Thread.sleep(Duration.ofSeconds(10));
        executorService.shutdown();

    }

    static class MyTask implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread());
            sleep();
            System.out.println(LocalDateTime.now());
        }


        void sleep(){
            try {
                Thread.sleep(Duration.ofSeconds(1));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}


