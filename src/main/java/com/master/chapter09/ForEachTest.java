package com.master.chapter09;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: ForEachTest
 * @Package: com.master.chapter09
 * @Description: jdk5.0新增的增强for循环
 * @Datetime: 2023/9/5 19:41
 * @author: ColorXJH
 */
public class ForEachTest {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        //内部仍然调用的迭代器
        for (Integer i:integers) {
            System.out.println(i);
        }
        int[] ints=new int[]{12,123,23,45,56};
        for(int i=0;i<ints.length;i++){
            ints[i]=100;
        }
        //迭代器
        for(int s:ints){
            s=5;
        }
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }

    }
}
