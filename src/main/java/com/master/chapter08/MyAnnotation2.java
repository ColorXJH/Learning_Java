package com.master.chapter08;

import java.lang.annotation.*;

/**
 * @ClassName: MyAnnotation2
 * @Package: com.master.chapter08
 * @Description:
 * @Datetime: 2023/9/4 21:39
 * @author: ColorXJH
 */
@Inherited
@Documented
@Repeatable(AfterJDK8Annotation.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER,ElementType.FIELD,ElementType.METHOD,ElementType.TYPE})
public @interface MyAnnotation2 {
    String value();
    String desc();
    String name() default "yun";
}
