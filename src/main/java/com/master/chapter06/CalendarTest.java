package com.master.chapter06;

import java.util.Calendar;
import java.util.Date;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: Calendar日历类的使用
 * @date 2023-08-30 12:40
 */
public class CalendarTest {
    public static void main(String[] args) {
        CalendarTest test=new CalendarTest();
        test.testCalendar();

    }
    public void testCalendar(){
        //创建实例
        Calendar calendar=Calendar.getInstance();
        //get()
        int days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR));
        //set()
        calendar.set(Calendar.DAY_OF_MONTH,22);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        //add()
        calendar.add(Calendar.DAY_OF_MONTH,2);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        //getTime()  日历类-》Date
        Date time = calendar.getTime();
        System.out.println(time);
        //setTime() Date->日历类
        Date date2=new Date();
        calendar.setTime(date2);
        int days1=calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days1 );

    }
}
