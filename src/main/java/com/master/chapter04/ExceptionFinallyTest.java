package com.master.chapter04;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2023-08-17 14:53
 */
public class ExceptionFinallyTest {
    public static void main(String[] args) {
        ExceptionFinallyTest test=new ExceptionFinallyTest();
        try {
            test.test2();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    public int test(){
        int a=10;
        int b=0;
        int c=2;
        try {
            System.out.println(a/b);
            //抛出异常，到不了下方的额++a,以及return
            return ++a;
        } catch (ArithmeticException e) {
            e.printStackTrace();
            System.out.println("异常代码捕获");
            System.out.println("a="+a);
            //先执行b++,然后执行返回，会进入finally,被finally中的return覆盖掉，从finally中返回
            return b++;
            //如果这里改成throw,则还是会在finally中return,但是也会伴随着抛出一个异常，到test2方法，
            //如果test2方法有异常处理则处理，没有则接着向上抛到main，一直没处理就会报错
            //如果下方有finally,则这个异常就被吞了
            //throw new IllegalAccessException("test方法catch中抛出的异常");
        }
        finally {
            System.out.println("一定会被执行的代码");
            System.out.println("a="+a);
            System.out.println("b="+b);
            //这个异常可以被捕获,为了避免异常被吞掉，考虑在finally代码块中抛出异常，如果有需要的话。。
            //这样即使在catch中返回return,也会执行finally中的代码，这里的return/throw会覆盖掉catch中的return/throw
            throw new NullPointerException("test方法finally中抛出的异常,导致catch抛出的异常被吞掉了");
            //return ++c;

            //JVM对于其他的物理连接，比如数据库连接输入输出流，socket链接无能为力，一般在此关闭，即使代码出现了异常，在推出之前
            //也需要先关闭连接
        }
    }
    public void test2(){
        int x=test();
        System.out.println(x);
    }
}
//总结，运行时异常其实属于非受检异常，你不知道是否会报错，需要你检查程序约束条件，一般对于非受检异常不编写try-catch处理
//这种异常可以通过程序逻辑来分辨，编译时异常（受检异常）必须要异常处理，否则无法编译通过，error错误属于无法处理的错误。。。