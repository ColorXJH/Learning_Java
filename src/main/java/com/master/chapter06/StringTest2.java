package com.master.chapter06;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2023-08-30 11:26
 */
public class StringTest2 {
    public static void main(String[] args) {
        String str=null;
        StringBuffer sb=new StringBuffer();
        sb.append(str);
        System.out.println(sb.length());//4
        System.out.println(sb);//"null"
        StringBuffer sb1=new StringBuffer(str);//报错 调用了str.length()
        System.out.println(sb1);
    }
}
