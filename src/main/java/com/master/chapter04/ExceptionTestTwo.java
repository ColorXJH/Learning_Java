package com.master.chapter04;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 异常放肆处理2： throws+异常类型
 * @date 2023-08-17 16:38
 */
public class ExceptionTestTwo {

    //主方法上一般捕捉，不然jvm就挂了，导致程序不正常退出 Process finished with exit code 1  正常为0

    public static void main(String[] args) /*throws IOException*/ {
        try {
            test2();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        test2();
    }

    public static void test2() throws IOException {
        test1();
    }

    public static void test1() throws FileNotFoundException,IOException {
        //抛出异常之后，后续的代码将无法执行，直接返回上级程序被捕捉的程序中
        File file=new File("test123.txt");
        FileInputStream reader=new FileInputStream(file);
        int data;
        //在字符流中是按字符读取的，而不是按字节读取。这是因为字符流在内部会根据指定的字符编码将字节转换为字符。
        while ((data=reader.read())!=-1){
            System.out.print((char)data);
        }

    }
}

//继承中父子类抛出异常的情况：子类抛出的异常需要比父类小，这点和可见性相反，子类的可见性需要大于等于父类
//重写参数方法必须一致（方法签名+参数类型）

class A{
    private String name;
    protected void show(int age) throws IOException {
//        File file=new File("test123.txt");
//        FileInputStream stream=new FileInputStream(file);
    }
}

class B extends A{
    @Override
    public void show(int age) throws FileNotFoundException {
    }
}
