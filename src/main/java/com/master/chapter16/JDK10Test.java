package com.master.chapter16;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: JDK10Test
 * @Package: com.master.chapter16
 * @Description:jdk10新特性
 * @Datetime: 2023/10/21 20:38
 * @author: ColorXJH
 */
public class JDK10Test {
    @Test
    public void testLocalVariableTypeInference(){
        //声明变量时，根据所赋的值，推断变量的类型
        var age=10;
        var list=new ArrayList<Integer>();
        list.add(123);
        list.add(234);
        //遍历操作
        for(var i:list){
            System.out.println(i);
            System.out.println(i.getClass());
        }
        for(var i=0;i<100;i++){
            System.out.println(i);
        }

    }
    //主要是通过右边推断左边
    @Test
    public void unableUseVar(){
        //var name=null;
        //var a=System.out::print;
        //var lamb=()-> System.out.println("lambda");
                //var array=new String[]{"a","b","b","d"};//ok,下面的不可以
        //var array={"a","b","b","d"};
    }

    @Test
    public void testDemo() throws IOException {
          var url=new URL("www.baidu.com");
          var connection=url.openConnection();
          var reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
          //反编译后就是如下的代码：
            //URK url=new URL("http://www.baidu.com");
            //URLConnection connection=url.openConnection();
            //Reader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
          /**
           * 从代码来看，就好像之前已经声明了这些类型一样，事实上，这一特性只发生在编译阶段，与运行时无关，所以对运行时的性能
           * 不会产生任何影响，所以请放心，这不是javascript
           */
    }

    @Test
    public void testCopyOf(){
        //如果本身集合就是只读的，copyOf()创建出来的集合就是同一个
        var list= List.of(123,2323,333);
        var copy=List.copyOf(list);
        System.out.println(list==copy);//true
        //本身集合不是只读的，copyOf()创建出来的集合就不是同一个
        var list2=new ArrayList<String>();
        var copy2=List.copyOf(list2);
        System.out.println(list2==copy2);//false

    }
}

/**
 *  局部变量的类型推断
 *      产生背景：
 *          开发者经常抱怨java中引用代码的程度，局部变量的显示类型声明，常常是被认为是不必须的，给一个好听的名字经常可以很清楚的表达下面应该怎样继续
 *      好处：
 *          减少了啰嗦和形式的代码，避免了冗余，并且对其了变量名，更容易阅读
 *      场景举例：
 *          类实例化时：
 *              作为java开发者，在声明一个变量时，我们总是习惯敲打两次变量类型，第一次用于声明变量类型，第二次用于构造器
 *              LinkedListHashSet<Integer>set=new LinkedListHashSet<>();
 *          返回值类型含复杂泛型结构：
 *              Iterator<Map.Entry<Integer,Student>>iterator=set.iterator();
 *          其他场景：
 *              我们也经常声明一种变量，他们只会被使用一次，而且是使用在下一行代码中，如下：
 *              URK url=new URL("http://www.baidu.com");
 *              URLConnection connection=url.openConnection();
 *              Reader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
 *                  太多的类型声明只会分散注意力，不会带来额外的好处
 *
 *      局部变量类型推断
 *          在局部变量中使用时，如下情况不适用
 *              1：初始值为null: var s=null;
 *              2：方法引用：var i=System.out::println
 *              3：lambda表达式：var i=()->Math.random();
 *              4：为数组静态初始化：var array={'a','b','c','d'}
 *          不适用于一下的结构中
 *              1：没有初始化的局部变量声明  var s=null
 *              2：方法的返回值类型  public var mathod(){return 0;}
 *              3：方法的参数类型   public void dispaly(var x){}
 *              4：构造器的参数类型 public Person(var x){this.x=x}
 *              5：属性   private var name;
 *              6：catch块  catch(Var e){e.printStackTrace()}
 *      工作原理：
 *          在处理var时，编译器先是查看表达式右边的部分，并根据右边变量的值进行推断，作为左边变量的类型，然后将该类型写入字节码当中
 *      注意：
 *          var不是一个关键字
 *              你不需要担心变量名或方法名与var发生冲突，因为var实际上并不是一个关键字，而是一个类型名，只有在编译器需要知道类型的地方才会用到它，
 *              除此之外，他就是一个普通的合法的标识符，总而言之就是：除了不能用它做类名，其他的都可以
 *          这不是javascript
 *              首先需要声明的是：var并不会改变java是静态语言的事实，编译器负责推断出类型，并把结果写入字节码文件，就好像是开发人元自己敲如类型一样
 *          查看这些类的字节码文件（.class文件），通过idea的反编译工具可以双击字节码文件直接得到他的源文件（通过反编译功能），可以看到其文件内容就是类型推断出来的代码
 * 集合新增创建不可变集合的方法
 *      java9中为集合（List/Set/Map）都添加了of()方法
 *      java10中为集合添加了copyOf()方法，用来创建不可变的集合
 */