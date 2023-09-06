package com.master.chapter09;

import java.util.*;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 集合Set接口及其实现
 * @date 2023-09-06 9:05
 */
public class SetTest {
    public static void main(String[] args) {
        SetTest setTest=new SetTest();
        //setTest.test1();
        //setTest.test2();
        //setTest.test3();
        setTest.test4();

    }
    public void test1(){
        Set set=new HashSet();
        set.add(345);
        set.add(123);
        set.add("bb");
        set.add("aa");
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            Object next = iterator.next();
            System.out.println(next);
        }

    }
    //不同对象的hashcode一样时，属性不同导致存放在一个链表中
    public void test2(){
        Person person=new Person("Color",28);
        Person2 person2=new Person2("Color",26);
        Set set=new HashSet();
        set.add(person);
        set.add(person2);
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }

    public void test3(){
        Set set=new TreeSet();
        set.add("CDF");
        set.add("BCD");
        set.add("ASE");
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
    //很经典的一道题 set先按照hashcode添加，移除的时候重新计算hashcode发现已经更改了，没移除掉，再新增的时候属于新的hashcode点位，再次新增是原来的点位但是内容不一致，所以四个都能加上
    //JDK 1.7中的HashSet使用数组+链表的数据结构，而JDK 1.8中的HashSet使用数组+链表/红黑树的数据结构。
    //具体来说，JDK 1.8中的HashSet内部使用了HashMap来实现。
    //在JDK 1.8之前，HashSet使用一个Entry数组来存储元素，每个Entry是一个链表节点，用于解决哈希冲突。当链表长度过长时，会导致查找效率下降。
    //而在JDK 1.8中，当链表长度超过一定阈值（默认为8）时，链表会转换为红黑树，以提高查找效率。这样，在哈希冲突较多的情况下，HashSet的性能得到了显著的提升
    public void test4(){
        Set set=new HashSet();
        Person4 p1=new Person4(1001,"AA");
        Person4 p2=new Person4(1002,"BB");
        set.add(p1);
        set.add(p2);
        System.out.println(set);
        p1.name="cc";
        set.remove(p1);
        System.out.println(set);
        set.add(new Person4(1001,"cc"));
        System.out.println(set);
        set.add(new Person4(1001,"AA"));
        System.out.println(set);
    }
}
/**
 * HashSet:Set接口的经典实现
 * 不保证元素的有序性
 * 非线程安全的
 * 集合元素可以是null
 * 对象必须重写hashcode()和equals()方法
 * 比较方法按照equals(),同时重写hashcode()方法
 * 底层原码，类似HashMap 底层是map结构，map结构的底层是数组外加链表
 */

/**
 * LinkedHashSet:在HashSet的基础之上（其子类），使得遍历其内部数据时，按照添加的顺序遍历
 * 添加的时候也是无序的，只是便利的时候有一个指针 指向第一个添加的位置，添加的时候也是计算hashcode散列码随机存放在数组上的
 * 在添加数据的同时，每个数据还维护了两个引用，记录此数据的前一个数据和后一个数据
 */

/**
 * TreeSet:按照添加对象的指定属性排序
 * 添加数据时，要求是同一个类的对象
 *  自然排序：比较标准按照compareTo方法比较this.a-o.a 比较为0的就不能加入，即不能放相同的数据
 *  定制排序：比较器的compare方法 o1-o2
 * 底层原码 类似于TreeMap 底层是红黑树的存储结构：
 */


/**
 * Set接口中没有定义新的方法，使用的都是Collection接口中声明过的方法
 * 无序性：不等于随机性，他们便利也是按照固定的顺序遍历，存储的数据在底层的数组中并非按照数组的索引顺序进行添加
 *      而是按照hashcode()方法的值来添加
 * 不可重复性
 * 添加元素的过程，以HashSet为例：
 *  向hashset中添加元素a,首先调用本身类的hashcode()计算哈希值
 *  通过某种算法计算出这个hash值在底层数组中的存放位置，即为索引
 *  判断数组此位置上是否已经有元素，如果没有直接存放，如果有，判断链表上各个元素hash值是否相同，不通则直接存放链表尾部，若相同则比较equals方法
 *  如果equals都不相同，则存放到链表尾部，否则添加失败
 *      链表位置jdk7 新元素在链表头 jdk8在链表末尾  七上八下
 */


class Person{
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return 333;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class Person2{
    String name;
    int age;

    public Person2(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return 333;
    }

    @Override
    public String toString() {
        return "Person2{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

/**
 * HashSet  LinkedHashSet  TreeSet 底层源码是下方的底层源码
 * HashMap  LinkedHashMap  TreeMap
 */

/**
 * 集合在java8中增强了 Collection接口中添加了默认方法forEach,接受一个Cosumer接口，使用lambda书写
 *
 */

class Person4{
    int id;
    String name;
    public Person4(int id,String name){
        this.id=id;
        this.name=name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person4 person4 = (Person4) o;
        return id == person4.id && Objects.equals(name, person4.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Person4{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}