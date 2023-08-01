package com.master.chapter01;

import java.util.Arrays;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2023-07-31 10:35
 */
public class ArrayTest {
    public static void main(String[] args) {
        //静态初始化 数组初始化+显式元素赋值操作
        int array[]={2,3,4,5,6};
        int []arrays=new int[]{3,4,7,8,9};
        System.out.println(array);
        System.out.println(arrays);
        //动态初始化 数组初始化  默认值为各个数据类型的默认值
        int []test=new int[5];
        //默认是unicode码0 而不是字符‘0’，或者说是字符编码0对应的字符，有空格的效果，但是也不是空格对应的ascii码值
        char[]test2=new char[5];
        String[] arr5=new String[5];
        if(test2[0]==0){
            System.out.println("char默认初始化值为0，所有能自动提升到int类型变量的默认值都是0，" +
                    "浮点数为0.0，布尔型是false（0）,引用数据类型为null(空值，不是“null”字符),同理，类中的属性的初始化值与这里相同");
        }
        System.out.println(Arrays.toString(arrays));
        System.out.println(Arrays.toString(test));
        System.out.println(Arrays.toString(test2));
        System.out.println(Arrays.toString(arr5));

    }
}
