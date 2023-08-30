package com.master.chapter06;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 格式化和解析日期的类 JDK8之前
 * @date 2023-08-30 11:38
 */
public class SimpleDateFormatTest {
    public static void main(String[] args) {

    }

    /**
     * SimpleDateFormat对Date的格式化和解析
     * 1:格式化 日期-》字符串 2:解析 字符串-》日期
     */
    @Test
    public void test1() throws ParseException {
        //默认构造器
        SimpleDateFormat sdf=new SimpleDateFormat();
        //带参数构造器
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date=new Date();
        System.out.println(date);
        //格式化
        String format = sdf.format(date);
        String format1 = sdf1.format(date);
        System.out.println(format);
        System.out.println(format1);
        //解析
        String str="23-8-31 下午12:17";
        String str1="2023-11-23 23:34:33";
        Date parse = sdf.parse(str);
        Date parse1 = sdf1.parse(str1);
        System.out.println(parse);
        System.out.println(parse1);
        //util-》sql
        java.sql.Date dates1=new java.sql.Date(parse.getTime());

    }
}
