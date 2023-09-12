package com.master.chapter11;

import java.io.*;

/**
 * @ClassName: InputOutputStreamTest
 * @Package: com.master.chapter11
 * @Description: IO流
 * @Datetime: 2023/9/9 20:45
 * @author: ColorXJH
 */
public class InputOutputStreamTest {
    public static void main(String[] args) {
        InputOutputStreamTest test=new InputOutputStreamTest();
        //test.fileReader();
        //test.fileReaderOverLoad();
        //test.fileWriter();
        //test.fileReaderAndWriter();
        //复制图片
        test.testInputStreamOutputStream();
    }
    public void fileReader(){
        File file=new File("test.txt");//相对路径
        FileReader reader= null;
        try {
            int read;
            reader = new FileReader(file);
            while ((read = reader.read())!=-1){
                System.out.print((char)read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(reader!=null)
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    //使用read的重载方法
    public void fileReaderOverLoad(){
        File file=new File("name.text");
        FileReader reader=null;
        char[]ch=new char[10];
        int num;
        try {
            reader=new FileReader(file);
            while ((num=reader.read(ch))!=-1){
                //返回读到的字节数 ，独处的字节粗出在ch数组中
//                for (int i = 0; i < num; i++) {
//                    //System.out.print(ch[i]);
//                    //或者   每次只取出num个，如果取数组的长度，则可能后面的内容是上次读取遗留下的
//                }
                System.out.print(new String(ch,0,num));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(reader!=null)
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void fileWriter(){
        File file=new File("test.txt");
        FileWriter writer=null;
        try {
            writer=new FileWriter(file,true);
            writer.write("\n你好啊，亲爱的小云");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(writer!=null)
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void fileReaderAndWriter(){
        File readFile=new File("test.txt");
        File writeFile=new File("name.txt");
        FileReader reader=null;
        FileWriter writer=null;
        try {
            reader=new FileReader(readFile);
            writer=new FileWriter(writeFile,true);
            writer.write('\n');
            int readNum=0;
            char[]ch=new char[10];
            while ((readNum=reader.read(ch))!=-1){
                writer.write(ch,0,readNum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(reader!=null)
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(writer!=null)
                    writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    //图片的复制只能使用字节流
    public void testInputStreamOutputStream(){
        File readFile=new File("001.jpg");
        File writeFile=new File("001_bak.jpg");
        FileInputStream inputStream=null;
        FileOutputStream outputStream=null;
        try {
            inputStream=new FileInputStream(readFile);
            outputStream=new FileOutputStream(writeFile);
            int readNum=0;
            byte[]bytes=new byte[10];
            while ((readNum=inputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,readNum);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(inputStream!=null)
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(outputStream!=null)
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 输入输出流 站在程序的角度
 * 字节字符流 站在字节字符的角度
 * 节点处理流 站在是否直接操作文件节点的角度
 * 他们有四个基础的抽象类 InputStream OutputStream Reader Writer
 * I/O流体系如下
 * 分类               字节输入流           字节输出流           字符输入流           字符输出流
 * 抽象基类           InputStream       OutputStream         Reader              Writer
 * 访问文件          FileInputStream   FileOutputStream     FileReader         FileWriter
 * 访问数组     ByteArrayInputStream ByteArrayOutputStream CharArrayReader    CharArrayWriter
 * 访问管道       PipedInputStream     PipedOutputStream    PipedReader         PipedWriter
 * 访问字符串                                               StringReader        StringWriter
 * 缓冲流         BuffedInputStream   BuffedOutputStream   BuffedReader        BuffedWriter
 * 转换流                                                InputStreamReader  OutputStreamWriter
 * 对象流         ObjectInputStream   ObjectOutputStream
 *               FilterInputStream   FilterOutputStream   FilterReader       FilterWriter
 * 打印流                             PrintStream                              PrintWriter
 * 推回输入流    PushbackInputStream                        PushbackReader
 * 特殊流         DateInputStream     DataOutputStream
 */


/**
 * 注意输入输出的不同，出入时文件不存在会报找不到文件异常
 * 输出是若文件不存在则会创建一个该文件,输出流时可以设置是否对原有文件覆盖哈市追加
 */