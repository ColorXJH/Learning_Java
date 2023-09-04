package com.master.chapter08;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 枚举类和注解
 * @date 2023-09-01 8:43
 */
public class EnumAndAnnotationTest {
    public static void main(String[] args) {
        Map<String, String> person1 = new HashMap<>();
        person1.put("name", "John");
        person1.put("age", "25");
        person1.put("addr", "123 Main St");

        Map<String, String> person2 = new HashMap<>();
        person2.put("name", "John");
        person2.put("age", "25");
        person2.put("addr", "456 Elm St");

        Map<String, String> person3 = new HashMap<>();
        person3.put("name", "Mike");
        person3.put("age", "35");
        person3.put("addr", "789 Oak St");

        List<Map<String, String>> list = Arrays.asList(person1, person2, person3);

        List<Map<String, String>> distinctList = list.stream()
                .map(map -> {
                    Map<String, String> newMap = new HashMap<>();
                    newMap.put("name", map.get("name"));
                    newMap.put("age", map.get("age"));
                    return newMap;
                })
                .distinct()
                .collect(Collectors.toList());
        distinctList.stream().forEach(stringStringMap -> System.out.println(stringStringMap));

        System.out.println(Season.SPRING);
        Season.SPRING.show();
        Season.SUMMER.show();

    }
}
/**
 * 当定义一组常量时，强烈建议使用枚举类
 * 类的对象只有有限个，确定的，我们称此类为枚举类
 * 如果枚举类中只有一个对象，则可以作为单例模式的实现方式
 * 定义枚举类的方式 1：jdk5.0之前自定义：2：jdk5.0之后的可以使用enum关键字定义枚举类
 * 枚举类型的常见方法：values() valueOf() toString() ordinal()
 * 使用enum关键字定义的枚举实现接口的情况:情况1：实现的普通接口，实现方法；
 *                                 情况2：jdk5之后的每个对象都需要实现相应的方法
 */
class SeasonTest{
    //1 声明season对象的属性

    private final String name;
    private final String desc;
    //2 私有化构造器

    private SeasonTest(String name,String desc){
        this.desc=desc;
        this.name=name;
    }
    //3 提供当前枚举类的多个对象

    public static final SeasonTest SPRING=new SeasonTest("spring","spring is worm");
    public static final SeasonTest SUMMER=new SeasonTest("summer","summer is hot");
    public static final SeasonTest AUTUMN=new SeasonTest("autumn","autumn is wild");
    public static final SeasonTest WINTER=new SeasonTest("winter","winter is cold");

    //4 获取枚举类对象的属性
    public String getSeason(){
        return name;
    }
    public String getDesc(){
        return desc;
    }

    //5 toString

    @Override
    public String toString() {
        return "SeasonTest{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}

enum Season implements Info{
    SPRING("spring","warm"){
        @Override
        public void show() {
            System.out.println("春天在哪里呀");
        }
    },
    SUMMER("summer","hot"){
        @Override
        public void show() {
            System.out.println("夏天夏天悄悄过去留下小秘密");
        }
    },

    AUTUMN("autumn","wild"){
        @Override
        public void show() {

        }
    },
    WINTER("winter","cold"){
        @Override
        public void show() {

        }
    };
    private final String name;
    private final String desc;
    private Season(String name,String desc){
        this.desc=desc;
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public String getDesc(){
        return desc;
    }

    //这里重写会导致每一个实例的show方法都是以一样的，也可以在上方为每个实例创建show方法

//    @Override
//    public void show() {
//
//    }
    //toString方法被重写了
    //枚举常量的name属性是由编译器自动生成的，它的值就是枚举常量的名称，与传入的name参数无关
}
interface Info{
    void show();
}