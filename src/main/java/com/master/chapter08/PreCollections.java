package com.master.chapter08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * @ClassName: PreCollections
 * @Package: com.master.chapter08
 * @Description: 集合框架预先看
 * @Datetime: 2023/9/4 22:11
 * @author: ColorXJH
 */
public class PreCollections {
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