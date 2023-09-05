package com.master.chapter09;

import java.util.*;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 集合的15个基本方法
 * @date 2023-09-05 9:18
 */
public class CollectionTest {
    public static void main(String[] args) {
        Collection collection=new ArrayList();
        collection.add("aa");
        collection.add(123);
        collection.add(new Date());
        System.out.println(collection.size());
        Collection collection1=new ArrayList();
        collection1.add("345");
        collection1.add("567");
        collection.addAll(collection1);
        System.out.println(collection.size());
        System.out.println(collection.isEmpty());
        collection.clear();
        System.out.println(collection.size());
        boolean contains = collection.contains("345"); //会调用equals方法 注意重写equals方法、
        boolean b = collection.containsAll(collection1);
        collection.remove("123");
        collection.removeAll(collection1);//移除共有的元素
        boolean b1 = collection.retainAll(collection1);//获取两个集合的交集
        collection.equals(collection1);//注意有序集合的顺序 打乱了顺序也会返回false
        System.out.println(collection.hashCode());
        Object[] objects = collection.toArray();//集合到数组的转化
        List<Object> objects1 = Arrays.asList(objects);//数组到集合的转化
        List<int[]> ints = Arrays.asList(new int[]{123, 346});//注意这里作为一个参数，打印会显示一个地址值
        //如果像下面传递就不会出现问题
        List<Integer> integers = Arrays.asList(123, 345);
        //或者使用包装类
        List<Integer> integers1 = Arrays.asList(new Integer[]{123, 456});
        //返回Iterator接口的实例，用于遍历集合元素
        //集合都继承Iterable接口，该接口中方法返回迭代器对象，more游标在第一个元素之前
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()){
            Object next = iterator.next();
        }


    }
}

/**
 * 集合框架
 *      collection:单列集合
 *          list 有序 --动态数组
 *              ArrayList LinkedList Vector
 *          set 无序 --唯一集合
 *              HashSet LinkedHashSet TreeSet
 *      map:双列集合
 *          HashMap LinkedHashMap TreeMap Hashtable Properties
 *          y=f(x)
 * Collection接口中发方法
 *
 */