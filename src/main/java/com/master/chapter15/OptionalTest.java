package com.master.chapter15;

import org.junit.Test;

import java.util.Date;
import java.util.Optional;

/**
 * @ClassName: OptionalTest
 * @Package: com.master.chapter15
 * @Description: java8的Optional类
 * @Datetime: 2023/10/17 22:14
 * @author: ColorXJH
 */
public class OptionalTest {
    @Test
    public void testOptional() throws Exception {
        Employee employee=new Employee();
        Optional<Employee> employee1 = Optional.of(employee);
        Optional<Object> ops = Optional.ofNullable(null);
        Optional<Object> empty = Optional.empty();
        System.out.println(employee1.isPresent());
        System.out.println(employee1.get());
        System.out.println(ops.orElse(new Date()));
        System.out.println(ops.orElseGet(Date::new));
        System.out.println(ops.orElseThrow(Exception::new));

    }
}

/**
 * 到目前为止，臭名昭著的空指针异常是导致java引用程序失败的最常见原因，以前，为了结局空指针异常，google公司著名的Guava项目映入了Optional类，
 * Guava通过使用检查空值的方式来防止代码污染，他鼓励程序员写出更干净的代码，收到Google Guava的启发，Optional类已成为java8类库的一部分
 *  Optional<T>类是一个容器类，它可以保存类型T的值，代表这个值存在，或者仅仅保存null,表示这个值不存在，原来用null表示一个值不存在，现在Optional可以更好的
 *  表达这个概念，并且可以避免空指针异常
 *      Optional类的java doc描述如下：这是一个可以为null的容器对象，如果值存在则isPresent()方法会返回true,调用get()方法会返回该对象
 *  Optional提供很多有用的方法，这样我们就不用显示的进行空值检查
 *      1：创建Optional类对象的方法
 *          Optional.of(T t):创建一个Optional实例，t必须非空
 *          Optional.empty():创建一个空的Optional实例
 *          Optional.ofNullable(T t):t可以为null
 *      2：判断Optional容器是否包含对象
 *          boolean isPresent():判断是否包含对象
 *          void ifPresent(Consumer consumer):如果有值，就执行Consumer接口的代码，并且该值会作为参数传递给这个方法
 *      3：获取Optional容器的对象
 *          T get():如果调用对象包含值，则返回该值，否则抛出异常
 *          T orElse(T other):如果有值则将其返回，否则返回指定对象other
 *          T orElseGet(Supplier other):如果有值则将其返回，如果没有值则返回Supplier接口实现提供的对象
 *          T orElseThrow(Supplier exceptionSupplier):如果有值将其返回，否则抛出由Supplier接口实现提供的异常
 *
 */