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
 *          9：
 *
 */

/**
 * idea中添加emoji表情：
 * 首先，切换到微软输入法 然后按住ctrl+shift+b emoji图片选择框就出来了 然后就找个注释随便加吧 让看注释的人也能体会到你的情
 * 😉
 */