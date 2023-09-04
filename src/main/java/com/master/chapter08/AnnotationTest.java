package com.master.chapter08;

import java.lang.annotation.Annotation;
import java.util.ArrayList;

/**
 * @ClassName: AnnotationTest
 * @Package: com.master.chapter08
 * @Description: java的注解
 * @Datetime: 2023/9/4 18:36
 * @author: ColorXJH
 */
public class AnnotationTest {
    public static void main(String[] args) {
        Class<Person> personClass = Person.class;
        Annotation[] annotations = personClass.getAnnotations();
        System.out.println(annotations.getClass());
        for (int i = 0; i < annotations.length; i++) {
            System.out.println(annotations[i]);
        }
    }

    @Deprecated
    public void show(){
        System.out.println("xixi--");
    }
    public void shows(){
        @SuppressWarnings({"unused","rawtypes"})
        ArrayList list=new ArrayList();
        System.out.println("haha");
    }

}

/**
 * 一定程度上说：框架=注解+反射+设计模式
 * 使用annotation时需要在前方加上@符号，并把该annotation当成一个修饰符使用，用于修饰它支持的程序元素
 * jdk内置的三个基本注解：@Override:限定重写父类方法，只能用于方法 @Deprecated:表示所修饰的元素（类，方法等）已过时 @SuppressWarnings:抑制编译器警告
 * Annotation可以像变量修饰符一样被使用，用于修饰包、类、构造器、方法、成员变量、参数、局部变量的声明，信息被保存在name=value中
 */
//@MyAnnotation(value = "Color",desc = "XJH")
    //jdk8之前的写法

@MyAnnotations({@MyAnnotation(value = "Color",desc = "XJH"),@MyAnnotation(value = "Color",desc = "XJH123")})
class Person{

    //JDK8之后的写法

    @MyAnnotation2(value = "Color",desc = "XJH")
    @MyAnnotation2(value = "Color",desc = "XJH")
    private String name;
}
/**
 * 声明：@interface
 * 属性：无参数方法
 * 默认属性 default
 * 没有成员称为标识注解，有成员成为元数据注解
 * 自定义注解必须要配上信息处理流程才有意义（反射）
 */

/**
 * jdk提供的四种元注解:用于修饰其他注解的注解 类似于String name="Color"现有的数据就是“Color” 元数据就是String类型 name变量名称
 * @Retention 指定注解的生命周期 ：RetentionPolicy.SOURCE/CLASS（默认行为）/RUNTIME 分别表示源文件/class文件/运行时有效（这个时刻可以使用反射获取该注解内容）
 * @Target 指定注解可以修饰哪些元素 通常包括CONSTRUCTOR/FIELD/LOCAL_VARIABLE/METHOD/PACKAGE/PARAMETER/TYPE 分别表示可以修饰构造器、域（成员变量）、局部变量、方法、包、参数、type表示类或者接口或者enum声明
 * @Documented 指定注解可以被javadoc工具提取成文档，想生成注解文档  @Retention的值必须是RUNTIME  实例：@Deprecated注解
 * @Inherited 指定注解具有继承性，服了使用了该注解修饰的注解，子类自动拥有该注解修饰的注解
 * 反射的使用
 */