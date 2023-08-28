package com.master.chapter06;

import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 字符串相关操作
 * @date 2023-08-25 10:17
 */
public class StringTest {
    public static void main(String[] args) {
        /**
         * 字面量形式的类型定义在方法区的字符串常量池中
         */
        String s1="abc";
        String s2="abc";
        /**
         * 堆空间
         */
        String s3=new String("def");
        System.out.println(s1==s2);
        //与c不同不需要结束符\0
        char[] array={'h','e','l','l','o'};
        char[] array2={'h','e','l','l','o','\0'};
        System.out.println(array);
        System.out.println(array2);
        System.out.println("-----------------------------");
        String s4="Color";
        String s5="XJH";
        String s6="ColorXJH";
        String s7=s4+"XJH";
        String s8="Color"+s5;
        String s9=s4+s5;
        String s10="Color"+"XJH";
        System.out.println(s6+"--"+s7+"--"+s8+"--"+s9);
        //只要有变量参与的链接，都是在堆中新建变量，返回地址值
        //s7对于s4而言是新创建的一个堆空间的变量 s6是元来有的变量
        System.out.println(s6==s7);
        //同上
        System.out.println(s6==s9);
        //s7 s8 对比s4 s5 都是堆空间中新建对象
        System.out.println(s7==s8);
        //s10编译时期已经确定无需重造，直接复用s6(字面量连接的行为)
        //这种优化只适用于编译时可以确定的字符串字面量。如果字符串是在运行时动态生成的，例如通过变量拼接或方法调用返回的字符串，优化就不会发生，每次都会创建新的字符串对象。
        System.out.println(s10==s6);
        //方法的返回值是常量池已经存在的字符串的地址
        //String 类的 intern() 方法是一个用于字符串常量池操作的方法。
        //它的作用是返回字符串对象在常量池中的引用，如果常量池中已经存在该字符串，则返回常量池中的引用；
        //如果常量池中不存在该字符串，则将该字符串添加到常量池中，并返回常量池中的引用。
        String s11=s9.intern();
        System.out.println(s11==s6);
        byte[] bytes = "中国".getBytes(Charset.forName("GBK"));
        System.out.println(">>>>>>>------");
        System.out.println(Arrays.toString(bytes));
        String sss=new String(bytes);
        System.out.println(sss);
    }
}
/**
 * String s=new String("abc");
 * 在内存中创建了两个对象：一个是堆空间中的new结构 另一个是char[]对应的常量池中的数据“abc”
 */


/**
 * String类的各种方法
 * String类与其他类型的转换方法
 * String类与char[]数组之间的转换
 * String类与byte[]数组之间的转换 编码与解码 字符集
 */