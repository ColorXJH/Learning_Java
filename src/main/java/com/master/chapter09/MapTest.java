package com.master.chapter09;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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
        MapTest mapTest=new MapTest();
        mapTest.test1();
    }
    public void test1(){
        Properties properties=new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("name.text");
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8")
        ) {
            properties.load(inputStreamReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(properties.getProperty("name"));
        System.out.println(properties.getProperty("age"));
        System.out.println(properties.getProperty("addr"));
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

/**
 * HashMap的底层实现原理
 *  jdk7:HashMap map=new HashMap();在实例化以后，底层创建了一个长度为16的一维数组Entry[] table
 *      map.put(key1,value1)
 *          首先调用key1所在类的hashcode()计算key1的hash值，此哈希值经过某种算法计算以后，得到在Entry[]数组中的存放位置
 *          如果此位置上数据为空,此时key1-value1添加成功（Entry添加成功）--情况1
 *          如果此位置上数据不为空（存在一个或多个数据（以链表的数据存在）），比较key1和已经存在的一个或多个数据的哈希值
 *              如果key1的哈希值和已经存在的数据的哈希值都不相同，此时key1-value1添加成功 --情况2
 *              如果key1的哈希值和某一个数据的哈希值相同，则比较equals,调用key1所在类的equals方法
 *                  如果equals返回false,则测试的key1-value1添加成功 --情况3
 *                  如果equals返回true,使用value1去替换相同key的value值
 *      关于情况2和情况3：此时key1-value1和原来的数据以链表的形式存储 7上8下；（7中存储在最上方，数组那里，8则将数据放在链表尾部，下方）
 *          7中数据以数组+链表的形式存储
 *      关于扩容：默认的扩容方式，扩容为原来的2倍，并将原有的数据复制过来（jdk7）
 *  jdk8:
 *      相较于jdk7在底层实现原理方面的不同：
 *          1: new HashMap();
 *              底层没有创建长度为16的数组（jdk8底层的数组由Entry[]变成了Node[]）
 *          2: put(key1,value1):
 *              首次调用put方法时，底层创建长度为16的数组
 *          3: 原来jdk7底层数据结构是数组+链表，jdk8中是数组+链表+红黑树
 *              当数组的某个索引位置上的元素以链表形式存在的个数大于8且当前数组长度大于64，此索引位置上的所有数据改用红黑树存储（方便相同hash值数据的查找）
 */

/**
 * LinkedHashMap底层实现原理:类似HashMap,只是多了两组引用，指向元素的前后
 * 按照添加的顺序来遍历集合 put方法基本上是继承了HashMap的put方法，只是重写了里面的newNode方法
 *  里面创建了一个Entry<K,V>，继承了HashMap的Node,在entry中添加了两个引用属性，before,after
 *
 */

/**
 * map中常见的方法
 * map.get(key)
 * map.containsKey(key)
 * map.containsValue(value)
 * mao.size()
 * map.isEmpty()
 * map.equals(obj)
 * map.keySet()
 * map.values()
 * map.entrySet()
 * map.put(key,value)
 * map.putAll(map)
 * map.remove(key)
 * map.clear()
 *
 */

/**
 * TreeMap
 *  按照key进行排序，要求key必须是由同一个类创建的对象
 *
 */

/**
 * Properties:Hashtable的子类，用于操作属性问价
 * 他的key/value都是字符串类型
 * setProperty(String name,String value)
 * getProperty(String key)
 */