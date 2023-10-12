package com.master.chapter14;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

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
    @Test
    public void testWhoCanHaveClassObject(){
        Class c1=Object.class;
        Class c2=Comparable.class;
        Class c3=String[].class;
        Class c4=int[][].class;
        Class c5= ElementType.class;
        Class c6=Override.class;
        Class c7=int.class;
        Class c8=void.class;
        Class c9=Class.class;
        int[]a=new int[10];
        int[]b=new int[100];
        Class a1=a.getClass();
        Class b1=b.getClass();
        //只要元素类型与维度一样，就是同一个Class
        System.out.println(a1==b1);
    }
    @Test
    public void testClassLoader(){
        //自定义类使用系统类加载器
        ClassLoader classLoader = ReflectTest.class.getClassLoader();
        System.out.println(classLoader);
        //获取扩展类加载器
        ClassLoader parent = classLoader.getParent();
        System.out.println(parent);
        //引导类加载器（是无法获取的）,主要负责加载java的核心类库
        ClassLoader parent1 = parent.getParent();
        //null 不是没有，只是你获取不到引导类加载器
        System.out.println(parent1);
        //核心类库的类加载器我们是无法拿到的
        ClassLoader classLoader1 = String.class.getClassLoader();
        System.out.println(classLoader1);
    }
    @Test
    public void testUseClassLoaderLoadProperties() throws IOException {
        //1：Properties 用来读取配置文件的
        Properties properties=new Properties();
//        FileInputStream inputStream=new FileInputStream("jdbc.properties");
//        properties.load(inputStream);

        //2：使用类加载器加载配置文件
        ClassLoader classLoader = ReflectTest.class.getClassLoader();
        //此时的识别位置不在项目下，resources资源目录下
        InputStream resourceAsStream = classLoader.getResourceAsStream("jdbc2.properties");
        properties.load(resourceAsStream);

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        System.out.println(user+"--"+password);



    }
    @Test
    public void testCreateRuntimeClassObject() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        //创建运行时类的对象 通过反射而不是new
        Class<?> aClass = Class.forName("com.master.chapter14.Person");
        //创建对应的运行时类的对象，注意它和获取类的构造器的区别，这里需要访问权限，获取构造器可以直接设置访问权限
        Object o = aClass.newInstance();
        System.out.println(o);
        Person person=(Person) o;
        person.show();
        //javabean要求类提供一个空参的构造器：
            //1:便于反射创建运行时类的对象
            //2:便于子类继承此运行时父类的时候，默认调用super()时，保证父类有此构造器
    }

    //创建一个指定类的对象，体会反射的动态性
    @Test
    public void testCreateSomeClassInstance() throws Exception {
        Object instance = getInstance("com.master.chapter14.Person");
        Person person=(Person) instance;
        person.show();
    }
    private Object getInstance(String classpath) throws Exception{
        Class<?> aClass = Class.forName(classpath);
        return  aClass.newInstance();
    }
    
    @Test
    public void showClassStructure(){

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

/**
 * 哪些类型可以有Class对象：
 *  1: class: 外部类，成员（成员内部类，静态内部类），局部内部类，匿名内部类
 *  2: interface: 接口
 *  3: 数组【】
 *  4: enum: 枚举
 *  5: annotation: 注解@interface
 *  6: primitive type: 基本数据类型
 *  7: void
 */

/**
 * 类的加载过程与ClassLoader的理解
 *      当程序主动使用某个类时，如果该类还未被加载到内存中，则系统会通过如下三个步骤来对该类进行初始化
 *          1：类的加载load
 *              将类的class文件读入内存，并为之创建一个java.lang.Class对象，此过程由类加载器完成
 *                  将class文件字节码内容加载到内存中，并将这些静态数据转换为方法区的运行时数据结构，然后生成一个代表这个类的java.lang.Class对象
 *                  作为方法区中类数据的访问入口（即引用地址），所有需要访问和使用类数据只能通过这个Class对象，这个加载的过程需要类加载器参与
 *          2：类的连接Link
 *              将类的二进制数据合并到JRE中
 *                  将java的二进制代码合并到JVM的运行状态之中的过程
 *                  验证：确保加载的类信息符合JVM规范，例如 以cafe开头，没有安全方面的问题
 *                  准备：正式为类变量（static）分配内存并设置变量默认初始值的阶段，这些内存都将在方法区中进行分配
 *                  解析：虚拟机常量池内的符号引用（常量名）替换为直接引用（地址）的过程
 *          3：类的初始化Initialize
 *              JVM负责对类进行初始化
 *                  执行类构造器<clinit>()方法的过程，类构造器<clinit>()方法是由编译期自动收集类中所有类变量的赋值动作和静态代码块中的语句合并产生的
 *                  类构造器是构造类信息的，不是构造该类对象的构造器
 *                  当初始化一个类的时候，如果发现其父类还没有进行初始化，则需要先触发其父类的初始化
 *                  虚拟机会保证一个类的<clinit>()方法在多线程环境下被正确加锁和同步
 *          过程如下：
 *              源程序（。java文件）-》java编译器-》字节码（。class文件）-》类加载器-》字节码校验器-》解释器-》操作系统平台
 *          类加载器的作用：
 *              类加载的作用；将字节码文件加载到内存中，并将这些静态数据转换为方法区的运行时数据结构，然后在堆中生成一个java.lang.Class对象，
 *                  作为方法区中类数据的访问入口
 *              类缓存的作用：标准的javase类加载器可以按要求查找类，但是一旦某个类被加载到类加载器中，它将维持加载（缓存）一段时间，不过JVM垃圾回收机制可以
 *                  回收这些Class对象
 *          了解ClassLoader:
 *              类加载器的作用是把类class加载进内存的，JVM规范定义了如下类型的类加载器
 *              自定义类加载器=》System ClassLoader=>Extension ClassLoader=>Bootstrap ClassLoader
 *                      从左到右检查类是否装载   ，从右到左尝试加载类
 *                      Bootstrap ClassLoader:引导类加载器：用c++编写，是jvm自带的类加载器，负责java平台核心库，用来装载核心类库，该加载器无法直接获取
 *                      Extension ClassLoader:扩展类加载器：负责jre/lib/ext目录下的jar包或者-D java.ext.dirs指定目录下的jar包装入工作库
 *                      System ClassLoader:系统类加载器：负责 java -classpath 或者 -D java.class.path所指定目录下的类与jar包装入工作库，是最常用的加载器
 */