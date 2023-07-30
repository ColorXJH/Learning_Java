package com.master.chapter01;

/**
 * @ClassName: FindVarTest
 * @Package: com.master.chapter01
 * @Description:找出1000以内的所有完数（完数是他的因子之和，因子：出去这个数本身的其他约数：6=1+2+3)
 * @Datetime: 2023/7/30 20:36
 * @author: ColorXJH
 */
public class FindVarTest {
    public static void main(String[] args) {
        int factor=0;
        for (int i=2;i<1000;i++){
            //这里只需要判断取到当前数值的一半就可以了
            for (int j=1;j<=i/2;j++){
                if(i%j==0){
                    factor+=j;
                }
            }
            if(i==factor){
                System.out.println(i);
            }
            factor=0;
        }
    }
}
