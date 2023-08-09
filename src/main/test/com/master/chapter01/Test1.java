package com.master.chapter01;

import org.junit.Test;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2023-08-08 17:02
 */
public class Test1 {
    @Test
    public void test1(){
        System.out.println("test");
    }
    @Test
    public void test2(){
        //三元运算符类型自动提升为double
        Object o1=true?new Integer(1):new Double(2.0);
        //1.0
        System.out.println(o1);
    }
    @Test
    public void test3(){
        Object o2;
        if(true){
            o2=new Integer(1);
        }else{
            o2=new Double(2.0);
        }
        System.out.println(o2);

    }
    @Test
    public void test4(){
        Integer i=new Integer(1);
        Integer j=new Integer(1);
        System.out.println(i==j);//false
        Integer m=1;
        Integer n=1;
        System.out.println(m==n);//true
        //Integer 中的缓存Integer cache[];
        // range [-128, 127] must be interned (JLS7 5.1.7)
        Integer g=128;
        Integer k=128;
        System.out.println(g==k);//false;
    }
}
