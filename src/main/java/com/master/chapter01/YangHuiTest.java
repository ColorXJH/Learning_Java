package com.master.chapter01;

import java.util.Arrays;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 使用二维数组打印一个杨辉三角   yanghui[i][j]=yanghui[i-1][j-1]+yanghui[i-1][j]
 * 第一行一个元素，第n行n个元素，每一行的首尾都是1，从第三行开始 ，对于非第一个元素和最后一个元素：有如上规律
 * @date 2023-08-01 10:23
 */
public class YangHuiTest {
    public static void main(String[] args) {
        //初始化二维数组
        int[][] yanghui=new int[10][];
        //给数组元素赋值
        for (int i=0;i<yanghui.length;i++){
            yanghui[i]=new int[i+1];
            //给首尾元素赋值
            yanghui[i][0]=1;
            yanghui[i][i]=1;
            //给每行的非首尾元素赋值,这里去掉if也可以，因为前两行也进不去判断
            if(i>1){
                for(int j=1;j<yanghui[i].length-1;j++){
                    yanghui[i][j]=yanghui[i-1][j-1]+yanghui[i-1][j];
                }
            }
        }
        //遍历数据
        for (int i=0;i< yanghui.length;i++){
            for (int j=0;j<yanghui[i].length;j++){
                System.out.print(yanghui[i][j]+" ");
            }
            System.out.println();
        }

    }

}
