package com.master.chapter16;

import org.junit.Test;

import java.util.Optional;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: jdk12 13的新特性
 * @date 2023-10-23 16:28
 */
public class JDK12_13_Test {
    @Test
    public void testSwitchBeforeJava12(){
        int age=12;
        switch (age){
            case 10:
                System.out.println("name");
                break;
            case 11:
                System.out.println("time");
                break;
            case 12:
                System.out.println("age");
            case 13:
                System.out.println("dada");
            case 15:
                System.out.println("now");
                break;
            default:throw new RuntimeException("no such number");
        }
    }

    @Test
    public void testSwitchOnJava13() throws IllegalAccessException {
        String name = "Color";
        switch (name) {
            case "xjh" -> System.out.println("this is xjh");
            case "color" -> System.out.println("this is color");
            case "Color" -> System.out.println("this is Color");
            case "1", "2", "3" -> System.out.println("bing");
            default -> new IllegalAccessException("no such string");
        }
        String name2 = "Color";
        int age = switch (name2) {
            case "xjh" -> 2;
            case "color" -> 3;
            case "Color" -> 4;
            case "1", "2", "3" -> 5;
            default -> throw new IllegalAccessException("no such string");
        };
        System.out.println(age);
    }

    @Test
    public void testStringConstant(){
        System.out.println("---------test-----------");
        String name="ColorXJH";
        Optional<String>optional=name.describeConstable();
        System.out.println(optional.get());
    }
}


/**
 * jdk12的主要8个新特性
 *      1：低暂停时间的GC
 *      2：微基准测试套件
 *      3：switch表达式
 *      4：JVM常量API
 *      5：只保留一个AArch64实现
 *      6：默认类数据共享归档文件（cds:类数据共享 class data sharing）
 *      7：可终止的G1 Mixed GC
 *      8：G1及时返回未使用的已分配内存
 *
 *          switch表达式
 *              传统的switch的弊端：
 *                  匹配是自上而下的：如果忘记写break,后面的case语句无论匹配与否都会执行
 *                  所有的case语句公用一个块范围，在不同的case语句定义的变量名不能重复
 *                  不能在一个case里面写多个执行结果一致的条件
 *                  整个switch不能作为表达式返回值
 *              语法详解：
 *                  扩展的switch语句，不仅可以作为语句，还可以作为表达式，并且2中写法都可以使用传统的switch语法
 *          微基准测试套件:
 *              JMH:java microbenchmark harness java微基准测试套件，基于方法层面的基准测试，精度可达微妙级，当你定位到热点方法时，希望进一步优化性能
 *                  可以使用JMH对优化的结果进行量化分析
 *              典型应用场景：
 *                  想准确的知道某个方法需要执行多少时间，以及执行时间和输入之间的相关性
 *                  对比接口不同实现在给定条件下的吞吐量
 *                  查看多少百分比的请求在多长时间内完成
 *              使用：
 *                  要使用JMH,必须准备好maven环境
 *          CDS:类数据共享机制：
 *              同一个物理机/虚拟机上启动多个jvm时，如果每一个jvm都单独装载所有的类，启动成本和占用内存将比较高，通过把一些核心类在每个jvm间共享
 *              每个jvm只需要装载自己的应用类即可，好处是：启动时间减少了，另外核心类是共享的，所以jvm的内存占用也减少了。
 *
 */