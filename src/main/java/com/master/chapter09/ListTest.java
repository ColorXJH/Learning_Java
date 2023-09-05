package com.master.chapter09;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: ListTest
 * @Package: com.master.chapter09
 * @Description: Collection的子接口 List,常见实现类ArrayList LinkedList Vector
 * @Datetime: 2023/9/5 20:01
 * @author: ColorXJH
 */
public class ListTest {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3);
        //删除索引
        integers.remove(2);
        //删除对象
        integers.remove(new Integer(3));

    }
}

/**
 * 三者异同
 * 同：三个类都实现了List接口，存储数据特点相同
 * 不同： ArrayList:作为list接口的主要实现类，线程不安全的 效率高，底层使用Object[] elementData数组
 *      LinkedList:底层使用的是双向链表，对于频繁的插入删除操作使用此类效率比上方高
 *      Vector:作为list的古老实现类,线程安全的 效率低 底层使用Object[] elementData数组
 * 查看他们的源码分析
 */