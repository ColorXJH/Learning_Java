package com.master.chapter11;

import java.io.*;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:转换流实例
 * @date 2023-09-13 16:17
 */
public class TransformerStreamTest {
    public static void main(String[] args) {
        TransformerStreamTest test=new TransformerStreamTest();
        test.transformerReadWriter();
    }
    //reader相当于是一个解码的操作 writer相当于是一个编码操作
    public void transformerReadWriter() {
        //字节输入流
        InputStreamReader reader = null;
        OutputStreamWriter writer=null;
        try {
            FileInputStream inputStream = new FileInputStream("name.txt");
            FileOutputStream outputStream=new FileOutputStream("name1.txt");
            //字节转换为字符的输入转换流                         使用系统默认的字符集, 解码集些什么，取决于当初村这个文件使用的是什么编码集
            reader = new InputStreamReader(inputStream, "UTF-8");
            //编码解码不一致会乱码
            writer= new OutputStreamWriter(outputStream,"GBK");
            char[] ch = new char[10];
            int len;
            while ((len = reader.read(ch)) != -1) {
                String str = new String(ch, 0, len);
                System.out.print(str);
                writer.write(ch,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(writer!=null)
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(reader!=null){}
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 转换流:
 *      从程序输入的角度看，可以把一个字节输入流转换为一个字符输入流 中间的桥梁InputStreamReader  解码
 *      然后输出的角度看 可以将字符流转换为原始的字节输出流 中间的桥梁 OutputStreamWriter   编码
 *      他们都属于字符流，处理流       涉及到编码 解码 字符集的问题
 */
