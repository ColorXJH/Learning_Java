package com.master.chapter05;

/**
 * @ClassName: CallableTest
 * @Package: com.master.chapter05
 * @Description: jdk5.0新增的创建线程的方式
 * @Datetime: 2023/8/24 20:33
 * @author: ColorXJH
 */

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 与runnable相比： call()方法可以有返回值，
 * 方法可以抛出异常，run()方法有异常只能try-catch,处理完之后抛出的异常无法被外部接受处理
 * 支持泛型的返回值
 * 借助FutureTask类(Future接口的唯一实现类)，获取返回结果
 */
public class CallableTest {
    public static void main(String[] args) {
        //以上无法被捕获
//        Thread thread=new Thread(new MyRuns());
//        thread.start();
//        thread.interrupt();
//        System.out.println("--------------------------------------------------------------------");
        //以下可以被捕获 --runnable改造
        MyUncaughtExceptionHandler myUncaughtExceptionHandler=new MyUncaughtExceptionHandler();
        Thread threads=new Thread(new MyRuns2());
        threads.setUncaughtExceptionHandler(myUncaughtExceptionHandler);
        threads.start();
        threads.interrupt();




        //以下Callable方式
        /**
         * 3:创建Callable接口实现类的对象
         */
        NumThread numThread=new NumThread();
        //构造函数可传递Runnable Callable
        /**
         * 4:讲此callable对象作为参数传递到FutureTask构造方法中
         */
        FutureTask<Integer> futureTask = new FutureTask(numThread);
        //FutureTask类实现了Runnable接口， 可以作为线程参数方法执行
        /**
         * 5:将futureTask作为参数传递到Thread，创建线程，调用start
         */
        new Thread(futureTask).start();
        try {
            //FutureTask实现了Future接口，可以返回
            //get()方法返回值即为重写Callable接口call方法的返回值
            /**
             * 6:获取Callable中call方法的返回值
             */
            Integer sum = futureTask.get();
            System.out.println("总和为："+sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}


class MyRuns implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(100);
            throw new RuntimeException("实现runnable接口的方法抛出的异常-try代码块中--无法被主线程捕获-即使主线程添加了try-catch方法");
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("实现runnable接口的方法抛出的异常-catch代码块中无法被主线程捕获-即使主线程添加了try-catch方法");
        }
    }
}

class MyRuns2 implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(100);
            throw new RuntimeException("实现runnable接口的方法抛出的异常-try代码块中--可以被实现了UncaughtExceptionHandler的类捕获");
        } catch (InterruptedException e) {
            //e.printStackTrace();
            System.out.println("线程中断的异常被捕获到了"+e.getMessage()+" 然后将其向上抛出到主线程main===>");
            throw new RuntimeException("实现runnable接口的方法抛出的异常-catch代码块中--可以被实现了UncaughtExceptionHandler的类捕获");
        }
    }
}

class MyUncaughtExceptionHandler implements  Thread.UncaughtExceptionHandler{

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("name:"+t.getName()+" error:"+e.getMessage());
    }
}


/**
 * Callable模式
 *
 */
/**
 * 1:创建一个实现了Callable接口的实现类
 */
class NumThread implements Callable<Integer>{
    /**
     * 2:实现call方法，讲此线程需要执行的操作声明在该方法中
     */
    @Override
    public Integer call() throws Exception {
        int sum=0;
        for (int i = 1 ; i <=100 ; i++) {
            if(i%2==0){
                System.out.println(i);
                sum+=i;
            }
        }
        return sum;
    }
}