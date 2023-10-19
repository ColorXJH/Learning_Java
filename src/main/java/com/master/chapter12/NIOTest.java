package com.master.chapter12;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @ClassName: NIOTest
 * @Package: com.master.chapter12
 * @Description: java new io /no-blocking io
 * @Datetime: 2023/9/16 16:09
 * @author: ColorXJH
 */
public class NIOTest {
    public static void main(String[] args) {
        NIOTest test=new NIOTest();
        test.test1();
    }
    public void test1(){
        //Path path1= Paths.get("name.txt");
        Path path1= Paths.get("E:\\software\\git");

        System.out.println(path1);
        System.out.println(path1.startsWith("e:\\software"));
        System.out.println(path1.endsWith("name.txt"));
        System.out.println(path1.isAbsolute());
        System.out.println(path1.getParent());
        System.out.println(path1.getRoot());
        System.out.println(path1.getFileName());
        System.out.println("---start---");
        for(int i=0;i<path1.getNameCount();i++){
            System.out.println(path1.getName(i)+"********");
        }
        System.out.println("---end---");
        System.out.println(path1.toAbsolutePath());
        Path path3=Paths.get("e:\\","software");
        Path path4=Paths.get("git");
        path3=path3.resolve(path4);
        System.out.println(path3);
        File file=path1.toFile();
        Path newPath=file.toPath();
        System.out.println(newPath);
    }
}

/**
 * NIO:New IO/Non-Blocking IO  是java1.4版本开始引进的新一套的IO API 可以替代标准的 java IO API,NIO与原来的IO有相同得分作用与目的，
 * 但是使用的方式完全不同,NIO支持面向缓冲区的(IO是面向流的)，基于通道的IO操作，NIO将以更高效的方式进行文件的读写操作
 * JAVA API中提供了两套NIO 一套是针对标准输入输出的NIO  另一套是网络编程NIO
 *          java.nio.channels.Channel
 *              FileChannel:处理本地文件
 *              SocketChannel:TCP网络编程的客户端的Channel
 *              ServerSocketChannel:TCP网络编程的服务器端的Channel
 *              DatagramChannel:UDP网络编程中发送端和接收端的Channel
 * 随着jdk7的发布，java对NIO进行了极大的扩展，增强了对文件处理和文件系统特性的支持，以至于我们称之为NIO.2,因为NIO提供的一些功能，NIO已成为文件处理中越来越重要的部分
 *
 * NIO.2中的Path Paths Files核心API
 *          早期的java只提供了File类来访问文件系统，但是File类的功能比较有限，所提供的方法新能也不高，而且大多数方法在出错时仅仅返回失败，并不会返回异常信息
 *          NIO.2为了弥补这种不足,引入了Path接口，代表一个平台无关的平台路径，描述了目录结构中文件的位置，Path可以堪成是File类的升级版本，实际引用的资源也可以不存在
 *          在以前的IO操作中都是这样写的：
 *              import java.io.File；
 *              File file=new File("index.html");
 *          但是在Java7中，我们可以这样写：
 *              import java.nio.file.Path;
 *              import java.nio.file.Paths
 *              Path path=Paths.get("index.html");
 *          同时NIO.2在java.nio.file包下还提供了Files,Paths工具类，Files包含了大量的静态工具方法操作文件，Paths则包含了两个返回Path的静态工厂方法
 *          Paths类提供的静态get()方法用来Path对象
 *              static Path get(String first,String...more),用于将多个字符串连接成路径
 *              static Path get(URI uri);返回指定URI对应的Path路径
 *          Path常用方法
 *              String toString();返回调用Path对象的字符串表示形式
 *              boolean startsWith(String path);判断是否以path路径开始
 *              boolean endsWith(String path);判断是否以path路径结尾
 *              boolean isAbsolute();判断是否是绝对路径
 *              Path getParent();返回Path对象包含整个路径，不包含Path对象指定的文件路径
 *              Path getRoot();返回调用Path对象的根路径
 *              Path getFileName();返回与调用Path对象关联的文件名
 *              int getNameCount();返回Path根目录后面元素的数量
 *              Path getName(int inx);返回指定索引位置inx的路径名称
 *              Path toAbsolutePath();作为绝对路径返回调用Path对象
 *              Path resolve(Path p);合并两个路径，返回合并后的路径对应的Path对象
 *              File toFile();将Path转换为File类的对象
 *          java.nio.file.Files用于操作文件或目录的工具类
 *              Files常用方法
 *                  Path copy(Path src,Path desc,CopyOption...how)文件的复制
 *                  Path createDirectory(Path path,FileAttribute<?>...attr)创建一个目录
 *                  Path createFile(Path path,FileAttribute<?>...attr)创建一个文件
 *                  void delete(Path path)删除一个文件、目录，若不存在，执行报错
 *                  void deleteIfExists(Path path)Path对应的文件、目录如果存在，执行删除
 *                  Path move(Path src,Path desc,CopyOption...how)将src移动到desc位置
 *                  long size(Path path)返沪path指定文件的大小
 *                  有用于判断的，有用于操作内容的
 */

/**
 * apache commons下的FileUtils文件复制api
 *
 */