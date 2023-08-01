package com.master.chapter01;

import java.util.Arrays;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 随即生成一个数组，要求数字不重复
 * @date 2023-08-01 11:12
 */
public class RandomUniqueArrayTest {
    public static void main(String[] args) {
        test1();
        test2();
    }
    public static void test2(){
        int[] array=new int [6];
        for (int i=0;i<array.length;i++){
            //Math.random()==>[0,1)=>[1,31)
            array[i]=(int)(Math.random()*30)+1;
            for(int j=0;j<i;j++){
                if(array[i]==array[j]){
                    i--;
                    break;
                }
            }
        }
        for (int i=0;i<array.length;i++){
            System.out.println(array[i]);
        }
    }

    public static void  test1(){
        int[] array=new int[6];
        for (int i=0;i<array.length;i++){
            array[i]=(int)(Math.random()+30)+1;
            boolean flag=false;
            while (true){
                for (int j=0;j<i;j++){
                    if(array[i]==array[j]){
                        flag=true;
                        break;
                    }
                }
                if(flag){
                    array[i]=(int)(Math.random()*30)+1;
                    flag=false;
                    continue;
                }
                break;
            }
        }
        System.out.println(Arrays.toString(array));
    }
}
