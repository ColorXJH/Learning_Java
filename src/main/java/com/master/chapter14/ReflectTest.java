package com.master.chapter14;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName: ReflectTest
 * @Package: com.master.chapter14
 * @Description: java的反射
 * @Datetime: 2023/10/9 20:29
 * @author: ColorXJH
 */
public class ReflectTest {
    public static void main(String[] args) {

    }

    //反射之前对于Person类的操作
    @Test
    public void beforeReflect(){
        //1 创建Person类的对象
        Person person=new Person("Tom",12);
        //2 通过对象，调用其内部的属性和方法
        person.age=10;
        System.out.println(person.toString());
        person.show();
        //在person类外部，不可以通过其对象 调用其内部私有的结构，比如私有方法，私有变量 私有的构造器，受到封装性的限制
    }
    //反射之后，对person的操作
    @Test
    public void afterReflect() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        //1: 通过反射创建Person类的对象
        //获取Class对象
        Class<Person> personClass = Person.class;
        //获取对象的构造器
        Constructor<Person> constructor = personClass.getConstructor(String.class, int.class);
        //构造对象
        Person jerry = constructor.newInstance("Jerry", 28);
        System.out.println(jerry);

        //2: 通过反射，调用对象指定的属性、方法
        Field age = personClass.getDeclaredField("age");
        age.set(jerry,27);
        System.out.println(jerry);

        Method show = personClass.getDeclaredMethod("show");
        show.invoke(jerry);
        //3: 通过反射可以调用Person类的私有方法 属性 构造器
        Method showNation = personClass.getDeclaredMethod("showNation", String.class);
        //改变方法的访问范围
        showNation.setAccessible(true);
        Object china = showNation.invoke(jerry, "China");
        System.out.println(china);
        Field name = personClass.getDeclaredField("name");
        name.setAccessible(true);
        name.set(jerry,"ColorXJH");
        System.out.println(jerry);
        Constructor<Person> constructor1 = personClass. getDeclaredConstructor(String.class);
        constructor1.setAccessible(true);
        Person kcy_zyf = constructor1.newInstance("KCY_ZYF");
        System.out.println(kcy_zyf);


    }

    //4:获取Class实例的4中方式
    public void testGetClassInstanceType() throws ClassNotFoundException {
        //方式1：调用运行时类的属性 .class
        Class<Person> personClass = Person.class;
        System.out.println(personClass);
        //方式2：通过运行时类的对象获取getClass()方法
        Person ps=new Person();
        Class<? extends Person> aClass = ps.getClass();
        //方式3：调用Class的静态方法forName(String classpath)
        Class<?> aClass1 = Class.forName("com.master.chapter14.Person");
        //加载到内存中的运行时类，会缓存一定的时间，在此时间之内，我们可以通过不同的方式来获取此运行时类
        System.out.println(personClass==aClass);//true
        System.out.println(personClass==aClass1);//true
        //方式4：使用类的加载器：ClassLoader
        ClassLoader classLoader = ReflectTest.class.getClassLoader();
        Class<?> aClass2 = classLoader.loadClass("com.master.chapter14.Person");
        System.out.println(aClass2==personClass);//true
        //方式3的使用频率较高

    }
}

/**
 * 反射被视为动态语言的关键，反射机制允许程序在执行期间借助Reflection API取得任何类的内部信息，并能直接操作任意对象的内部属性及方法
 * 加载完成类之后，在堆内存的方法区中就产生了一个Class类型的对象（一个类只有一个Class对象），这个对象就包含了完整的类的结构信息，我们可以通过
 * 这个对象看到类的结构，这个对象就像一面镜子，透过这个镜子可以看到类的结构，所以我们称之为：反射
 *  正常方式：引入需要的“包类”名称-》通过new实例化-》取得实例化对象
 *  反射方式：实例化对象-》getClass()方法-》得到完整的“包类”名称
 *      反射机制研究及应用
 *          1：在运行时判断任意一个对象所属的类
 *          2：在运行时构造任意一个类的对象
 *          3：在运行时判断任意一个类所具有的成员变量和方法
 *          4：在运行时获取泛型信息
 *          5：在运行时调用任意一个对象的成员变量和方法
 *          6：在运行时处理注解
 *          7：生成动态代理
 *              主要相关API:
 *                  java.lang.Class:代表一个类
 *                  java.lang.reflect.Method:代表类的方法
 *                  java.lang.reflect.Field:代表类的成员变量
 *                  java.lang.reflect.Constructor:代表类的构造器
 */

/**
 *  疑问：通过直接new对象的方式或反射的方式都有可以调用公共的结构，开发中到底用哪个？
 *      建议使用直接new的方式，什么时候会使用到反射的方式？
 *             动态性，编译的时候无法确定使用哪个类的对象，就使用反射的方式
 *                  例如：服务器代码跑起来了，根据请求url，动态创造不同的类的对象执行不同的方法
 *  疑问：反射机制与面向对象中的封装性是不是矛盾的？如何看待两个技术？
 *      不矛盾，封装性是解决建议使用哪些属性方法，反射是技术上的可实现性，动态性
 */


/**
 * Class的理解 对于Person类有不同的实例对象
 * 那么对于许多特定的类，抽象出来一个更高的层级，都有属性 构造器 方法，等
 * 这些类就可以使用新的一个类:Class来表示，它表明每一个类都共有的一些属性
 * 类的加载过程：java程序编写完成之后-》javac.exe（编译器编译过程，得到字节码文件）-》java.exe(java虚拟机JVM:解释执行过程,相当于将某个字节码文件加载到内存中)
 *      此过程（加载的过程）被称为类的加载，加载到内存中的内就成为运行时类，此时的类就作为Class的一个实例，比如Person.class字节码文件加载到内存之后，其本身就充当Class类的一个实例
 *          以前通过类去造对象-》现在类本身也是一个对象，是Class的对象 Class类是class类的更高一级抽象 将所有的class类本身抽象出来，得到一个新的类Class,注意大小写
 *          换句话说：Class的实例就对应一个运行时类
 *              万事万物皆对象：对象.xxx;File;URL;类本身也是对象=》反射;前端，数据库，在java层面都是对象
 */