package com.master.chapter15;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

/**
 * @ClassName: MethodReferenceTest
 * @Package: com.master.chapter15
 * @Description: java8的方法引用
 * @Datetime: 2023/10/16 20:33
 * @author: ColorXJH
 */
public class MethodReferenceTest {
    public static List<Employee> getEmployees(){
        List<Employee>list=new ArrayList<>();
        list.add(new Employee(20,"color",200.00,1001));
        list.add(new Employee(21,"color2",300.00,1002));
        list.add(new Employee(22,"color3",400.00,1003));
        list.add(new Employee(23,"color4",500.00,1004));
        list.add(new Employee(24,"color5",600.00,1005));
        return list;
    }
    //对象::实例方法
    @Test
    public void testConsumer(){
        //对比void accept(T t)与void println(T t)
        //lambda表达式
        Consumer<String>consumer=s -> System.out.println(s);
        consumer.accept("北京");
        //方法引用 本质上就是lambda表达式，也是函数式接口的一个实例
        Consumer<String>consumer1=System.out::println;
        consumer1.accept("方法引用");
        //体会Supplier的get()与Employee的getName()
        //要求接口中的抽象方法的参数列表与返回值类型与方法引用的参数列表和返回值类型一致(对象::实例方法  类::静态方法,类::实例方法 不适用！！)
        Employee employee=new Employee(20,"color",200.00,1001);
        Supplier<String> supplier=employee::getName;
        System.out.println(supplier.get());

    }

    //类::静态方法
    @Test
    public void test2(){
        //体会Comparator的comparator=(t1,t2)与Integer.compare(t1,t2)
        Comparator<Integer>comparator=(t1,t2)->Integer.compare(t1,t2);
        System.out.println(comparator.compare(12,21));
        Comparator<Integer>comparator1=Integer::compare;
        System.out.println(comparator1.compare(22,33));
        //体会Function中的 R apply(T t)  与Math中的Long round(Double d)
        Function<Double,Long> func=d->Math.round(d);
        System.out.println(func.apply(12.3));
        Function<Double,Long> func2=Math::round;
        System.out.println(func2.apply(122.33));
    }
    //类::实例方法
    @Test
    public void test3(){
        //参考Comparator中的int compare(T t1,T t2)与String 中的int t1.compare(t2)
        Comparator<String>con=(s1,s2)->s1.compareTo(s2);
        System.out.println(con.compare("abc","abd"));
        Comparator<String>comparator=String::compareTo;//s1.compare(s2)
        //第一个参数作为方法的调用者出现 这样也能作为方法引用调用
        //BiPredicate中的boolean test(T t1,T t2)与String中的boolean t1.equals(t2)
        BiPredicate<String,String>pre1=(s1,s2)->s1.equals(s2);
        System.out.println(pre1.test("abc","abd"));
        BiPredicate<String,String>pre=String::equals;
        System.out.println(pre.test("abc","abd1"));
        //Function中的R apply(T t)与Employee中的 getName();  第一个参数 t就是employee对象 由它去调用方法，然后返回的是一个参数
        Employee employee=new Employee(20,"color",200.00,1001);
        Function<Employee,String>function=e-> e.getName();
        System.out.println(function.apply(employee));
        Function<Employee,String>function1=Employee::getName;
        System.out.println(function1.apply(employee));

    }

    //构造器引用与数组引用
    @Test
    public void test4(){
        //构造器引用
        //Supplier中的T get()
            //lambda
        Supplier<Employee>supplier=()->new Employee();
        supplier.get();
            //构造器引用
        Supplier<Employee>supplier1=Employee::new;
        supplier1.get();
        //Function 中的R apply(T t)
        Function<Integer,Employee>function=id->new Employee(id);
        Employee apply = function.apply(1001);
        System.out.println(apply);
        Function<Integer,Employee>function1=Employee::new;
        System.out.println(function1.apply(1223));
        //BiFunction中的R apply(T t,U u)
        BiFunction<Integer,String,Employee>function2=(id,name)->new Employee(id,name);
        BiFunction<Integer,String,Employee>function3=Employee::new;
        System.out.println(function3.apply(12,"ColorXJH"));

        //数组引用
        //Function R apply(T t)
        Function<Integer,String[]>function4=length->new String[length];
        Function<Integer,String[]>function5=String[]::new;//并不用写参数，和构造器引用一样，不需要写参数
        System.out.println(Arrays.toString(function4.apply(5)));


    }
}

/**
 * 方法引用
 *      当要传递给lambda体的操作已经有实现的方法了，可以使用方法引用
 *      方法引用可以看作是lambda表达式的深层次表达，换句话说，方法引用就是lambda表达式，也就是函数式接口的一个实例
 *          通过方法的名字来指向一个方法，可以认为是lambda表达式的一个语法糖
 *      要求：实现接口的抽象方法的参数列表和返回值类型，必须与方法引用的方法的参数列表和返回值类型保持一致
 *      格式：使用操作符“::”将类或对象与方法名分割开来
 *      如下三种主要使用方式：
 *          对象::实例方法名
 *          类::静态方法名
 *          类::实例方法名
 *
 */

/**
 * 构造器引用与数组引用
 *      构造器引用与方法引用类似，函数式接口的抽象方法的形参列表和构造器函数的形参列表一致，抽象方法返回值类型即为构造器所属的类的类型
 */