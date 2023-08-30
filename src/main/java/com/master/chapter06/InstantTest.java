package com.master.chapter06;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 瞬时点
 * @date 2023-08-30 15:43
 */
public class InstantTest {
    public static void main(String[] args) {
        //格林威治本初子午线时间 0时区
        Instant instant=Instant.now();
        System.out.println(instant);
        //东八区 带偏移量的时间
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);
        //获取标准时间对应的毫秒数1970年1月1日0时0分0秒到现在的毫秒数
        //类似Date的getTime(), System.currentTimeMillis()
        long l = instant.toEpochMilli();
        System.out.println(l);
        System.out.println(System.currentTimeMillis());
        System.out.println(new Date().getTime());
        //创建时间实例(0时区) 通过给定毫秒数-》Instant实例 类似 new Date(xxxL)
        Instant instant1=Instant.ofEpochMilli(1693382232031L);
        System.out.println(instant1);


    }
}
