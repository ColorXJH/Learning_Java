package com.master.chapter11;

import java.io.File;

/**
 * @ClassName: FileTest
 * @Package: com.master.chapter11
 * @Description: 文件/文件目录路径类的抽象表示
 * @Datetime: 2023/9/9 20:44
 * @author: ColorXJH
 */
public class FileTest {
    public static void main(String[] args) {
        /**
         * 创建File类的实例
         *      绝对路径与相对路径
         *      windows/dos默认使用\ ==》\\
         *      Unix、URL使用/ ==>/
         *      java跨平台：seperator分隔符
         */
        //不同的创建file的方式
        File file=new File("test.properties");//相对于本项目
        File file1=new File("D:\\学习\\test-git\\waiter-service.yml");//绝对路径 关于分隔符\\ 实际上使用/这种unix写法 java-windows平台也能识别
        System.out.println(file);
        System.out.println(file1);

        File file2=new File("D:\\学习\\test-git","waiter-service.yml");
        File file3=new File(file2,"waiter-service.yml");
    }
}


/**
 * File类的常用功能
 *      获取功能
 *
 *
 *      重命名功能
 *
 */