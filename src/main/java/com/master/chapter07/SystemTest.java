package com.master.chapter07;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: System代表系统
 * @date 2023-08-31 11:13
 */
public class SystemTest {
    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        System.out.println(System.getProperty("java.home"));
        System.gc();
        System.exit(0);
        System.out.println("执行不到----");
    }
}


/**
 * Math:提供一系列的方法用于数学计算
 * BigInteger:可以表示不可变的任意精度的整数
 * BigDecimal:支持不可变的，任意精度的有符号十进制定点数 构造器，加减乘除 舍入规则 精度位数
 */