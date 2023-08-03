package com.master.chapter02;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:递归调用
 * @date 2023-08-03 14:52
 */
public class RecursionTest {
    public static void main(String[] args) {
        //递归：一个方法体内调用自身
        //递归需要向已知方向递归，需要有临界点，递归方向控制
            //计算1-100所有自然数的和
        System.out.println(calculate(100));
        System.out.println(xxx(10));
        System.out.println(febs(10));

        //汉诺塔问题
        int n=3;//盘子的数量
        char a='A';//A柱
        char b='B';//B柱
        char c='C';//C柱
        hanoi(n, a, b, c);

    }
    /**
     * Description: 递归求和
     * @Author: ColorXJH
     * @Date: 2023-08-03 15:00
     * @param num
     * @Return: int
     **/
    public static int calculate(int num){
        if(num==1){
            return 1;
        }else{
            return calculate(num-1)+num;
        }
    }
    //f(0)=1,f(1)=4 f(n+2)=2*f(n+1)+f(n)

    public static int xxx(int num){
        if(num==0){
            return 1;
        }else if(num==1){
            return 4;
        }
        else{
            return 2*xxx(num-1)+xxx(num-2);
        }
    }
    //斐波那契额数列
    public static int febs(int num){
        if(num==1){
            return 1;
        }else if(num==2){
            return 1;
        }else{
            return febs(num-1)+febs(num-2);
        }
    }
    //汉诺塔问题

    public static void hanoi(int n,char a,char b,char c){
        if (n==1){
            System.out.println("将盘子从"+a+"移动到"+c);
        }
        else{
            //将上面的n-1个盘子从A柱子移动到B柱子（借助C柱子）
            hanoi(n-1,a,c,b);
            // 将最底下的一个盘子从A柱子移动到C柱子
            System.out.println("将盘子从"+a+"移动到"+c);
            // 将B柱子上的n-1个盘子移动到C柱子（借助A柱子）
            hanoi(n-1,b,a,c);
        }
    }
    //快速排序-见chapter01 sort-test
}
