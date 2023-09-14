package com.master.chapter11;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:其他流的使用
 * @date 2023-09-14 12:45
 */
public class OtherStreamTest {
    public static void main(String[] args) {
        OtherStreamTest test = new OtherStreamTest();
        //test.test1();
        //test.test2();
        //test.test3();
        //test.test4();
        test.test5();
    }

    public void test1() {
        Scanner scanner = new Scanner(System.in);
        String context;
        while (true) {
            context = scanner.nextLine();
            System.out.println(context);
            if (!"e".equals(context) && !"exit".equals(context)) {
                System.out.println(context.toUpperCase(Locale.ROOT));
            } else {
                break;
            }
        }
    }

    public void test2() {
        //缓冲字符流有一个readLine()方法，要是输入字节流转换为字符流，中间有一个转换流桥梁 字节-》字符转化
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String context;
        try {
            while (true) {
                context = bufferedReader.readLine();
                if ("e".equalsIgnoreCase(context) || "exit".equalsIgnoreCase(context)) {
                    System.out.println("程序结束");
                    break;
                }
                System.out.println(context.toUpperCase(Locale.ROOT));
            }
        } catch (Exception e) {

        } finally {
            if(bufferedReader!=null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //打印asciii码表
    public void test3() {
        PrintStream ps = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File("ASCII.txt"));
            ps = new PrintStream(fos, true);
            if (ps != null) {
                System.setOut(ps);
            }
            for (int i = 0; i <= 255; i++) {
                System.out.print((char) i);
                if (i % 50 == 0) {
                    System.out.println();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();//打印流关闭不会报io异常，不需要异常处理
            }
        }

    }
    //数据流写入，读取时需要使用流，而不是双击文件打开
    public void test4() {
        DataOutputStream dataOutputStream = null;
        try {
            dataOutputStream = new DataOutputStream(new FileOutputStream(new File("data.txt")));
            dataOutputStream.writeUTF("ColorXJH");
            dataOutputStream.writeInt(28);
            dataOutputStream.writeDouble(135.6);
            dataOutputStream.writeChar('X');
            dataOutputStream.writeBoolean(true);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(dataOutputStream!=null)
                dataOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //读的时候顺序不能变
    public void test5() {
        DataInputStream dataInputStream = null;
        try {
            dataInputStream = new DataInputStream(new FileInputStream("data.txt"));
            String s = dataInputStream.readUTF();
            int i = dataInputStream.readInt();
            double b = dataInputStream.readDouble();
            char c = dataInputStream.readChar();
            boolean b1 = dataInputStream.readBoolean();
            System.out.println(s + "---" + i + "---" + b + "---" + c + "---" + b1);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(dataInputStream!=null)
                dataInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 标准输入输出流
 * 可以对基本的system类de默认设备进行重定向输出输入
 * 打印流 PrintStream PrintWriter  都是输出流 实现将基本数据类型数据格式化为字符串输出
 *      PrintStream打印的所有字符都可以使用平台默认的字符编码转化为字节
 *      在需要写入字符而不是字节时，推荐使用PrintWriter
 *      System.out返回的时PrintStream的实例
 * 数据流:操作java语言的基本数据类型和String,可以使用数据流
 *      DataInputStream  DataOutputStream
 *      分别套接在InputStream/OutputStream的子类流上
 *      常用方法：boolean readBoolean()  byte readByte() char readChar()  float readFloat()
 *      double readDouble() short readShort()  long readLong()  int readInt() String readUTF()
 *      void readFully(byte[] b)
 *      还有输出流对应的write方法
 */

/**
 * 创建一个类，可以读取标准输入的字符串，数字，整型数据
 * 创建一个字符缓冲流，与上一样，首先入去readLine(),
 * 然后将这个字符串改为整型 浮点型 如果可以转化内的话
 * 当然你也可以偷懒使用代理模式，包装一个Scanner类，使用其中的方法
 */