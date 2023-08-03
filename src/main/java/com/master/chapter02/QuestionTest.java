package com.master.chapter02;

import java.io.PrintStream;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2023-08-03 14:07
 */
public class QuestionTest {
    public static void main(String[] args) {
        int a=10;
        int b=10;
        //在该方法执行后，仅仅打印a=100,b=200
        //method1(a,b);
        method2(a,b);
        System.out.println("a="+a);
        System.out.println("b="+b);
    }
    public static void method1(int a,int b){
        System.out.println("a="+a*10);
        System.out.println("b="+b*20);
        System.exit(0);
    }
    public static void method2(int a,int b){
        PrintStream ps=new PrintStream(System.out){
            @Override
            public void println(String str) {
                if("a=10".equals(str)){
                    str="a=100";
                }else if("b=10".equals(str)){
                    str="b=200";
                }
                super.println(str);
            }
        };
        System.setOut(ps);

    }
}
