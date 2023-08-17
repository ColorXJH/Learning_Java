package com.master.chapter04;

import org.junit.Test;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:异常处理方式 try-catch-finally
 * @date 2023-08-17 13:14
 */
public class ExceptionTestOne {
    @Test
    public void test1(){
        String name="abc";
        try {
            System.out.println("hello");
            Character c = name.charAt(3);
            System.out.println("after exception");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            System.out.println("-------");
            e.printStackTrace();
            System.out.println("出现异常了，不要紧张。。。");
        }
        System.out.println("after catch the exception");
    }
}
