package com.master.chapter16;

import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.util.Locale;
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
    public void testSwitchOnJava12() throws IllegalAccessException {
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

    @Test
    public void testCompactNumberInstance(){
        var cnf= NumberFormat.getCompactNumberInstance(Locale.CHINA,NumberFormat.Style.SHORT);
        System.out.println(1_00000);
        System.out.println(1_9200);
        System.out.println(1_000_000);
        System.out.println(1L<<30);
        System.out.println(1L<<40);
        System.out.println(1L<<50);
        System.out.println("-----------------------");
        System.out.println(cnf.format(1_000));
        System.out.println(cnf.format(1_9200));
        System.out.println(cnf.format(1_000_000));
        System.out.println(cnf.format(1L<<30));
        System.out.println(cnf.format(1L<<40));
        System.out.println(cnf.format(1L<<50));

    }

    @Test
    public void testStringAdd(){
        var result="foo".transform(x->x+"bar");
        System.out.println(result);
        var test="bar".indent(2);
        var test1="a\n   b\nc ".indent(2);
        System.out.println(test);
        System.out.println(test1);
    }

    @Test
    public void testFilesMismatch() throws IOException {
        //文件可以不存在，但是目录需要存在
        FileWriter writer=new FileWriter("writer1.txt");
        writer.write("a");
        writer.write("b");
        writer.write("c");
        writer.write("d");
        writer.close();
        FileWriter writer2=new FileWriter("writer2.txt");
        writer2.write("a");
        writer2.write("u");
        writer2.write("c");
        writer2.write("d");
        writer2.close();
        //显示不匹配的数据的位置 从0开始，如果都匹配则返回-1
        System.out.println(Files.mismatch(Path.of("writer1.txt"),Path.of("writer2.txt")));
        /**
         * IO:File
         * NIO2:Files操作本地文件的工具类，Path:替换原有的File,Paths:实例化Path
         *
         */
    }

    @Test
    public void testSwitchOnJava13(){
        String x="color";
        //->自带一个break的效果 :后面的操作 使用了yield就不需要使用break了
        //:与->不可以混合使用
        int i=switch (x){
            case "color1"->5;
            case "xjh"->6;
            case "color"->{
               yield 99;
            }
            default -> throw new IllegalStateException("Unexpected value: " + x);
        };
        int y=switch (x){
            case "color":yield 33;
            case "xjh":yield 22;
            default:yield 35;
        };
    }

    @Test
    public void testTextBlock(){
        String x="<html>\n" +
                "\t<p> this is colorxjh </p>\n" +
                "</html>";
        System.out.println(x);
        System.out.println("------on java 13------");
        //"""    """;之间的内容作为文本块
        String textBlock= """
                <html>
                	<p> this is colorxjh </p>
                </html>
                """;
        System.out.println(textBlock);
        String names= """ 
                abc\n
                asdasdas\n
                sdadasdsa\n""";
        System.out.println(names);
        String t1= """
                abc""";
        String t2= """
                abc
                """;
        //表示空字符串
        String t3= """
                """;
        //表示包含一个换行符
        String t4= """
                
                """;
        System.out.println(t1.length());//3
        System.out.println(t2.length());//4  结束的地方包含了一个换行符
        //其他的包括sql语句
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
 *      7：可终止的G1 Mixed GC (G1=gabage collection first,简称G1:垃圾优先回收机制)
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
 *          可终止的G1 Mixed GC:
 *              简而言之，当G1垃圾回收器的回收超过暂停时间的目标，则能终止垃圾回收过程
 *          支持unicode11:
 *          支持压缩数字格式化：
 *              NumberFormat添加了对紧凑形式格式化数字的支持，
 *          String新增方法:
 *              String#transform(Function):返回该函数返回的输出
 *              String#indent(int n):提供空格符号
 *          Files新增mismatch方法:
 *          新增项
 *          废弃项
 *          移除项
 *
 */


/**
 * JDK13的新特性
 *      5个JEP(JAVA的增强提案)
 *          1：动态CDS档案（CDS:类数据共享）
 *          2：ZGC:取消使用未使用的内存
 *          3：重新实现旧版本套接字API
 *          4：swithc表达式
 *          5：文字块
 *
 *          switch表达式（预览版）
 *              对比jdk12,引入了yield语句，用于返回值，这意味着，switch表达式的返回值应该使用yield,如果不返回，则使用break;
 *          文本块：
 *              文本块就是指多行字符串，例如一段格式化之后的json,xml,有了文本块之后用户就不需要转义，java可以自动给搞定
 *              文本块可以提高java程序的可读性和可写性
 *              文本块是java语言的一种新文字，它可以用来表示任何字符串，并且提供更大的表现力和更小的复杂性
 *              文本块由零个或者多个字符组成，由开始和结束分隔符括起来
 *              """   """表示开始和结束分隔符,开始符号单独占一行
 *              文本块中的内容可以直接使用”，“，但不是必须的
 *              文本块中的内容可以直接包含行终止符，允许再文本块中使用\n，但不是必须的
 *
 */