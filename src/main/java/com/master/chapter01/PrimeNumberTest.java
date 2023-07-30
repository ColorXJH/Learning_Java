package com.master.chapter01;

/**
 * @ClassName: PrimeNumberTest
 * @Package: com.master.chapter01
 * @Description: 1-100000以内质数算法优化  只能被自身和1整除的数字，最小的为2
 * @Datetime: 2023/7/30 15:38
 * @author: ColorXJH
 */
public class PrimeNumberTest {
    public static void main(String[] args) {
        boolean flag=true;
        int count=0;
        long start=System.currentTimeMillis();
        //遍历100000以内的自然数
        for(int i=2;i<100000;i++){
            //j被i去除
            //优化2：对本身是质数的自然数有效
            for(int j=2;j<=Math.sqrt(i);j++){
                //i被j除尽
                if(i%j==0){
                    flag=false;
                    //优化1：只对本身是非质数的自然数是有效的
                    break;
                }
            }
            if(flag){
                count++;
            }
            flag=true;
        }
        long end=System.currentTimeMillis();
        System.out.println("100000以内的质数个数为："+count);
        System.out.println("耗时为："+(end-start));

    }
    //没有优化：int j=2;j<i;j++
    //100000以内的质数个数为：9592
    //耗时为：11397

    //优化1： break;
    //100000以内的质数个数为：9592
    //耗时为：1014

    //优化2：int j=2;j<=Math.sqrt(i);j++
    //100000以内的质数个数为：9592
    //耗时为：9
}
