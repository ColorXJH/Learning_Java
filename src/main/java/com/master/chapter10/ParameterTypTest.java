package com.master.chapter10;

import java.util.ArrayList;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: java的范型
 * @date 2023-09-08 15:14
 */
public class ParameterTypTest {
    public static void main(String[] args) {
        ParameterTypTest test=new ParameterTypTest();
        test.test1();
    }
    public void test1(){
        ArrayList list=new ArrayList();
        list.add(123);
        list.add(234);
        list.add(345);
        list.add("ggg");
        for (Object obj: list) {
            //类型转换异常
            System.out.println((int)obj);
        }
    }
}

/**
 * 为什么需要范型
 * 集合中限制类型存入 防止不同类型的强制类型转换
 * 范型在底层使用的就是Object[]
 */