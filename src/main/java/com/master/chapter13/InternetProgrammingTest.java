package com.master.chapter13;

import java.net.InetAddress;
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
    public void test2(){

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
 */

