package com.master.chapter08;

import java.lang.annotation.*;

/**
 * @ClassName: AfterJDK8Annotation
 * @Package: com.master.chapter08
 * @Description: 可重复的注解
 * @Datetime: 2023/9/4 21:29
 * @author: ColorXJH
 */

@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER,ElementType.FIELD,ElementType.METHOD,ElementType.TYPE})
public @interface AfterJDK8Annotation {
    MyAnnotation2[] value() ;
}
