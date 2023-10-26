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
 *
 */