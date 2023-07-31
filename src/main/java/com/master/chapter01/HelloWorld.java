package com.master.chapter01;

/**
 * @ClassName: HelloWorld
 * @Package: com.master.chapter01
 * @Description:
 * @Datetime: 2023/7/24 19:47
 * @author: ColorXJH
 */
public class HelloWorld {
    public static void main(String[] args) {
        int xx=0,yy=1,zz=3;
        int xb=--xx- --yy- --yy;//-1 - 0- (-1)=
        int max=(xx>yy?xx:yy)>zz?(xx>yy?xx:yy):zz;
        System.out.println(xb);
        System.out.println(max);
    }
}
