package com.master.chapter16;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.SequencedCollection;
import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * @Description: jdk16新特性
 * @Author: ColorXJH
 * @CreateDate: 2023/10/28 14:44
 * @Version: 1.0
 */
public class JDK16_21Test {
    //jdk21的新特性
    //1:作用域值
    //ThreadLocal中我们对一个值去设置和修改，需要我们手动控制，可能我们需要在main中set,其他地方我们只允许获取，不允许修改值
    public static ThreadLocal<String>NAME=new ThreadLocal<>();
    //使用Scoped Value
    public static ScopedValue<String>NAME1=ScopedValue.newInstance();
    //java.lang.ScopedValue 是预览 API，默认情况下处于禁用状态。 （请使用 --enable-preview 以启用预览 API） 在setting的java的编译器里面的additional commandline parameter里面加
    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        //测试1：
        /*NAME.set("color");
        //给NAME1绑定了一个字符串“colorxjh”,这个绑定关系只在a方法执行的时候是有用的
        ScopedValue.runWhere(NAME1,"ColorXJH",JDK16_21Test::a);
        //超过昂啊之外是拿不到值的,报错
        System.out.println("a方法之外：：：："+NAME1.get());
        a();*/


        //测试2
        //testConcurrent();

        //测试3；虚拟线程
        virtualThread();

        //测试4：新定义声明了顺序集合的接口
        SequencedCollection collection=new ArrayList();
    }
    public static void a(){
        //这里只是在调用的时候修改了，应为这个值是绑定到了b的方法--》说明当前链条的值不能修改，如果修改也是重新开了一个链条的作用域
        ScopedValue.runWhere(NAME1,"ColorXJHaaa",JDK16_21Test::b);
        //只能在a方法中拿到相应的值
        System.out.println("a方法内部:::"+NAME1.get());
        b();
        System.out.println(NAME.get());
    }
    public static void b(){
        //如果a方法调用了b,则在b的内部也是可以拿到的，存在一个调用链条(属于a的调用栈，不同于thread local 只要是同一个线程都可以拿到)
        System.out.println("b方法内部:::"+NAME1.get());
        System.out.println(NAME.get());
        //没有此操作可能造成内存浪费
        NAME.remove();
    }

    //2：结构化并发（用到了虚拟线程）
    public static void testConcurrent() throws ExecutionException, InterruptedException, IOException {
        /*ExecutorService service= Executors.newFixedThreadPool(2);
        Future<String> user = service.submit(new GetUserTask());
        Future<String> order = service.submit(new GetOrderTask());
        String user_query = user.get();//阻塞获取用户信息
        var order_query = order.get();//阻塞获取订单信息
        System.out.println(user_query+order_query);
        service.shutdown();*/
        //结构化代码的优点
//        StructuredTaskScope.ShutdownOnFailure scope=new StructuredTaskScope.ShutdownOnFailure();//当有任务执行失败时终止
        StructuredTaskScope.ShutdownOnSuccess scope=new StructuredTaskScope.ShutdownOnSuccess();//当有任务执行成功时终止
        Supplier<String>order=scope.fork(new GetOrderTask());
        Supplier<String>user=scope.fork(new GetUserTask());
        scope.join();
        String user_query= user.get();
        String order_query= order.get();
        System.out.println(user_query+order_query);
//        scope.shutdown();
    }

    static class GetUserTask implements Callable<String>{

        @Override
        public String call() throws Exception {
            try {
                System.out.println("查询user开始");
                TimeUnit.SECONDS.sleep(1);
//                throw new RuntimeException("程序执行出错");
                System.out.println("查询user结束 ");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "user-query";
        }
    }
    static class GetOrderTask implements Callable<String>{

        @Override
        public String call() throws Exception {
            try {
                System.out.println("查询order开始");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("查询order结束 ");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "order-query";
        }
    }

    //3：虚拟线程
    public static void virtualThread() throws IOException {
        //选择1
        Thread thread = Thread.ofVirtual().name("ColorXJH").unstarted(new Tasks123());
        thread.start();
        //选择2
        Thread.ofVirtual().name("XJH").start(new Tasks123());
        //选择3
        ThreadFactory factory=Thread.ofVirtual().name("ace").factory();
        Thread thread1 = factory.newThread(new Tasks123());
        thread1.start();
        //选择4
        Thread.startVirtualThread(new Tasks123());
        //选择5：结构化并发用的也是虚拟线程
        StructuredTaskScope.ShutdownOnFailure scope=new StructuredTaskScope.ShutdownOnFailure();
        scope.fork(()->{
            System.out.println("------结构化并发-------");
            System.out.println(Thread.currentThread());
            return "success";
        });
        //选择6：虚拟线程池
        ExecutorService executorService= Executors.newVirtualThreadPerTaskExecutor();
        //vm -option设置：-Djdk.virtualThreadScheduler.parallelism=1  -Djdk.virtualThreadScheduler.maxPoolSize=1 保证核心线程 最大线程都是1
        executorService.submit(new Tasks123());
        executorService.submit(new Tasks123());
        //虚拟线程中也是可以支持ThreadLocal的，即在虚拟线程中，也是可以使用ThreadLocal共享数据的
        //防止主控程序的控制台退出
        System.in.read();
    }


    static class Tasks123 implements Runnable{

        @Override
        public void run() {
            System.out.println("你好");
            System.out.println("666777888"+Thread.currentThread());
        }
    }

    @Test
    public void testSwitchOnJDK17(){
        var name="xjh";
        int age=switch (name){
            case "123","234"->23;
            case "xjh"->{
                System.out.println("xixi");
                yield 29;
            }
            default -> throw new IllegalStateException("Unexpected value: " + name);
        };
        System.out.println(age);
        Object o="XJH";
        String message=switch (o){
            case null->"o is null";
            case Integer i->String.format("integer i is %s",i);
            case Long l->String.format("Long l is %s",l);
            case Double d->String.format("Double d is %s",d);
            case String s->String.format("String s is %s",s);
            default -> o.toString();
        };
        System.out.println(message);
    }


}
/**
 * jdk16新特性类似jdk15
 *
 */

/**
 * jdk17特性
 *      switch表达式增强，支持类型匹配
 *
 */

/**
 * jdk18新特性
 *      简单的web服务器
 *          其目的是提供命令行工具来启动仅提供静态文件的最小web服务器，没有可用的CGI或类似Servlet的功能，该工具可以用于原型设计，临时编码和测试目的
 *          目标：
 *              提供开箱即用的静态http文件服务器，设置简单，功能最少
 *              降低开发人员使用成本，使jdk更易于使用
 *              通过命令行提供默认实现以及用于编程创建和自定义的小型api
 *                  通过指定自己的文件夹地址，暴露到web服务器上，相当于在局域网创建了一个文件服务器，供别的同事访问
 */


/**
 * jdk19新特性
 *      虚拟线程
 *          jdk19中的虚拟线程就是业界的协程，应为协程是用户态的，线程是操作系统内核态的，所以携程仍然基于的是线程，一个线程可以承载多个协程，但是如果
 *          所有协程都只基于一个线程，那样效率肯定不会高，所以jdk19中协程会基于ForkJoinPool线程池，利用多个线程来支持协程的运行，并且利用ForkJoinPool，而不是
 *          普通的ThreadPoolExecutor,可以支持大任务的拆分
 *
 *          我们在利用协程执行Runnable时候，底层会把Runnable提交到一个ForkJoinPool中去执行，我们可以通过：
 *              -Djdk.virtualThreadScheduler.parallelism=1;  默认cup的核心数
 *              -Djdk.virtualThreadScheduler.maxPoolSize=1;  最大256
 *          这两个参数来设置ForkJoinPool的核心线程数据和最大线程数据
 *
 *          ForkJoinPool中的线程在执行任务过程中，一旦线程阻塞了，比如sleep,lock,io操作时，那么这个线程就回去执行ForkJoinPool中的其他任务，
 *          从而可以做到一个线程在执行过程中，也能并发的执行多个任务，达到协程并发执行任务的效果
 *
 */

/**
 * jdk20新特性
 *  Scoped Value
 *  虚拟线程
 */

/**
 * jdk21新特性
 *      Scoped Value
 *          核心： 一个链表 在初始化执行的时候 pre=null 当前对象为Snapshot1，设置k/v  再次创建新的值的时候，重新创建一个Snapshot2,将链表的pre设置为Snapshot1,当前对象
 *          Snapshot2,设置k/v 当方法结束的时候，就将其pre指针的对象Snapshot1赋值为当前对象，在退出多次ScopedValue.runWhere方法的时候就依次这样执行，就会一次将pre的指针恢复会当前的对象
 *          知道最后一次，恢复为第一个对象，这样就得到最初的k/v,这个模式类似于方法栈的调用与退出，其中的局部变量的设置于获取
 *      虚拟线程
 *      结构化并发
 *
 *      Vector矢量API
 */