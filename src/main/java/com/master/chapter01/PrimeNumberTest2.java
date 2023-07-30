package com.master.chapter01;

/**
 * @ClassName: PrimeNumberTest2
 * @Package: com.master.chapter01
 * @Description:
 * @Datetime: 2023/7/30 20:02
 * @author: ColorXJH
 */
public class PrimeNumberTest2 {
    public static void main(String[] args) {
        boolean flag=true;
        int count=0;
        long start=System.currentTimeMillis();
        //遍历100000以内的自然数
        label:for(int i=2;i<100000;i++){
            //j被i去除
            //优化2：对本身是质数的自然数有效
            for(int j=2;j<=Math.sqrt(i);j++){
                //i被j除尽
                if(i%j==0){
                    continue label;
                }
            }
            //能执行到此步骤的都是质数
            count++;
        }
        long end=System.currentTimeMillis();
        System.out.println("100000以内的质数个数为："+count);
        System.out.println("耗时为："+(end-start));

    }
}
