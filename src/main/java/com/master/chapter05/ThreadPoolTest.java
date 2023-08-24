package com.master.chapter05;

import java.util.concurrent.*;

/**
 * @ClassName: ThreadPoolTest
 * @Package: com.master.chapter05
 * @Description:
 * @Datetime: 2023/8/24 21:35
 * @author: ColorXJH
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        /*
        //Positive example 1：
        //org.apache.commons.lang3.concurrent.BasicThreadFactory
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
        //Positive example 2：
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();

        //Common Thread Pool
        ExecutorService pool = new ThreadPoolExecutor(5, 200,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        pool.execute(()-> System.out.println(Thread.currentThread().getName()));
        pool.shutdown();//gracefully shutdown
        */

        /**
         * 1:创建指定线程数的线程池
         */
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        //设置线程池接口实现类的一些属性
        executorService.setCorePoolSize(10);
        executorService.setKeepAliveTime(10,TimeUnit.SECONDS);
        executorService.setMaximumPoolSize(20);
        /**
         * 2:执行指定的线程操作
         */
        //适用于runnable
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println(i);
                }
            }
        });
        //适用于callable
        try {
            int x=executorService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return 20;
                }
            }).get();
            System.out.println("得到的结果为："+x);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        /**
         * 3:关闭线程池
         */
        executorService.shutdown();
    }
}
/**
 * jdk5 提供了线程池的相关api:ExecutorService(线程池接口) 和 Executors(线程池的工具类)
 * 好处：提高响应速度，减少创建新线程的时间； 降低资源消耗，重复利用线程池中的资源，无需每次重新创建；便于线程管理
 * 综合下来创建多线程有四种方式： 1：继承Thread 2：实现Runnable接口 3：实现Callable接口 4：使用线程池
 */