package com.master.chapter09;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:Collections工具类
 * @date 2023-09-07 15:53
 */
public class CollectionsTest {
    public static void main(String[] args) {
        Collections.sort(Arrays.asList(1,3,5,7,3,8));
    }
}


/**
 * Collections是操作Collection Map的工具类
 * reverse(list)
 * shuffle(list)
 * sort(list)
 * sort(list,comparator)
 * swap(list,int,int)
 * max(Collection)
 * max(Collection,comparator)
 * min(Collection)
 * min(collection,comparator)
 * frequency(Collection,obj)
 * copy(list desc,list src)
 * replaceAll(list,oldVal,newVal)
 * 多个SynchronizedXX方法，返回线程安全的集合
 */

/**
 * Enumeration接口时Iterator迭代器的古老版本
 * // 创建一个Vector集合
 *         Vector<String> vector = new Vector<>();
 *         vector.add("Apple");
 *         vector.add("Banana");
 *         vector.add("Orange");
 * // 获取Vector集合的枚举器
 *         Enumeration<String> enumeration = vector.elements();
 *
 *         // 使用枚举器遍历集合元素
 *         while (enumeration.hasMoreElements()) {
 *             String element = enumeration.nextElement();
 *             System.out.println(element);
 *         }
 */