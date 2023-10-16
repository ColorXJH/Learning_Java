package com.master.chapter15;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @ClassName: LambdaTest
 * @Package: com.master.chapter15
 * @Description: java8 新特新
 * @Datetime: 2023/10/14 19:37
 * @author: ColorXJH
 */
public class LambdaTest {

    @Test
    public void testLambda(){
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                System.out.println("你好-世界");
            }
        };
        //不是多线程，只是对象调用方法
        runnable.run();
        System.out.println("------lambda表达式方式进行重写-------");
        Runnable runnable1=()-> System.out.println("lambda--你好世界");
        runnable1.run();

        Comparator<Integer> comparator=new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };
        int compare=comparator.compare(12,23);
        System.out.println(compare);
        System.out.println("---lambda-compare--");
        //lambda表达式，作为接口的实现
        Comparator<Integer>comparator1=(o1,o2)->{
            return o1-o2;
        };
        //方法引用的写法
        Comparator<Integer>comparator2=Integer::compare;
        System.out.println(comparator1.compare(12,15));
    }
    @Test
    public void testUseLambda(){
        //形式1：无参无返回值
        Runnable r1=()-> System.out.println("你好世界");
        r1.run();
        //形式2：有参数无返回值 参数String o 参数类型可写可不写，可以通过范型类型推断
        Consumer<String> consumer=(o)->{
            System.out.println(o);
        };
        consumer.accept("hello");
        //形式3：参数只有一个时，参数小括号可以省略
        Consumer<String>consumer1=o-> System.out.println("--参数只有一个，小括号可以省略--");
        //形式4： 两个及其以上参数，有返回值
        Comparator<Integer>c1=(o1,o2)->{
            return o1-o2;
        };
        //形式5：lambda只有一条语句时，return与大括号若有，则都可以省略
        Comparator<Integer>c2=(o1,o2)->o1-o2;
        //形式6: 方法引用形式
        Comparator<Integer>c3=Integer::compare;
    }
    @Test
    public void testFourFunctionalInterface(){
        //Consumer消费者接口
        happyTime(500,money-> System.out.println("今天出门消费了多少钱："+money));
        //Supplier生产者
        String s1 = testSupplier(() -> "xjh");
        System.out.println(s1);
        //判断者
        List<String>list= Arrays.asList("北京","天津","上海","广州");
        List<String> strings = filterString(list, s -> s.contains("海"));
        System.out.println(strings);
        //函数型接口
        List<Integer> list1 = testFunction(list, (String o1) -> {
            if (o1.contains("州")) {
                return 1;
            } else {
                return 0;
            }
        });
        System.out.println(list1);
    }
    public void happyTime(double money,Consumer<Double> consumer){
        consumer.accept(money);
    }
    public List<String> filterString(List<String>list, Predicate<String>pre){
        ArrayList<String> filterList=new ArrayList<>();
        for(String s:list){
            if(pre.test(s)){
                filterList.add(s);
            }
        }
        return filterList;
    }
    public String testSupplier(Supplier<String>supplier){
        return supplier.get();
    }
    public List<Integer>testFunction(List<String>list, Function<String,Integer>function){
        ArrayList<Integer>listss=new ArrayList<>();
        for(String str:list){
            listss.add(function.apply(str));
        }
        return listss;
    }
}

/**
 * java8新特性简洁：
 *      速度更快
 *      代码更少，增加了新的语法lambda表达式
 *      强大的Stream API
 *      便于并行
 *      最大化减少空指针异常：Optional
 *      Nashorn引擎，允许在jvm上运行js应用：jjs func.js  jjs.exe命令执行js程序
 */

/**
 * lambda表达式
 * 为什么要使用lambda表达式？
 *      lambda是一个匿名函数，我们可以把lambda表达式理解为是一段可以传递的代码（将代码像数据一样进行传递）
 *      使用它可以写出更加简洁，更加灵活的代码，作为一种更加紧凑的代码风格，是java的语言表达能力得到了提升
 *      lambda在其他语言中是作为函数传递，而在java中，lambda的本质是作为接口的一个实例传递
 */

/**
 * lambda表达式的使用
 *      (o1,o2)->Integer.compare(o1,o2);
 *      格式：
 *          ->:lambda操作符，箭头操作符，
 *              左边：形参列表，原来接口中抽象方法的形参
 *              右边：lambda体，重写的抽象方法的方法体
 *      如果一个接口中只声明了一个抽象方法，则此接口就称为函数时接口，使用@FunctionalInterface标注
 */

/**
 * 什么是函数式Functional接口
 *      只包含一个抽象方法的接口
 *      你可以通过lambda表达式来创建该接口的对象，若lambda表达式抛出一个受检异常，那么该异常需要在目标接口的抽象方法上声明
 *      我们可以在一个接口上使用@FunctionalInterface注解，这样可以检查是否是一个函数式接口，同时javadoc也会包含一条声明，说明这个接口是函数式接口
 *      在java.util.function包下定义了java8的丰富的函数式接口
 *
 *      即：函数式接口的实例就是lambda表达式
 */


/**
 * 如何理解函数式接口？
 *      java从诞生起就一致倡导一切皆对象，java里面oop面向对象编程就是一切，但是随着新兴语言scala,python的兴起
 *      java面临挑战，java不得不做出调整和改变，即现在java不经式oop语言，还是oof(支持面向函数编程)
 *
 *      在函数式编程语言中，函数被当作一等公民，在函数式编程语言中，lambda表达式的类型是函数，但是在java中，lambda的
 *      是对象，而不是函数，他必须依赖于一种特别的对象类型--》函数式接口
 *
 *      简单来说，在java8中，lambda表达式就是一个函数式接口的实例，这就是lambda表达式和函数式接口的关系，也就是说
 *      只要一个对象是函数式接口的实例，那么该对象就可以使用lambda表达式来表示
 *
 *      匿名类实现的表达式现在部分可以使用lambda来书写
 *
 */

/**
 * java内置四大核心函数式接口
 *      Consumer<T> 消费型接口:对类型为T的对象应用操作，包含方法void accept(T t)
 *      Supplier<T> 共给型接口:返回类型为T的对象，包含方法T get()
 *      Function<T,R> 函数型接口:对类型为T的对象进行操作，并返回结果，结果是R类型的对象，包含方法 R apply(T t)
 *      Predicate<T> 断定型接口:确定类型为T的对象是否满足某约束，并返回boolean值，包含方法 boolean test(T t)
 */