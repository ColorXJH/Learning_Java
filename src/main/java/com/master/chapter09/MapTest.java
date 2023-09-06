package com.master.chapter09;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: MapTest
 * @Package: com.master.chapter09
 * @Description: 映射的Map接口，平行与集合Collection接口
 * @Datetime: 2023/9/6 20:30
 * @author: ColorXJH
 */
public class MapTest {
    public static void main(String[] args) {
        Map map=new HashMap<>();
    }
}

/**
 * Map:双列数据，存储KEY-VALUE对的数据，类似于高中的y=f(x)
 *      HashMap 实现类 作为Map的主要实现类 线程不安全 效率高 可以存储null的key和value  底层实现HashMap：1.7数组+链表 1.8数组+链表+红黑树
 *          LinkedHashMap 实现类  解决遍历操作效率偏差的HashMap实现类 保证在遍历Map元素时按照添加的顺序遍历，在原有的HashMap底层结构上添加了一对引用，只想前一个和后一个映射
 *      SortedMap 接口
 *          TreeMap 实现类  有序的键值对存储，按照key的自然排序以及比较器排序 底层使用红黑树排序
 *      Hashtable 实现类 作为Map的古老实现类 线程安全 效率低 不能存储null的key和value（类似于Vector都是1.0就出席拿了作为原始的存储方式，在1.2的时候出现了体系，再将它们划分进来的，前朝元老进入当朝体系，不经常被使用）
 *          properties 实现类 常用来处理配置文件，key和value都是String类型
 * Map接口主要有5个实现类,后面即使考虑线程安全List/Map 也不会去使用Vector/Hashtable 而是使用Collections工具类返回线程安全的集合/映射
 *
 * HashMap的底层实现原理？
 * HashMap和Hashtable的异同？
 * ConcurrentHashMap与Hashtable的异同？
 */

/**
 * Map接口
 *  key:无序 不可重复：Set  --》自定义的类 HashMap需要重写equals和hashcode方法TreeMap重写比较器方法  xxx.containsKey()
 *  value:无序 可重复 Collection  自定义的类需要重写equals方法 xxx.containsValue(),为什么不重写hashcode:因为根据key找到的value,或者说是封装在entry当中
 *  put(k,v)内部转换为put(entry) entry是不可重复的无序的，他有k,v,其实内部还是操作一个个Entry
 *
 */