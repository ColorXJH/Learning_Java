package com.master.chapter16;

import org.junit.Test;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: jdk9及其以上版本介绍
 * @date 2023-10-18 15:23
 */
public class HigherJavaTest {
    public static void main(String[] args) {
        HigherJavaTest test=new HigherJavaTest();
        test.testBeforeJava8();
        test.testInputStreamAdd();
    }
    @Test
    public void testBeforeJava8(){
        InputStreamReader reader= null;
        try {
            reader = new InputStreamReader(System.in);
            char[] ch=new char[20];
            int len;
            if((len=reader.read(ch))!=-1){
                String str=new String(ch,0,len);
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(reader!=null)
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void testOnJava8(){
        //资源的自动关闭
        try(InputStreamReader reader=new InputStreamReader(System.in);)
        {
            char[] ch=new char[20];
            int len;
            if((len=reader.read(ch))!=-1){
                String str=new String(ch,0,len);
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testAfterJava8(){
        //自动关闭的资源的实例化是可以放在try外部的，可以使用，不可修改
        InputStreamReader reader=new InputStreamReader(System.in);
        //OutputStreamWriter writer=new OutputStreamWriter(System.out);
        try(reader;/*writer;*/) {
            char[] ch=new char[20];
            int len;
            if((len=reader.read(ch))!=-1){
                String str=new String(ch,0,len);
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //jdk9:集合工厂方法创建只读集合
    @Test
    public void testCollectionFactoryCreateReadOnlyCollection(){
        //之前 list set map等
        List<String> list=new ArrayList<>();
        list.add("123");
        list.add("234");
        list= Collections.unmodifiableList(list);
        //list.add("4677");
        //这个方法也创建了不可修改的集合
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);

        //jdk8以及之后得写法：
        Map<String,Integer> map=Collections.unmodifiableMap(new HashMap<String,Integer>(){
            {
                put("a",1);
                put("b",2);
                put("c",3);
                put("d",4);
            }
        });
        map.forEach((k,v)-> System.out.println(k+"v"));
        //下面也是9创建只读的集合
        List<Integer> integers1 = List.of(1, 2, 3, 4, 5, 6);
        Set<Integer> integers2 = Set.of(2, 3, 4, 5, 5, 5, 6);
        Map<String, Integer> tom = Map.of("tom", 23, "xjh", 33);
        Map<Integer, Integer> integerIntegerMap = Map.ofEntries(Map.entry(123, 345), Map.entry(345, 567));

    }

    @Test
    public void testInputStreamAdd(){
        ClassLoader cl=this.getClass().getClassLoader();
        //test  main方法都一样
        //资源文件夹下查找
        try(InputStream is=cl.getResourceAsStream("name.txt");
            //项目目录下存放
        OutputStream os=new FileOutputStream("name_copys.txt");
        ) {
            //把输入流中的数据直接复制到输出流中
            is.transferTo(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStreamApiAdd(){
        Stream<Integer> limit = Stream.iterate(0, d -> d + 1).limit(10);
        //返回从头开始的指定规则的尽量多的元素
        limit.takeWhile(x->x<7).forEach(System.out::println);
        Stream<Integer> limit2 = Stream.iterate(0, d -> d + 1).limit(10);
        System.out.println("------------");
        //返回剩余的元素
        limit2.dropWhile(x->x<7).forEach(System.out::println);
        System.out.println("------------");
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, null);//5个
        //不能只有null,可以多个值，其中包含一个空的
        //Stream<Integer> integerStream2 = Stream.of(null);//5个
        //下面是可以的
        Stream<Integer> integerStream2 = Stream.of(null,null);//5个
        integerStream2.forEach(System.out::println);
        Integer i=10;
        i=null;
        //允许创建一个参数为null的流 类似OPptional的ofNullable
        Stream<Integer> integerStream1 = Stream.ofNullable(i);
        //对比jdk8新增了一个自定义的终止条件
        Stream.iterate(0,x->x<100,x->x+1).forEach(System.out::println);

    }
    @Test
    public void testOptionalStream(){
        List<String> list=new ArrayList<>();
        list.add("Tom");
        list.add("Jerry");
        list.add("Tim");
        Optional<List<String>>optional=Optional.ofNullable(list);
        Stream<List<String>> stream = optional.stream();
        Stream<List<String>> stream1 = optional.stream();
        stream.forEach(System.out::println);
        stream1.flatMap(x->x.stream()).forEach(System.out::println);

    }
}

/**
 * JDK9
 *  新特性
 *      模块化系统
 *      jShell命令
 *      多版本兼容jar包
 *      接口的私有方法
 *      钻石操作符的使用升级（范型）
 *      语法改进：try语句
 *      String存储结构变更
 *      便利的集合特性：of()
 *      增强的Stream API
 *      全新的http客户都安api
 *      Deprecated相关的api
 *      javac的html5支持
 *      javascript引擎升级：Nashorn
 *      java的动态编译器
 */

/**
 * jdk8及其以前的版本目录结构：bin db include jre（下级bib lib目录） lib
 *      bin:包含命令行开发和调试工具，例如javac,jar、javadoc
 *      include:包含在编译本地代码时使用的c/c++头文件
 *      lib:包含jdk工具的几个jar和其他类型的文件，它有一个tools.jar,其中包含javac编译器的java类
 *      jre/bin:包含基本命令，例如java命令，在windows平台上，它包含系统的运行时动态链接库DLL
 *      jre/lib:包含用户可以编辑的配置文件，如.properties和.policy文件，包含几个jar，rt.jar文件包含运行时的java类和资源文件
 * jdk8之后的目录结构：bin conf include jmods legal lib
 *      bin:包含所有命令，在windows平台上，它继续包含系统的运行时动态链接库
 *      conf:包含用户可编辑的配置文件
 *      include:包含要在以前编译本地代码时使用的c/c++头文件，它只存在与jdk中
 *      jmods:包含JMOD格式的平台模块，创建自定义运行时映像时需要它，它只存在于jdk中
 *      legal:包含法律声明
 *      lib:包含非windows平台上的动态链接本地库，其子目录和文件不应由开发人员直接编辑或使用
 * Module：模块化系统
 *      模块将由通常的类与新的模块声明文件（module-info.java）组成，该文件是位于java代码结构的顶层，该模块描述符明确的定义了我们的模块需要什么依赖关系
 *      以及哪些模块被外部使用，在exports子句中未提及到的包默认情况下将封装在模块中，不能在外部使用
 * Java的REPL工具：jshell命令
 *      像python,Scala之类的语言早就有了交互式编程环境REPL(read-evaluate-print-loop)了，以交互式的方式对语句和表达式进行求值，开发者只需要输入一些代码，
 *      就可以在编译前获得对程序的反馈，而之前的java版本想要执行代码，必须创建文件，声明类，提供测试方法才能实现
 *      设计理念：即写即得，快速运行
 *      实现目标:java9中终于拥有的REPL工具，jShell，让java可以像脚本语言一样执行，从控制台启动jShell，利用jShell在没有类的情况下直接声明变量，计算表达式
 *      执行语句，即开发时可以在命令行内直接运行java代码，而无需创建java文件，无需再书写你main方法了
 *          jShell也可以从文件中加载语句或者将语句保存在文件中
 *          jShell也可以是tab进行自动补全和自动添加分号
 *      如何使用，直接在对应jdk版本的bin目录下cmd,进入之后命令行直接运行jshell.exe
 *      /help 请求使用方法  /help intro
 *      /edit 调出刚才定义的一些操作记录
 *      可以定义变量 方法，类，导包，也可以导入第三方jar包
 *      /imports 默认导入的包
 *      方法，变量 类可以覆盖之前写的
 *      /open 文件路径，调用脚本文件（就是在命令行中的代码文件）
 *      jShell没有受检异常，本来应该强迫我们捕获的，但是jShell在后台为我们隐藏掉了，所以我们声明哪些带受检异常的操作时不需要try-catch了
 *      /exit 退出
 * 接口的私有方法
 *      java8中规定接口中的方法除了抽象方法之外，还可以定义静态方法和默认方法，在一定的基础上扩展了接口的功能，此时的接口更像是一个抽象类
 *              接口中出现了方法体
 *      在java9中，接口更加灵活和强大，连方法的访问权限修饰符都可以为private的了，此时方法讲称为你对外暴露的api的一部分
 * 钻石操作符的使用升级
 *      jdk9之后，我们将能够与匿名实现类共同使用钻石（范型）操作符，在java8中如下操作时会报错的
 *          Comparator<Object>com=new Comparator<>(){
 *                  @Override
 *                  public int compare(Object o1,Object o2){
 *                      return 0;
 *                  }
 *          }
 *          JDK8中会出现编译报错，cannot use <> with anonymous inner class
 * 语法改进：try语句
 *      java8中，可以实现资源的自动关闭，但是要求执行后必须关闭的所有资源必须在try中完成初始化，否则不能通过编译，如下：
 *          try(InputStreamReader reader=new InputStreamReader(System.in)){
 *              //读取数据细节
 *          }catch(Exception e){
 *              e.printStackTrace();
 *          }
 *      java9中，用资源语句编写try将更加容易，我们可以在try子句中使用已经初始化过的资源，此时的资源是final的
 *          InputStreamReader reader=new InputStreamReader(System.in)
 *          OutputStreamWriter writer=new OutputStreamWriter(System.out);
 *          try(reader;writer;){
 *              //reader/writer是final的，不可被再次赋值
 *          }catch(Exception e){
 *              e.printStackTrace()
 *          }
 * String底层存储结构的变更
 *         动机：
 *              当前的string类春初字符串的实现就是使用字符数组，使用两个字节（16比特位）存储每一个字符，从众多的应用系统收集道德数据，
 *              string字符串是堆空间的主要组成部分，并且，大多数的string字符串是只包含拉丁字符的，这些字符只需要一个字节就可以存储，这样另一半的
 *              就通常处于未使用状态
 *         解释：
 *              我们改变string存储的内部结构，让一个String从一个UTF-16的字符数组编程一个字节数组外加上一个编码标志域，新的string存储字符串编码
 *              即使用iso-8859-1(一个字节存储字符)又使用UTF-16(两个字节存储一个字符)，基于存储的字符串内容，编码标志决定了那种编码方式将被使用
 *              string改变了，那么和string相关的也改变了，比如：StringBuffer/StringBuilder
 * 集合工厂方法：快速创建只读集合
 *      要创建一个只读，不可改变的集合，必须构造和分配它，然后添加元素，最后包装成一个不可修改的集合
 *              List<String>list=new ArrayList<>();
 *              list.add("123");
 *              list.add("234");
 *              list.add("345");
 *              list=Collections.unmodifiableList(list)
 *                  我们一下写了5行，即：他不能表达为单个表达式
 * InputStream加强
 *      inputStream终于有了一个非常有用的方法：transferTo,可以用来将数据直接传输到OutputStream.这是在处理原始数据流时，非常常见的一种做法
 *          详情见上方代码
 * 增强的StreamAPI
 *      JDK9中，stream api变得更好，stream接口中添加了四个新的方法：takeWhile,dropWhile,ofNullable,还有一个iterate方法的重载方法，可以让你
 *      提供一个Predicate(判断条件)来指定什么时候结束迭代
 *      除了对Stream本身的扩展，Optional和Stream之间的结合也得到了改进，现在可以通过Optional的新方法stream(),将一个Optional对象转换成一个
 *      或者可能是空的Stream对象
 * Optional获取Stream的方法
 *      Optional类中stream()方法的使用，
 * Nashorn:javascript引擎升级
 *      nashorn项目在jdk9中得到改进，他为java提供轻量级的javascript运行时环境，nashorn跟随netscape的rhino项目，目的是为了在java中实现一个
 *      高性能但轻量级的javascript运行时，nashorn项目使得java应用能够嵌入javascript.他在jdk8中为java提供一个javascript引擎
 *      java9包含一个用来解析Nashorn的EcmaScript语法数的api,这个api使得ide和服务端框架不需要依赖Nashorn项目的内部实现，就能够分析EcmaScript
 */