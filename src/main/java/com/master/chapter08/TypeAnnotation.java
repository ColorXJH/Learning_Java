package com.master.chapter08;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.ArrayList;

/**
 * @ClassName: TypeAnnotation
 * @Package: com.master.chapter08
 * @Description: jdk8之后的类型注解
 * @Datetime: 2023/9/4 21:45
 * @author: ColorXJH
 */
public @interface TypeAnnotation {
}

/**
 * jdk8之后注解可以应用在任何地方
 * jdk8之前注解只能在声明的地方使用
 * jdk8之后新增的Target类别：TYPE_PARAMETER TYPE_USE
 * TYPE_PARAMETER表示该注解能写在类型变量的声明语句中，比如泛型声明
 * TYPE_USE表示该注解能写在类型的任意语句中
 */
@Target(ElementType.TYPE_PARAMETER)
@interface TypeDefine{

}
@Target(ElementType.TYPE_USE)
@interface TypeUseDefine{

}
class TestDefine<@TypeDefine U>{
    private U u;
    public <@TypeDefine T>void test(T t){}
}

class TestUse{
    public void show(){
        ArrayList<@TypeUseDefine String>list=new ArrayList<>();
        int num= (@TypeUseDefine int)10L;
    }
}