package com.master.chapter14;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: java运行时类丰富结构的获取
 * @date 2023-10-12 9:16
 */
public class ReflectionTest {
    @Test
    public void testField(){
        Class clazz=Color.class;
        //只获取当前运行时类及其所有父类/接口当中 声明为public的属性
        Field[] fields = clazz.getFields();
        for (Field field:fields) {
            System.out.println(field);
        }
        //
        System.out.println("------------------");
        //获取当前运行时类自己的所有属性，不考虑权限，不包含父类中声明的属性
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field:declaredFields) {
            System.out.println(field);
        }
        System.out.println("--------------------");
        //获取属性的权限修饰符、数据类型、变量名
        Field[] declaredFields1 = clazz.getDeclaredFields();
        for (Field field:declaredFields1) {
            //获取权修饰符
            int modifiers = field.getModifiers();
                //Modifier类中定义了权限修饰符的常量大小
            System.out.println("获取权限修饰符常量："+modifiers);
                //翻译回来
            System.out.println("获取权限修饰符："+Modifier.toString(modifiers));
            //变量的数据类型
            Class<?> type = field.getType();
            System.out.println("获取数据类型："+type.getName());
            //变量名
            String name = field.getName();
            System.out.println("属性名称："+name);

        }

    }
    @Test
    public void testMethod(){
        Class clazz=Color.class;
        System.out.println("----获取该类以及父类中的所有public方法---");
        //获取当前运行时类及其父类/接口中所有的public修饰符的方法
        Method[] methods = clazz.getMethods();
        for(Method method:methods){
            System.out.println(method);
        }
        System.out.println("-----------测试本类中的所有方法-------------");
        //获取当前运行时类中的所有方法，不考虑权限，不包含父类的方法，
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for(Method method:declaredMethods){
            System.out.println(method);
        }
    }

    //获取运行时类的父类

    @Test
    public void testRuntimeClasSuperClass(){
        Class clazz=Color.class;
        //获取运行时类的父类
        Class superclass = clazz.getSuperclass();
        System.out.println(superclass);
        System.out.println("---------------");
        //带范型的父类
        Type genericSuperclass = clazz.getGenericSuperclass();
        System.out.println(genericSuperclass);
        //获取带范型的父类的范型
        System.out.println("---------------");
        Type genericSuperclass1 = clazz.getGenericSuperclass();
        //转换为 带参数的类型
        ParameterizedType parameterizedType=(ParameterizedType) genericSuperclass1;
        //获取范型参数类型
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        for(Type tp:actualTypeArguments){
            System.out.println(tp.getTypeName());
            System.out.println(((Class)tp).getName());
        }
    }


    //注解 @xxx -想要获取到注解 ，注解的类型必须是runtime及以后
    //权限修饰符 返回值类型 方法名（参数1，参数2.。。）throws xxxException{}

    @Test
    public void testMethodStructure(){
        Class clazz=Color.class;
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for(Method method:declaredMethods){
            System.out.println(method);
            //获取方法声明的注解
            Annotation[] annotations = method.getAnnotations();
            for(Annotation an:annotations){
                System.out.println(an);
            }
            //获取权限修饰符
            System.out.println(Modifier.toString(method.getModifiers()));
            //获取返回值类型
            System.out.println(method.getReturnType().getName());
            //获取方法名
            System.out.println(method.getName());
            //获取形参列表
            Class<?>[] parameterTypes = method.getParameterTypes();
            if(!(parameterTypes==null&&parameterTypes.length==0)){
                for(Class p:parameterTypes){
                    System.out.print(p+"--"+p.getName() +"\t");
                }

            }
            //获取方法抛出的异常
            Class<?>[] exceptionTypes = method.getExceptionTypes();
            for(Class e:exceptionTypes){
                System.out.print("异常类型："+e.getName()+"\t");
            }
        }

    }


    @Test
    public void testAccessConstructor(){
        Class clazz=Color.class;
        Constructor[] constructors = clazz.getConstructors();
        for(Constructor cons:constructors){
            System.out.println(cons);
        }
        System.out.println("------------------");
        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
        for(Constructor cons:declaredConstructors){
            System.out.println(cons);
            Annotation[] annotations = cons.getAnnotations();
            for(Annotation an:annotations){
                System.out.println(an);
            }
        }
    }

    //获取运行时类的接口
    @Test
    public void testRuntimeInterface(){
        Class<Color> colorClass = Color.class;
        //获取该类实现的接口
        Class<?>[] interfaces = colorClass.getInterfaces();
        for(Class s:interfaces){
            System.out.println(s);
        }
        //获取运行时类的父类的接口
        System.out.println("------------------------- ");
        Class<?>[] interfaces1 = colorClass.getSuperclass().getInterfaces();
        for(Class s:interfaces1){
            System.out.println(s);
        }
    }


    //获取运行时类所在的包
    @Test
    public  void testRuntimeClassPackage(){
        Class clazz=Color.class;
        Package aPackage = clazz.getPackage();
        System.out.println(aPackage);
    }


    //获取运行时类所声明的注解
    @Test
    public  void testRuntimeClassAnnotation(){
        Class clazz=Color.class;
        Annotation[] annotations = clazz.getAnnotations();
        for(Annotation s:annotations){
            System.out.println(s);
        }
    }

    //调用运行时类指定的结构：属性、方法、构造器
    @Test
    public void testInvokeRunTimeClassFieldMethodConstructor() throws NoSuchFieldException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class clazz=Color.class;
        //获取指定属性
        Field name = clazz.getDeclaredField("name");
        Field id = clazz.getField("id");
        Field weight=clazz.getDeclaredField("weight");
        weight.setAccessible(true);

        //创建运行时类的对象
        Object o = clazz.newInstance();
        id.set(o,1001);
        Object o1 = id.get(o);
        //静态属性前面可以不设置，显示书写null
        weight.set(null,1222);
        System.out.println(o1);

        name.setAccessible(true);
        name.set(o,"Color");
        System.out.println(o);
        //获取指定类型的方法
        Method method=clazz.getDeclaredMethod("display",String.class);
        method.invoke(o,"ColorXJH");
        //获取静态方法
        System.out.println("----获取静态方法----");
        Method method1=clazz.getDeclaredMethod("showStatic",String.class);
        method1.setAccessible(true);
        //null 或者Color.class都可以
        Object static_method = method1.invoke(null, "static_method");
        System.out.println(static_method);

        //获取类中指定的构造器
        System.out.println("---------调用类的指定的构造器---------");
        Constructor declaredConstructor = clazz.getDeclaredConstructor(String.class);
        //设置访问权限
        declaredConstructor.setAccessible(true);
        //调用此构造器创建运行时类的对象
        Object my_name_is_lihua = declaredConstructor.newInstance("my name is lihua");
        //输出该对象
        System.out.println(my_name_is_lihua);
    }
}

//        为什么这里会有两个compareTo方法?
//        这是因为在Java中，如果一个类实现了Comparable<T>接口，并重写了compareTo方法，那么它实际上会有两个compareTo方法。
//        compareTo(Object obj)方法：这个方法是从Comparable接口继承而来的，它接受一个Object类型的参数。当你使用反射获取类的全部方法时，会包含这个方法。
//        compareTo(T obj)方法：这个方法是你在类中重写的compareTo方法，它接受一个泛型类型的参数。这个方法是你实际使用的比较方法。
//        这两个方法的存在是为了保持向后兼容性。在Java早期的版本中，Comparable接口只有一个compareTo(Object obj)方法。
//        后来引入了泛型，为了让已经实现了Comparable接口的类能够继续使用，就保留了这个方法，并在实现类中添加了一个新的泛型版本的compareTo方法。
//        所以，当你使用反射获取类的全部方法时，会得到这两个compareTo方法。但在实际使用中，你应该使用泛型版本的compareTo方法。

//注解+反射+设计模式=框架