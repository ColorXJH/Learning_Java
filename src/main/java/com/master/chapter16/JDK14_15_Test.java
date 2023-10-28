package com.master.chapter16;

import org.junit.Test;

/**
 * @ClassName: JDK14_15_Test
 * @Package: com.master.chapter16
 * @Description:
 * @Datetime: 2023/10/26 20:11
 * @author: ColorXJH
 */
public class JDK14_15_Test {
    public static void main(String[] args) {
        Runnable r=()-> System.out.println("ColorXJH");
    }

    @Test
    public void testInstanceOf(){
        //old
        Object obj="xjh";
        if(obj instanceof String){
            String str=(String)obj;
            String abc = str.concat("abc");
            System.out.println();
        }
        //new
        if(obj instanceof String myStr){//作用域仅限于if块，扩展不到else
            String concat = myStr.concat("-color");
            System.out.println(concat);
        }
        String name=null;
        System.out.println(name.toUpperCase());
    }

    @Test
    public void testRecord(){
        User parent=new User("color",null);
        User user=new User("XJH",parent);
        System.out.println(user.toString());
        //new
        UserRecord pr=new UserRecord("good",null);
        UserRecord sr=new UserRecord("DAY DAY UP",pr);
        System.out.println(sr.toString());
        System.out.println(sr.name());
        System.out.println(sr.parent());
    }
    @Test
    public void testRecordFeature(){
        Person person=new Person("ColorXJH");
        person.setNation("china");
        Person.setNation("CHINA");
        var nameToUpperCase = person.getNameToUpperCase();
        System.out.println(nameToUpperCase);
    }

    @Test
    public void testSwitch(){
        String name="ColorXJH";
        switch (name){
            case "xjh"->System.out.println("hahaha");
            case "color"->System.out.println("xixixi");
            case "ColorXJH"-> System.out.println("JDK14_15_Test.testSwitch");
            default -> throw new RuntimeException("出错了");
        }
        int age=11;
        int general=switch (age){
            case 10->1;
            case 11-> {
                System.out.println("xixi");
                yield 22;
            }
            case 12->3;
            default -> throw new RuntimeException("出错了");
        };
        System.out.println(general);
        int age1=11;
        int genera2=switch (age1){
            case 10:yield 1;
            default : throw new RuntimeException("出错了12344");
        };
        System.out.println(genera2);

    }
    @Test
    public void testTextBlock(){
        String name="<html>\n" +
                " \t<p>hello</p>\n" +
                "</html>";
        //jdk13 内有换行
        String test= """
                <html>
                 	<p>hello</p>
                </html>""";
        //末尾多了一个换行。tes2.length比test.length大一个
        String tes2= """
                <html>
                 	<p>hello</p>
                </html>
                """;
        //jdk14新特性 \表示取消换行操作  \s表示空格 \s\=>空格+不要换行
        String test3= """
                <html>\s\
                 	<p>hello</p>\s\
                </html>""";
        System.out.println(name);
        System.out.println(test3);
        System.out.println(test.length());
        System.out.println(tes2.length());


    }




























}

/**
 * 从哪几个角度学习新特性
 *      1:语法层面
 *      2:API层面
 *      3:底层优化
 *
 *      java14发布 16大新特性，代码更加简洁明快
 *          此版本包含的JEP(java增强提案 JDK Enhancement Proposal),拥有16个新特性。包括两个孵化器模块，三个预览特性，两个弃用的功能，两个删除的功能
 *          1：孵化器模块：
 *              将尚未定稿的api和工具先交给开发者使用，以获得反馈，并用这些反馈进一步改善java平台的质量
 *          2：预览特性：
 *              是规格已经成型，实现已经确定，但还未最终定稿的功能，他们出现在java中的目的是收集在真实世界中使用后的反馈信息，促进这些功能的最终定稿
 *              这个特性可能随时会改变，根据反馈结果，这些特性甚至可能会被移除，但是通常所有的预览特性最后都会在java中固定下来
 *      正式的有关代码改变的新特性：
 *          1：instanceof的模式匹配：
 *              他为更为通用的模式匹配打开了大门，模式匹配通过更为简便的语法基于一定的条件来抽取对象的组件，而instanceof刚好是这种情况，它先检查
 *              对象的类型，然后在调用对象的方法和访问对象的手段，使用它可以减少强制类型转换，实现简单安全的代码
 *          2：非常实用的NullPointerException
 *              该特性改进了NullPointerException的可读性，能更加准确地给出null变量的信息
 *                  在idea的vm options中添加 -XX:+ShowCodeDetailsInExceptionMessages;后续可能设置为默认开启
 *          3：Record(预览特性Preview)：
 *              难题：当程序员想要创造纯数据载体类的时候，通常需要编写大量的重复的模板是的代码，get/set 构造器，equals hashcode方法等等
 *              解决方案：使用record来减少类声明语法，效果类似lombok的@Data注解，kotlin中的dataclass,他们的共同特点是类的部分或者全部状态
 *              可以在类头中描述，并且这个类中只包含数据，实现了类的京凑型声明，减少了模板代码
 *                  当你用Record声明一个类时，该类将自动拥有以下功能：
 *                      1：获取成员变量的简单方法
 *                      2：一个equals方法的实现
 *                      3：一个hashcode方法的实现
 *                      4：一个可以打印该类所有成员属性的toString方法
 *                      5：请注意只会有一个构造方法
 *                      6：和枚举类型一样，记录也是类的一种受限形式，作为回报记录对象在间接性方面提供了显著的好处
 *          4：switch表达式
 *              switch可以当作语句使用，也可以当作表达式使用
 *              使用->代替以前的:+break;另外还提供了yield来在block中返回值
 *          5：文本块：预览第二版
 *              问题：在java中通常需要使用String表示html,xml,sql,json字符串，这些字符串在赋值时需要转移加链接操作
 *              那以维护且很不方便
 *              解决：
 *                  1：简化跨越多行字符串，避免对特殊字符进行转义
 *                  2：增强可读性
 *                  3：解析新的转义序列
 *                      \ \s
 *          6：弃用ParallelScavenge和SerialOld GC
 *          7：删除CMS垃圾回收器，（G1已经默认好几年了）
 *          8：ZGC Shenandoah两大新的垃圾回收器（主打停顿时间）
 *          9：打包工具（孵化器模块）
 *             这个工具为开发者带来了一种打包java应用的方式，目的在于创建一种简单的打包工具，可以用于构建exe,pkg,dmg,deb,rpm格式的安装文件
 *          10：G1的NUMA-Aware的内存分配
 *              改功能改进了G1垃圾回收器在非一致内存访问（NUMA）系统上的整体性能
 *          11：JFR事件流：
 *              java为了更方便的了解JVM运行的情况，在之前JDK11的版本中引进了JFR特性：java飞行记录仪，新的特性中，可以公开JDK飞行记录仪的数据，用于持续监视
 *              从而简化各种工具和应用程序对JFR数据的访问
 *          12：非易失性映射字节缓冲区
 *              在JEP352中，对FileChannelAPI进行了扩展，以允许创建MappedByteBuffer实例，与易失性存储器RAM不同，他们在非易失性数据存储（NVM 非易失性存储器）
 *              上工作，但是，目标平台是Linux x64
 *              非易失性内存能够持久保持数据，因此可以利用改特性来改进性能
 *

 *
 */

/**
 * idea中添加emoji表情：
 * 首先，切换到微软输入法 然后按住ctrl+shift+b emoji图片选择框就出来了 然后就找个注释随便加吧 让看注释的人也能体会到你的情
 * 😉
 */



/**
 * java 15的新特性
 *      EdDSA数字签名算法
 *          与现有的jdk签名方案相比EdDSA具有更高的安全性和性能，因此备受关注，已经在OpenSSL和BoringSSL等加密库中得到了支持，是一种现在的椭圆曲线方案，具有jdk中现有签名算法的优点
 *      密封类（预览）
 *          密封的类和接口：
 *              通过密封的类和接口来增强java编程语言，是新的预览特性
 *              用于限制超类的使用，密封的类和接口限制其他可能继承或实现他们的其他类或接口
 *              具体使用：
 *                  使用修饰符sealed,你可以将一个类声明为密封类，密封的类使用reserved关键字permits列出可以直接扩展他的类，子类可以是最终的，密封的或者非密封的
 *      隐藏类
 *          该提案通过启用标准API来定义无法发现且具有有限生命周期的隐藏类，从而提高JVM上所有语言的效率，jdk内部和外部的框架能够动态生成类，而这些类可以定义隐藏类，通常来说jvm的
 *          很多语言都有动态生成类的机制，这样可以提高语言的灵活性和效率
 *              隐藏类天生为框架而设计，在运行时生成内部class
 *              隐藏类只能通过反射访问，不能直接被其他类的字节码访问
 *              隐藏类可以独立于其他类加载、卸载、这样可以减少框架的内存占用
 *          定义：
 *              就是不能直接被其他class的二进制代码使用的class,它主要被一些框架用来生成运行时类，但是这些类不是被用来直接使用的，而是通过反射机制来调用
 *              比如在jdk8中引入了lambda表达式，jvm并不会在编译时期将lambda表达式转换为专门的类，而是在运行时将相应的字节码动态生成相应的类对象
 *              另外，使用动态代理也可以为某些类生成新的动态类
 *          那么我们希望这些动态生成的类需要具有什么特性呢？：
 *              不可发现性：应为我们是为某些静态的类动态生成的动态类，所以我们希望把这个动态生成的类看作是静态类的一部分，所以我们不希望除了该静态类之外的其他机制发现
 *              访问控制：我们希望在访问控制静态类的同时，也能控制到动态生成的类
 *              生命周期：动态生成的类的生命周期一般都比较短，我们并不需要将其保存和静态的生命周期一致
 *          API的支持：
 *              所以我们需要一些api来定义无法发现的且具有有限生命周期的隐藏类，这将提高所有基于jvm的语言实现的效率
 *                  java.lang.refelect.Proxy可以定义隐藏类作为实现代理接口的代理类
 *                  java.lang.invoke.StringConcatactory可以生成隐藏类来保存常量连接方法
 *                  java.lang.invoke.LambdaMetaFactory可以生成隐藏类的nestmate类，以容纳访问封闭变量的lambda主体
 *                      普通类是通过调用ClassLoader::defineClass创建的，而隐藏类是通过调用Lookup::defineHiddenClass创建的，这使JVM从提供的字节码中，
 *                      派生一个隐藏类，连接该隐藏类，并返回提供对隐藏类的反射访问的查找对象，调用程序可以通过返回的查找对象来获取隐藏类的Class对象
 *      移除  Nashorn JavaScript引擎
 *          取而代之的是GraalVM:在HotSpot基础上增强而形成的跨语言全栈虚拟机
 *      重新实现Legacy DatagramSocket API
 *          java.net.datagram.Socket和java.net.MulticastSocket的当前实现可以追溯到jdk1.0,那时候ipv6还在开发中，因此当前的多播套接字实现尝试调和ipv4和ipv6难以维护的方式
 *          通过替换java.net.datagram的基础实现，重新实现旧版DatagramSocket API
 *          更改java.net.DatagramSocket和java.net.MulticastSocket为更简单、现代化的底层实现
 *      禁用偏向锁定
 *          HotSpot虚拟机使用改优化来减少非竞争锁定的开销
 *      instanceof模式匹配（第二次预览）
 *          继java14引入之后，没有做任何修改
 *      ZGC:一个可扩展的低延迟垃圾回收器
 *          继java11引入的垃圾回收器，经过多次试验，自此终于成为正式特性，支持Linux MacOS Windows
 *          非默认，默认是：G1，现在只需要-XX:+UseZGC即可
 *      文本快
 *          jdk13引入文本块text-block,现在已经转正
 *      Shenandoah:地暂停时间垃圾收集器
 *          与ZGC类似，转正了，目前只存在于OpenJDK中
 *      移除Solaris和SPARC端口
 *          主要原因是近年来Solaris和SPARC操作系统已经被Linux操作系统和英特尔处理器所取代，放弃对Solaris和SPARC端口的支持将使OpenJDK社区的贡献者能加速开啊新功能，推动平台向前发展
 *      外部存储访问API(第二次孵化版本)
 *          目的是引入一个api,以允许java程序安全有效的访问java堆之外的外部存储器，如本机，持久和托管堆，有许多java程序是访问外部内存的，比如Ignite和MapDB，该API将有助于
 *          避免与垃圾收集器相关的成本以及与开进程共享内存以及通过将文件映射到内存来序列化和反序列化内存内容相关的不可预测性：Foreign-Memory-Access API
 *      Records(第二次预览)
 *          不能声明为abstract
 *          不可以显示继承其他类
 *      废弃RMI激活机制
 *          远程方法调用的激活机制是RMI中一个过时的部分，RMI的其他部分暂时不会被弃用，在rmi系统中我们使用延迟激活，将激活对象推迟到客户第一次使用（即第一次方法调用）之前，
 *          既然rmi激活机制这么好用，为什么要移除呢？
 *              因为对于现代应用程序来说，分布式系统大部分都是基于web的，web服务器已经解决了穿越防火墙、过滤请求、身份验证和安全性的问题，并且也提供了很多延迟加载的技术，所以
 *              现代程序应用中，RMI Activation已经很少被用到了 ，jdk8中可选，jdk15废弃
 *      给CharSequence接口新增了isEmpty默认方法
 *          default boolean isEmpty(){return this.length()==0}
 *      对TreeMap提供了putIfAbsent,computeIfAbsent,computeIfPresent,compute,merge方法提供了overriding实现
 * 总结：
 *      jdk15整体看来新特性方面并不是算很亮眼，它主要是对之前版本预览特性功能做了确定，如文本块 ZGC,这样一来就可以放心大胆的使用了
 *      你发任你发，我用java8
 */