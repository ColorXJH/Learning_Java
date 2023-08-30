package com.master.chapter06;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: jdk8之后的日期时间api
 * @date 2023-08-30 14:34
 */
public class JDK8DateAPITest {
    public static void main(String[] args) {
        JDK8DateAPITest test=new JDK8DateAPITest();
        test.test1();
        test.test2();
    }

    public void test1(){
        //传统日期API  1 可变性 2偏移量 3格式化只对Date有用 4线程不安全
        Date date=new Date(2023-1900,8-1,30);
        System.out.println(date);
        //新的日期时间API 本地日期 本地时间 本地日期时间 时区 持续时间
        //Date->新时间表示方法 Date.toInstant()
    }
    /**
     * LocalDate
     * LocalTime
     * LocalDateTime
     */
    public void test2(){
        //now()
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDate+"---"+localTime+"---"+localDateTime);
        //of()
        LocalDateTime localDateTime1 = LocalDateTime.of(2023, 8, 30, 15, 24, 32);
        System.out.println(localDateTime1);
        //getXxx() 获取相关属性
        System.out.println(localDateTime1.getDayOfMonth());
        System.out.println(localDateTime1.getDayOfYear());
        System.out.println(localDateTime1.getMonthValue());
        System.out.println(localDateTime1.getMonth());
        //withXXX() 设置相关的属性 不可变性
        LocalDateTime localDateTime2 = localDateTime1.withDayOfMonth(2);
        System.out.println(localDateTime2);
        //plus
        LocalDateTime localDateTime3 = localDateTime2.plusDays(3);
        System.out.println(localDateTime3);
        //minus
        LocalDateTime localDateTime4 = localDateTime3.minusHours(5);
        System.out.println(localDateTime4);
    }
}
