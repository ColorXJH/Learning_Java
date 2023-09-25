package com.master.chapter13;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: java 网络编程
 * @date 2023-09-18 14:49
 */
public class InternetProgrammingTest {
    public static void main(String[] args) {
        InternetProgrammingTest test=new InternetProgrammingTest();
        test.test1();

    }
    public void test1(){
        try {
            InetAddress address=InetAddress.getByName("86.86.136.65");
            System.out.println(address);
            InetAddress address1=InetAddress.getByName("www.baidu.com");
            System.out.println(address1);
            InetAddress address3=InetAddress.getByName("localhost");
            System.out.println(address3);
            InetAddress address2=InetAddress.getLocalHost();
            System.out.println(address2);
            //    /86.86.136.65
            //    www.baidu.com/180.101.50.242
            //    localhost/127.0.0.1
            //    DESKTOP-O5JSGQM/86.86.136.65
            System.out.println("---------------------");
            //获取域名和主机地址
            System.out.println(address.getHostName());
            System.out.println(address.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
    //tcp 客户端发送信息给服务端
    @Test
    public void test2Client() {
        //1:创建socket对象 知名服务器端的ip+端口号
        OutputStream outputStream = null;
        Socket socket = null;
        try {
            InetAddress address = InetAddress.getByName("127.0.0.1");
            socket = new Socket(address, 8799);
            //2:获取输出流 用于输出数据
            outputStream = socket.getOutputStream();
            outputStream.write("你好，我是客户端MM".getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //3：关闭资源
                if(outputStream!=null)
                outputStream.close();
                if(socket!=null)
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //tcp 服务端将数据显示在控制台上
    @Test
    public void test2Server() {
        ByteArrayOutputStream bios = null;
        InputStream inputStream = null;
        Socket accept = null;
        ServerSocket socket = null;
        try {
            //1：创建服务器短socket
            socket = new ServerSocket(8799);
            //2：获取客户端socket
            accept = socket.accept();
            //3:获得输入流
            inputStream = accept.getInputStream();
            //不建议，可能有乱码，中文
//        int len;
//        byte[]bytes=new byte[20];
//        while ((len=inputStream.read())!=-1){
//            System.out.print(new String(bytes,0,len));
//        }
            //4：读取数据
            bios = new ByteArrayOutputStream();
            byte[] buffer = new byte[10];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                bios.write(buffer, 0, len);
            }
            System.out.println(bios.toString());
            System.out.println("收到了来自于哪里的数据"+socket.getInetAddress().getHostAddress());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //5：关闭资源
            try {
                if(bios!=null)
                bios.close();
                if(inputStream!=null)
                inputStream.close();
                if(accept!=null)
                accept.close();
                if(socket!=null)
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void test4(){

    }
}
/**
 * 计算机网络：不同区域的计算机互通连接成一个规模大的网络系统
 * 网络编程：直接或间接的通过网络协议与其他计算机实现数据交换，进行通讯
 * 网络编程中的两个主要问题：
 *      如何准确的定位网络上一台或多态主机，定位主机上的特定应用 ip+port
 *      找到主机后如何高效可靠的进行数据传输  网路通信协议（osi模型：过理想化的7层模型 TCP/IP模型：事实上的国际准则）
 * 网络通信协议太复杂，在指定协议时，将复杂的协议分成一些简单的成分，再将他们复合起来，最常用的复合方式就是层次复合
 *      即同层见可以通信，上一层可以调用下一层，而与再下一层不发生关系，各层互不影响，有利于系统的开发和扩展
 *      osi参考模型：   7层模型：    应用层 表示层 会话层 传输层 网络层 数据链路层 物理层
 *      TCP/IP参考模型：4层模型 ：   应用层             传输层 网络层  数据链路物理层
 *      TCP/IP参考模型对应协议  应用层：http,ftp,dns,telnet...
 *                           传输层：TCP,UDP
 *                           网络层：IP,ICMP,ARP
 *                           数据链路物理层：Link
 * IP地址类：InetAddress
 *      192.168.xx.xx是局域网 不是公网 万维网
 *
 * 域名：wwww.baidu.com 先通过本地的host文件查看有没有对应的映射，没有就去dns服务器上解析相应的ip地址
 * 本地回路地址：127.0.0.1  域名/本机地址：localhost
 *
 * ip+端口 组合在一起得到了一个网络套接字 Socket
 *
 */

/**
 * 传输层中有两个比较重要的协议：TCP/UDP
 * TCP:传输控制协议
 * UDP:用户数据报协议
 * TCP/IP 包含两个主要协议: 传输控制协议/网络互联协议 的一组协议
 */


/**
 * TCP协议：
 *      使用TCP协议之前需要建立TCP链接，形成传输数据通道
 *      传输前，采用三次握手方式，点对点通信 可靠
 *      TCP协议进行通信的两个应用进程：客户端，服务端
 *      在连接中可以进行大量数据的传输
 *      传输完毕，需要释放已建立的链接，效率低
 *
 *      链接建立完成之后有四次挥手，用于关闭连接时
 */

/**
 * UDP协议：
 *      将数据、源、目的封装成数据包，不需要建立链接
 *      每个数据报的大小限制在64K内
 *      发送不管对方是否准备好，接收方收到也不确认，是不可考的
 *      可以广播发送
 *      发送给数据结束时无需释放资源，开销小，速度快
 *
 */