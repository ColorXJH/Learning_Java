package com.master.chapter06;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2023-08-25 14:05
 */
/**
 * 那变量的定义就是根据变量的类型，找到内存中的某个地址，看其上面的值了
 * 如果是基本数据类型变量，其上面的值就是我们想要的真实内容，
 * 如果是引用数据类型变量，那么它上面的值就是另一块内存的地址值，
 */
public class StringTestArgs {
    String str=new String("good");
    char[]ch={'t','e','s','t'};
    public void change(String str,char[]ch){
        //string是不可变型 引用地址值再次指向其他内容，所以就从新分配了个地址值去存储这个String
        System.out.println("str inner reference change before:"+str.hashCode());
        str="test ok";
        ch[0]='b';
        System.out.println("str  inner method args hashcode:"+str.hashCode());
        System.out.println("ch  inner method args hashcode:"+ch.hashCode());

        ch=new char[10];
        System.out.println("reference changed hashcode:"+ch.hashCode());
    }
    public static void main(String[] args) {
        StringTestArgs testArgs=new StringTestArgs();
        System.out.println("--before--");
        System.out.println("str hashcode:"+testArgs.str.hashCode());
        System.out.println("ch hashcode:"+testArgs.ch.hashCode());
        /**
         * 对于方法的值传递机制：会传递值得副本，无论是基本数据类型还是引用数据类型
         * 当你在方法内部对其他引用类型的变量进行修改时（创建一个新的对象重新赋值），原始变量仍然引用原来的对象。
         * 只是如果你不修改副本引用地址，你是可以操作原始数据而已，如果你修改了副本地址，那么也不会影响原始数据得指向
         * 只是在内存中重新分配了一块地址给副本重新指向了而已
         */
        testArgs.change(testArgs.str,testArgs.ch);

        System.out.println("--after--");
        System.out.println("str hashcode:"+testArgs.str.hashCode());
        System.out.println("ch hashcode:"+testArgs.ch.hashCode());

        System.out.println(testArgs.str);//good
        System.out.println(testArgs.ch);//best
    }
}
/**
 * 在Java中，基本数据类型的变量和引用数据类型的变量存储的方式是不同的。
 * 基本数据类型的变量（如int、boolean、char等）直接存储其值在栈空间中。当你声明一个基本数据类型的变量并赋予一个值时，该值直接存储在变量所分配的栈内存中。这意味着基本数据类型的变量存储的是实际的值。
 * 引用数据类型的变量（如对象、数组等）存储的是对象的引用或地址值。当你声明一个引用数据类型的变量时，实际上在栈空间中分配了一个引用变量，并将其初始化为null或者指向某个对象。对象本身存储在堆空间中。引用变量存储的是对象在堆空间中的地址，通过引用变量可以访问和操作对象的成员。
 * 需要注意的是，无论是基本数据类型的变量还是引用数据类型的变量，它们的值都存储在栈空间中。对于基本数据类型，存储的是实际的值；对于引用数据类型，存储的是对象的引用或地址值。
 * 另外，Java还有一块内存区域称为方法区（Method Area），用于存储类的信息、静态变量、常量池等。但是，方法区不存储变量的实际值，而是存储变量的定义和描述信息。
 * 总结起来，Java的基本数据类型的变量和引用数据类型的变量都存储在栈空间中，但存储的内容有所不同。基本数据类型的变量存储实际的值，而引用数据类型的变量存储对象的引用或地址值。对象本身存储在堆空间中。
 *
 * 对于int x=5 这里的x就是变量名，他本是是一个地址值，这个地址上存储的是5这个值
 * 对于Person p=new Person p也是一个变量名，它本身是一个栈空间的内存地址值，其地址上存储的是一个引用，这个引用是堆空间的地址值
 * 真实的对象存储在堆空间上，在上面开辟了一块内存，存储了对象的信息，这块内存的首地址值就是这个引用，被存储在变量p上
 * 所以说，一个变量其既有内存地址值，也有存储数据值，只不过基本数据类习惯的存储数据值就是数据，而引用数据类型存储的数据是另一块内存的地址值
 */