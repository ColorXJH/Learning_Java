package com.master.chapter06;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2023-08-29 10:08
 */
public class StringBufferBuilderTest {
    public static void main(String[] args) {
        String str1="Color";
        StringBuffer sb1=new StringBuffer("abc");
        StringBuilder sb2=new StringBuilder("def");
    }
}
/**
 * String  StringBuffer  StringBuilder的异同
 * String:不可变的而字符序列
 * StringBuffer:线程安全的可变的字符序列
 * StringBuilder:jdk5.0新增 线程不安全的可变的字符序列
 * 只要不是多线程操作共享数据 就推荐StringBuilder
 * 三者底层都是char[]数组
 * 扩容机制
 * 常用方法
 */