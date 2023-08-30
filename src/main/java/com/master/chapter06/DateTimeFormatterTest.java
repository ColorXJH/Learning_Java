package com.master.chapter06;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: jdk8 格式化解析日期和时间
 * @date 2023-08-30 16:03
 */
public class DateTimeFormatterTest {
    public static void main(String[] args) {
        //实例化方式1 默认格式
        DateTimeFormatter isoLocalDateTime = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime now=LocalDateTime.now();
        //格式化
        String format = isoLocalDateTime.format(now);
        System.out.println(now);
        System.out.println(format);
        //解析
        TemporalAccessor parse = isoLocalDateTime.parse("2023-08-30T16:17:09.721");
        System.out.println(parse);

        //本地方式
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        String format1 = dateTimeFormatter.format(now);
        System.out.println(format1);

        //自定义的格式化
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        String format2 = dateTimeFormatter1.format(now);
        System.out.println(format2);
    }

}
