package com.master.chapter15;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: java8引入的stream api
 * @date 2023-10-17 9:17
 */
public class StreamApiTest {
    @Test
    public void streamTest(){
        //1：通过集合方式创建stream
        //java8中的集合接口被扩展了，提供了两个获取流的方法
            //default Stream<E> stream();//返回一个顺序流
            //default Stream<E> parallelStream();//返回一个并行流
        List<Employee> employees = MethodReferenceTest.getEmployees();
        //顺序流和并行流
        Stream<Employee> stream = employees.stream();
        Stream<Employee> employeeStream = employees.parallelStream();

        //2：通过数组方式创建stream
        //java8中的Arrays的静态方法stream()可以获取数组流
            //static <T>Stream <T> stream(T[] array);//返回一个流
            //重载形式，能够处理对应基本类型的数组
                //public static IntStream stream(int[] array)
                //public static LongStream stream(long[] array)
                //public static DoubleStream stream(double[] array)
        int[] array=new int[]{1,3,6,7,99,21};
        IntStream stream1 = Arrays.stream(array);
        Employee e1=new Employee(28,"COLORXJH",23.00,1001);
        Employee e2=new Employee(23,"COLOR",26.00,1002);
        Employee[] earray=new Employee[]{e1,e2};
        Stream<Employee> stream2 = Arrays.stream(earray);

        //3：通过Stream的of方法创建stream
        Stream<Integer> integerStream = Stream.of(12, 33, 45, 67);

        //4：创建无限流
            //可以使用静态方法Stream.iterate()和Stream.generate()创建无限流
                //迭代
                //public static<T> Stream<T>iterate(final T seed,final UnaryOperator<T>f)
                //生成
                //public static<T> Stream<T>generate(Supplier<T> s);
        //遍历前10个偶数                 中间操作
        Stream.iterate(0,t->t+2).limit(10).forEach(System.out::println);
        //生成10个随机数
        Stream.generate(Math::random).limit(10).forEach(System.out::println);

    }

    @Test
    public void testStreamMiddleOperation(){
        List<Employee> employees = MethodReferenceTest.getEmployees();
        System.out.println("--过滤--");
        employees.stream().filter(d->d.getAge()>22).forEach(System.out::println);
        employees.stream().limit(3).forEach(System.out::println);
        employees.stream().skip(3).forEach(System.out::println);
        System.out.println("----------");
        employees.stream().distinct().forEach(System.out::println);
        System.out.println("-- 映射---");
        employees.stream().map(d->d.getAge()).forEach(System.out::println);
        employees.stream().filter(d->d.getName().length()>5).map(Employee::getName).forEach(System.out::println);
        //flatMap与map区别
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> integers1 = Arrays.asList(33, 44, 55, 66);
        List<List<Integer>> lists = Arrays.asList(integers, integers1);
        System.out.println("----map与flatMap---");
        lists.stream().map(d->d.toString()).forEach(System.out::println);
        lists.stream().forEach(t->Arrays.toString(t.toArray()));
        lists.stream().flatMap(d->d.stream()).forEach(System.out::print);

        //排序
        System.out.println("---排序--");
        List<Integer> integers2 = Arrays.asList(2, 66, 5, 99, 122, 7, 9);
        integers2.stream().sorted().forEach(System.out::println);
        integers2.stream().sorted((o1,o2)->o2-o1).forEach(System.out::println);
        //实现排序接口，或者指定比较器
        employees.stream().sorted((o1,o2)-> o2.getAge()- o1.getAge()).forEach(System.out::println);
    }
    @Test
    public void testStreamEnd(){
        List<Employee> employees = MethodReferenceTest.getEmployees();
        System.out.println(employees.stream().allMatch(d->d.getAge()>21));
        System.out.println(employees.stream().anyMatch(d->d.getAge()>21));
        System.out.println(employees.stream().noneMatch(d->d.getAge()>25));
        System.out.println(employees.stream().findFirst().get());
        System.out.println(employees.stream().findAny().get());
        System.out.println(employees.stream().count());
        System.out.println(employees.stream().max((o1,o2)-> o1.getAge()- o2.getAge()));
        //注意这个是集合的遍历操作，不是流的遍历操作 他称为外部迭代
        employees.forEach(System.out::println);
        //内部迭代
        employees.stream().forEach(System.out::println);
        System.out.println("=-----------规约操作---------------");
        //规约操作
            //1：计算1-10自然数的和
        Stream.iterate(1,d->d+1).limit(10).forEach(System.out::println);
        Integer reduce = Stream.iterate(1, d -> d + 1).limit(10).reduce(0, (s, d) -> s = s + d);
        System.out.println(reduce);
        Stream.iterate(1,d->d+1).limit(10).reduce(0,Integer::sum);
            //2：计算所有员工的工资  初始值也可以不要
        Double reduce1 = employees.stream().map(d -> d.getPrice()).reduce(0.0, Double::sum);
        Optional<Double> reduce2 = employees.stream().map(d -> d.getPrice()).reduce(Double::sum);
        System.out.println(reduce1);
        System.out.println(reduce2.get());
    }
}

/**
 * stream api说明
 *      java8有两个极为重要的改变，一个是lambda另一个就是stream api
 *      stream api把真正的函数式编程风格引入了java中，这是目前为止对java类库最好的补充
 *      因为stream api可以极大的提升开发人员的生产力，让程序员写出高效干净整洁的代码
 *      Stream是java8中处理集合的关键抽象概念，它可以指定你希望对集合进行的操作，可以执行
 *      非常复杂的查找，过滤和映射数据等操作，使用stream api对集合数据进行操作，就类似于使用sql
 *      执行的数据库查询一样，也可以使用stream来执行并行操作，提高处理效率
 *      stream和Collection集合的区别：
 *          collection是一种静态的内存数据结构，而stream是有关计算的，前者是主要面向内存，存储在内存中，
 *          后者主要是面向cpu，通过cpu实现计算
 * 什么是stream?
 *      是数据渠道，用于操作数据源（集合、数组）所生成的元素序列，集合讲的是数据，stream讲的是计算
 *          stream自己不会存储元素
 *          stream不会改变源对象，相反，他们会返回一个持有结果的新的stream
 *          stream操作是延迟执行的，这意味着他们会等到需要结果的时候才会去执行
 *
 */

/**
 * stream 操作的三个步骤
 *      1：创建stream：一个数据源（集合数组），获取一个流
 *      2：中间操作：一个中间操作链，对数据源的数据进行处理
 *      3：终止操作（终端操作）：一旦执行终止操作，就执行中间操作链，并产生结果，之后，不会再被使用
 *          数据源=》filter=>map=>...=>终止操作
 *                  一系列中间操作
 */

/**
 * stream的中间操作
 *      多个中间操作可以连接起来形成一个流水线，除非流水线上触发终止操作，否则中间操作不会执行任何处理
 *      而在终止操作时一次性全部处理，称为惰性求值
 *      1：筛选与切片
 *          filter(Predicate p):接受lambda,从流中排除某些元素
 *          distinct():筛选，通过流所生成元素的hashcode()和equals()去除重复元素
 *          limit(long max):截断流，使其元素不超过给定数量
 *          skip(long n):跳过元素，返回一个扔掉了前n个元素的流，若流中元素不足n个，则返回一个空流，与limit(n)互补
 *      2：映射
 *          map(Function f):接受一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
 *          mapToDouble(ToDoubleFunction f):接受一个函数作为参数，该函数会被应用到每个元素上，产生一个新的Doubletream
 *          mapToInt(ToIntFunction f):接受一个函数作为参数，该函数会被应用到每一个元素上，产生一个IntStream
 *          mapToLong(ToLongFunction f):接受一个函数作为参数，该函数会被应用到每一个元素上，产生一个LongStream
 *          flatMap(Function f):接受一个函数作为参数，讲流中的每个值都换成另一个流，然后把所哟的流连接成一个流
 *      3：排序
 *          sorted():产生一个新的流，其中按自然顺序排列
 *          sorted(Comparator com):产生一个新流，其中按照比较器顺序排序
 */

/**
 * Stream的终止操作
 *      终端操作会从流的流水线产生结果，其结果可以是任何不是流的值，例如List,Integer,甚至是void
 *      流进行终止操作后，不能再次使用
 *          1：匹配与查找
 *              allMatch(Predicate p):检查是否匹配所有元素
 *              anyMatch(Predicate p):检查是否至少有一个元素匹配
 *              noneMatch(Predicate p):检查是否没有匹配所有元素
 *              findFirst():返回第一个元素
 *              findAny():返回当前流中的任何元素
 *          2：归约
 *              reduce(T t,BinaryOperator b):可以讲流中元素反复结合起来，得到一个值，返回T
 *              reduce(BinaryOperator b):可以将流中元素反复结合起来，得到一个值，返回Optional<T>
 *                  map和reduce的连接通常被称为map-reduce 模式，因谷歌用它来进行网络搜索而出名
 *
 */