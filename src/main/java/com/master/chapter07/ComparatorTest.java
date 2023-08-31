package com.master.chapter07;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @ClassName: ComparatorTest
 * @Package: com.master.chapter07
 * @Description: 定制排序
 * @Datetime: 2023/8/30 23:11
 * @author: ColorXJH
 */
public class ComparatorTest {
    public static void main(String[] args) {
        Persons[] people=new Persons[4];
        people[0]=new Persons(28,"Color");
        people[1]=new Persons(27,"KCY");
        people[2]=new Persons(26,"WXY");
        people[3]=new Persons(25,"ZYF");
        Arrays.sort(people, new Comparator<Persons>() {
            //o1>o2 返回正数 o1=o2返回0， o1<o2返回负数 也是从小到大排序 ，不过这些都是可以修改的，比如调换顺序，添加负号（-）
            //默认都是从低到高排序
            @Override
            public int compare(Persons o1, Persons o2) {
                return o1.age- o2.age;
            }
        });
        System.out.println(Arrays.toString(people));
    }
}

class Persons{
    public int age;
    public String name;
    public Persons(int age,String name){
        this.age=age;
        this.name=name;
    }

    @Override
    public String toString() {
        return "Persons{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}