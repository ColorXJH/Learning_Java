package com.master.chapter01;

import java.util.Arrays;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 排序算法
 * @date 2023-08-01 14:48
 */
public class SortTest {
    public static void main(String[] args) {
        //高效率-低存储  时间复杂度-空间复杂度
        //稳定性： AB顺序排序算法后保持顺序不变
        //十大内部排序算法：
            //选择排序：直接选择排序，堆排序
            //交换排序：冒泡排序，快速排序
            //插入排序：直接插入排序，折半插入排序，shell排序
            //归并排序
            //桶式排序
            //基数排序
        //算法的5大特性:输入，输出，有穷性，确定性，可行性
        //bubbleSort();
        int[]arr=new int[]{12,34,0,-90,223,78,23,-99,22,22};
        quickSort(arr,0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    //冒泡排序,通过对待排序列从前往后，依次比较两个相邻元素的大小，若发现逆序则交换顺序，使排序较大的逐渐靠前
    //在排序过程中，如果发现一趟排序下来，没有交换顺序，就说明序列有序，可以设置标志，以减少比较次数

    public static void bubbleSort(){
        int[]arr=new int[]{12,34,0,-90,223,78,23,-99,22,22};
        for(int i=0;i< arr.length-1;i++){
            for(int j=0;j< arr.length-1-i;j++){
                if(arr[j]>arr[j+1]){
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
    }
    //快速排序,从数列中挑选一个元素，称为“基准”，重新排序数列，所有比基准小的放在前面，比基准大的放在后面，这个操作完成后，基准就处于数列的中间位置
    //递归的将小于基准的元素的子数列和大于基准的子数列排序
    //递归的最底部情形：是数列的大小为0或者1，就是已经被排好了，虽然一直递归下去，但是这个算法告诉我们每次迭代，都会将一个数排序到他最后的位置

    /**
     * Description: 快速排序算法
     * @Author: ColorXJH
     * @Date: 2023-08-01 16:09
     * @param data 数据
     * @param start 其实位置缩影
     * @param end 终止位置索引
     * @Return: void
     **/
    public static void quickSort(int[]data,int start,int end){
        if(start<end){
            //选一个基准
            int base=data[start];
            //低位为起始位置
            int low=start;
            //高位为索引位置+1,为了配合后面的:data[--high]取到当前最后一个位置数据，改成以下就好了
            //int high=end+1;
            int high=end;
            while(true){
                //低位遇到大于基准的数才停下
                //while(low<end&&data[--low]-base<=0){}
                while(low<end&&data[low]-base<=0){
                    low++;
                }
                //高位遇到小于基准的才停下
                //while(high>start&&data[--high]-base>=0){}
                while(high>start&&data[high]-base>=0){
                    high--;
                }
                //如果低位没有超过高位，则交换位置
                if(low<high){
                    swap(data,low,high);
                }else{
                    //如果已经超过则什么也不做，退出本次比较
                    //100 1 2 3 4 5 6 7 8 9 10
                    break;
                }
            }
            //退出循环之后，需要交换基准和高位索引位置的值，因为出来循环之后表示高位的值已经没有基准大了
            swap(data,start,high);
            //递归调用

            quickSort(data,start,high-1);
            quickSort(data,high+1,end);
        }



    }

    //    交换位置

    private static void swap(int[]data,int i,int j){
        int temp=data[i];
        data[i]=data[j];
        data[j]=temp;
    }
}
