package com.master.chapter08;

import java.lang.annotation.*;

/**
 * @ClassName: MultiAnnotation
 * @Package: com.master.chapter08
 * @Description:
 * @Datetime: 2023/9/4 21:17
 * @author: ColorXJH
 */
public class MultiAnnotation {

}
//jdk8之前的写法
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotations{
    MyAnnotation[] value();
}