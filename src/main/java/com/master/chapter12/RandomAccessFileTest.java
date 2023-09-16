package com.master.chapter12;

import java.io.*;

/**
 * @ClassName: RandomAccessFileTest
 * @Package: com.master.chapter12
 * @Description: 随机存取文件流
 * @Datetime: 2023/9/16 14:39
 * @author: ColorXJH
 */
public class RandomAccessFileTest {
    public static void main(String[] args) {
        RandomAccessFileTest test=new RandomAccessFileTest();
        try {
            //test.testRandomAccessFileStream();
            test.testInsert();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testRandomAccessFileStream() throws IOException {
        RandomAccessFile randomAccessFile=new RandomAccessFile("name.txt","rw");
        randomAccessFile.seek(3);//将指针放置到角标为3的位置，第四个字符上 开始覆盖操作
        randomAccessFile.write("KCY".getBytes());
        File file=new File("name.txt");
        long length = file.length();
        randomAccessFile.seek(length);//实现文件末尾追加功能
        randomAccessFile.write("我爱你宝贝".getBytes());
        randomAccessFile.close();
    }
    //实现插入效果
    public void testInsert() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("name.txt", "rw");
        // 调整到要插入数据的下标
        randomAccessFile.seek(3);
        // 存储插入位置后面的数据
        StringBuilder sb = new StringBuilder((int) new File("name.txt").length());
        //start 方式1： 使用字符流读取数据
        //randomAccessFile.getFD() 是RandomAccessFile类中的一个方法，用于获取与此文件关联的文件描述符（FileDescriptor）。
        //文件描述符是一个抽象的句柄，用于表示打开的文件。它包含了文件的底层操作系统资源的引用，可以用于执行底层的文件操作。
        //在上述示例中，我们使用randomAccessFile.getFD()来获取文件描述符，并将其传递给FileInputStream的构造函数，以便创建一个新的InputStreamReader。这样做是为了确保RandomAccessFile和InputStreamReader共享同一个文件描述符，以便在读取数据时不会出现冲突或错误。
        //总之，randomAccessFile.getFD()方法用于获取RandomAccessFile对象关联的文件描述符，以便在需要时进行底层文件操作。
        //使用字节数组读取数据时，如果中文字符被分割开，就会导致乱码的情况。为了避免这个问题，可以使用字符流来读取数据
//        InputStreamReader reader = new InputStreamReader(new FileInputStream(randomAccessFile.getFD()), "UTF-8");
//        char[] chars=new char[20];
//        int ch;
//        while ((ch = reader.read(chars)) != -1) {
//            sb.append(new String(chars,0,ch));
//        }
//        System.out.println(sb.toString());
        //end


        //start 方式2：使用输出流，将数据放入输出流数组中，防止出现乱码
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        byte[] buffer=new byte[10];
        int len;
        while ((len=randomAccessFile.read(buffer))!=-1){
            bos.write(buffer,0,len);
        }
        //将流中的数据输出到StringBuilder
        sb.append(bos.toString());
        System.out.println(sb.toString());
        //end


        // 指针重新回到原始位置
        randomAccessFile.seek(3);
        randomAccessFile.write("你是我的小呀小苹果".getBytes("UTF-8"));
        // 将builder中的数据写入到文件中
        randomAccessFile.write(sb.toString().getBytes("UTF-8"));
        randomAccessFile.close();
    }

}

/**
 * RandomAccessFile声明在java.io包下，直接继承自Object类，实现了DataInput DataOutput接口，这个类既可以读也可以写
 * 支持随机访问的方式，程序可以直接跳到文件的任意位置进行读写
 *          支持只访问文件的部分内容    在文件末尾追缴内容
 * 该对象包含一个记录指针，用于标记当前读写出的位置
 *          long getFilePointer() 获取当前指针的位置
 *          void seek(long pos) 将文件记录指针定位到pos位置
 * randomAccessFile可以实现多线程 断点下载上传功能 记录每个线程的指针位置，可以自己实现一下
 */