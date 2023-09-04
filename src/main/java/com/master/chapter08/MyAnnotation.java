package com.master.chapter08;

import java.lang.annotation.*;

/**
 * @ClassName: MyAnnotation
 * @Package: com.master.chapter08
 * @Description:自定义注解
 * @Datetime: 2023/9/4 19:51
 * @author: ColorXJH
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER,ElementType.FIELD,ElementType.METHOD,ElementType.TYPE})
public @interface MyAnnotation {
    String value();
    String desc();
    String name() default "yun";
}
