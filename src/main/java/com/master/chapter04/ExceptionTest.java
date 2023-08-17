package com.master.chapter04;

import org.junit.Test;

import java.io.*;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 异常处理
 * @date 2023-08-17 9:27
 */
public class ExceptionTest {
    public static void main(String[] args) {
        //StackOverflowError 栈溢出异常
        //main(args);
        //OutOfMemoryError OOM 堆溢出异常
        //Integer[] arr=new Integer[1024*1024*1024];
    }

    @Test
    public void test7(){
        //文件 "test.txt" 将被创建在相对于当前工作目录的位置。在 IntelliJ IDEA 中，默认的当前工作目录通常是项目的根目录
        File file=new File("test.txt");
        try {
            FileInputStream inputStream=new FileInputStream(file);
            //读取一个字节
            int data=inputStream.read();
            while (data!=-1){
                //将这个字节数据转换为字符型数据(有中文时会乱码)
                System.out.print((char) data);
                data=inputStream.read();
            }
            //关闭资源流
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test8(){
        File file=new File("test.txt");
        try(FileInputStream fileInputStream=new FileInputStream(file);
            //UTF-8 UTF-16 都是变长的字符集
            //UTF-8 是一种变长编码，它使用 1 到 4 个字节来表示不同的字符。
            //对于占用两个字节的字符，UTF-8 编码会将其表示为两个字节的序列。
            //当您使用 字节流 reader.read() 方法读取一个 UTF-8 编码的文件时，它会按字节读取，而不会进行字符编码的转换。
            //当您使用 字符流 reader.read() 方法读取一个字符时，它会返回一个 int 类型的值，该值表示读取的字符，可以正确地处理占用两个字节的字符。因此，当读取一个占用两个字节的字符时，转换为 char 类型并打印通常不会出错
            InputStreamReader reader=new InputStreamReader(fileInputStream,"UTF-8")
        ){
            int data;
            //在字符流中是按字符读取的，而不是按字节读取。这是因为字符流在内部会根据指定的字符编码将字节转换为字符。
            while ((data=reader.read())!=-1){
                System.out.print((char)data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

