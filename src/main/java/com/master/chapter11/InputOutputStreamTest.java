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
        //test.testInputStreamOutputStream();
        //缓冲流
        //test.testBuffedCopy();
        test.copyWithBufferedReaderWriter();
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
                //可以对图片文件进行加解密操作，对字节进行亦或操作,一个数据亦或两次就变成原始数据
                //x^5=y  y^5=x=>所以如果需要解密，就在运行一次，将加解密文件对调一下
//                for(int i=0;i<readNum;i++){
//                    bytes[i]=(byte)(bytes[i]^5);
//                }
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
    //使用缓冲流而不是单纯的字节/字符流
    public void testBuffedCopy() {
        File srcFile = new File("true.mp4");
        File descFile = new File("true1.mp4");
        FileInputStream inputStream=null;
        FileOutputStream outputStream=null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            inputStream = new FileInputStream(srcFile);
            outputStream = new FileOutputStream(descFile);
            bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            byte[] buffer = new byte[1024];
            int byteRead;
            while ((byteRead = bufferedInputStream.read(buffer)) != -1) {
                bufferedOutputStream.write(buffer, 0, byteRead);
            }
            //刷新缓冲输出流的缓冲区数据
            //bufferedOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //先关闭输出 再关闭输入  先关闭缓冲流，再关闭底层流
            //但是其实我们在关闭外层流的时候，会自动昂我们关闭底层流
            //所以只需关闭缓冲输出流 然后关闭缓冲输入流就可以了
            try {
                if(bufferedOutputStream!=null)
                    bufferedOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
//            try {
//                if(outputStream!=null)
//                    outputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            try {
                if(bufferedInputStream!=null)
                    bufferedInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
//            try {
//                if(inputStream!=null)
//                    inputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

        }
    }

    public void copyWithBufferedReaderWriter() {
        BufferedWriter writer = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new File("name.txt")));
            writer = new BufferedWriter(new FileWriter(new File("name1.txt")));
            //方法1
//            char[] ch = new char[10];
//            int length;
//            while ((length = reader.read(ch)) != -1) {
//                writer.write(ch, 0, length);
//            }
            //方法2
            String context;
            while ((context=reader.readLine())!=null){
                //问题 不包含换行符，需要自己处理
                //writer.write(context+"\n");
                //或者
                writer.write(context);
                writer.newLine();//提供换行的操作
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(writer!=null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
 * 输出是若文件不存在则会创建一个该文件,输出流时可以设置是否对原有文件覆盖还是追加
 */

/**
 * 文本文件建议使用字符流读，编码不一致会乱码，字符流可以指定编码，字节流没有编码因为不需要编码，不存在乱码，除非你在操作过程中查看了文件，单纯的额复制是不会造成乱码的
 * 二进制文件，比如音频 视频 图片 建议使用字节流
 */

/**
 * 关于缓冲流和字节流的区别：他们都可以定义一个字节数组来批量传递
 * 缓冲流的内部默认的缓冲区大小和我们定义的传递数据字节的byte[] buffer是不同的概念。
 * 缓冲流的内部默认缓冲区大小是根据具体实现类来确定的，一般情况下是根据操作系统的文件系统进行优化的。
 *
 * 缓冲流BufferedOutputStream有一个内部缓冲区，它以缓冲区大小作为数据传输的条件。
 * 当调用write方法时，数据会先写入到内部缓冲区中，当缓冲区满了或者调用flush方法时，才会将缓冲区中的数据一次性写入到目标文件。
 *
 * 而字节流FileOutputStream没有内部缓冲区，它直接将数据写入到目标文件，没有缓冲区的概念。
 * 当调用write方法时，数据会直接写入到目标文件
 */