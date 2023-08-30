package com.master.chapter07;

import java.util.Arrays;

/**
 * @ClassName: CompareTest
 * @Package: com.master.chapter07
 * @Description: java的排序比较
 * @Datetime: 2023/8/30 22:14
 * @author: ColorXJH
 */
public class CompareTest {
    public static void main(String[] args) {
        CompareTest test=new CompareTest();
        test.test1();
        Person[] people=new Person[4];
        people[0]=new Person(28,"Color");
        people[1]=new Person(27,"KCY");
        people[2]=new Person(26,"WXY");
        people[3]=new Person(25,"ZYF");
        Arrays.sort(people);
        System.out.println(Arrays.toString(people));

    }

    /**
     * Comparable接口使用案例
     * 像String 包装类实现了Comparable接口，重写了compareTo()方法，给出了比较两个对象大小的方式
     * compareTo:当前对象大于形参obj 返回正数 等于返回0 小于返回负数  即从小到大排序
     */
    public void test1(){
        String[]arr=new String[]{"AA","CC","KK","MM","GG","JJ","DD"};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}

class Person implements Comparable<Person>{
    public int age;
    public String name;
    public Person(int age,String name){
        this.age=age;
        this.name=name;
    }

    @Override
    public int compareTo(Person o) {
        return this.age-o.age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
/**
 * 如何使用Comparable与Comparator
 *
 *
 */