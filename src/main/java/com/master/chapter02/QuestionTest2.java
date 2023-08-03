package com.master.chapter02;

import java.util.Arrays;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2023-08-03 14:18
 */
public class QuestionTest2 {
    public static void main(String[] args) {
        //定义一个int型数组，int[] arr=new int[]{12,3,3,34,56,77,432}
        //让数组每个位置上的值去除以首位置的元素，得到的结果作为新值，存在该位置上，然后遍历数组
        int[] arr=new int[]{12,3,3,34,56,77,432};
        //倒着来
        for(int i=arr.length-1;i>=0;i--){
            arr[i]=arr[i]/arr[0];
        }
        System.out.println(Arrays.toString(arr));
        //或者
        int[] arr2=new int[]{12,3,3,34,56,77,432};
        int temp=arr2[0];
        for(int i=0;i<arr2.length;i++){
            arr2[i]=arr2[i]/temp;
        }
        System.out.println(Arrays.toString(arr2));
        int[] arr3=new int[]{1,2,3};
        //地址值
        System.out.println(arr3);
        char[]ch=new char[]{'a','b','c'};
        //abc 底层调用方法不一样，println是重载的方法，调用char[]时会遍历数组
        System.out.println(ch);

    }
}
