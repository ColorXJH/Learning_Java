package com.master.chapter01;

import java.util.Arrays;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:数组的复制，反转，查找
 * @date 2023-08-01 12:38
 */
public class ArrayTest2 {
    public static void main(String[] args) {
        copy();
        reverse();
        query();
    }

    public static void copy(){
        String[] arr=new String[]{"JJ","XX","GG","BB","AA","TT"};
        String[] newArr=new String[arr.length];
        for(int i=0;i< newArr.length;i++){
            newArr[i]=arr[i];
        }
    }
    public static void reverse(){
        String[] arr=new String[]{"JJ","XX","GG","BB","AA","TT"};
        String temp;
        //方法1
//        for(int i=0;i<arr.length/2;i++){
//            temp=arr[i];
//            arr[i]=arr[arr.length-i-1];
//            arr[arr.length-i-1]=temp;
//            temp=null;
//        }
        //方法2
        for(int i=0,j= arr.length-1;i<j;i++,j--){
             temp=arr[i];
             arr[i]=arr[j];
             arr[j]=temp;
             temp=null;
        }
        System.out.println(Arrays.toString(arr));
    }
    public static void query(){
        String[] arr=new String[]{"JJ","XX","GG","BB","AA","TT"};
        //线性查找
        String dest="BB";
        boolean flag=false;
        for(int i=0;i< arr.length;i++){
            if(dest.equals(arr[i])){
                System.out.println("找到了指定的元素"+dest+"，位置为："+i);
                flag=true;
                break;
            }
        }
        if(!flag){
            System.out.println("没有找到");
        }
        //二分法查找,（前提是需要排过序的）
        int[] arr2=new int[]{-98,-88,-34,0,12,14,15,45,67,97,133,223,299};
        int dest2=-34;
        int head=0;
        int end=arr2.length-1;
        boolean flag2=false;
        while (head<=end){
            int middle=(head+end)/2;
            if(dest2==arr2[middle]){
                System.out.println("找到了指定元素"+dest2+" 索引位置为："+middle);
                flag2=true;
                break;
            }else if(arr2[middle]>dest2){
                end=middle-1;
            }else{
                head=middle+1;
            }

        }
        if(!flag2){
            System.out.println("没有找到");
        }
    };
}
