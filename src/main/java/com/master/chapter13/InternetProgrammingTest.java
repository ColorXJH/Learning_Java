package com.master.chapter13;

import org.junit.Test;

import java.io.*;
import java.net.*;

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

    //socket-serverSocket网络通信复制文件
    @Test
    public void test3Client() {
        OutputStream outputStream = null;
        FileInputStream fils = null;
        Socket socket = null;
        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 9090);
            outputStream = socket.getOutputStream();
            fils = new FileInputStream("001.jpg");
            byte[] bytes = new byte[1024];
            int len;
            while ((len = fils.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(outputStream!=null)
                outputStream.close();
                if(fils!=null)
                fils.close();
                if(socket!=null)
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void test3Server() {
        FileOutputStream fos = null;
        InputStream inputStream = null;
        ServerSocket serverSocket = null;
        Socket accept = null;
        try {
            serverSocket = new ServerSocket(9090);
            accept = serverSocket.accept();
            inputStream = accept.getInputStream();
            fos = new FileOutputStream("001_COPY.jpg");
            byte[] bytes = new byte[1024];
            int len;
            while ((len = inputStream.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fos!=null)
                fos.close();
                if(inputStream!=null)
                inputStream.close();
                if(serverSocket!=null)
                serverSocket.close();
                if(accept!=null)
                accept.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //客户都安发送文件给服务端，服务端保存到本地并返回“发送成功给客户端”
    @Test
    public void test4Client(){
        OutputStream outputStream = null;
        FileInputStream fils = null;
        Socket socket = null;
        InputStreamReader reader=null;
        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 9090);
            outputStream = socket.getOutputStream();
            fils = new FileInputStream("001.jpg");
            byte[] bytes = new byte[1024];
            int len;
            while ((len = fils.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
            //socket阻塞式的编程不会终止，需要显示关闭一下数据的输出
            socket.shutdownOutput();

            //客户都安接收来自于服务器端的数据并显示到控制台上
            InputStream inputStream = socket.getInputStream();
            //为了防止乱码 bos可以，也可以使用转换流InputStreamReader 作为桥梁将字节流转换为字符流
            //ByteArrayOutputStream bos=new ByteArrayOutputStream();
            reader=new InputStreamReader(inputStream,"UTF-8");
            char[]chars=new char[10];
            int clen;
            while ((clen=reader.read(chars))!=-1){
                System.out.print(new String(chars,0,clen));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(reader!=null)
                    reader.close();
                if(outputStream!=null)
                    outputStream.close();
                if(fils!=null)
                    fils.close();
                if(socket!=null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void test4Server(){
        FileOutputStream fos = null;
        InputStream inputStream = null;
        ServerSocket serverSocket = null;
        OutputStream outputStream=null;
        Socket accept = null;
        try {
            serverSocket = new ServerSocket(9090);
            accept = serverSocket.accept();
            inputStream = accept.getInputStream();
            fos = new FileOutputStream("001_COPY.jpg");
            byte[] bytes = new byte[1024];
            int len;
            while ((len = inputStream.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
            }

            //不会显示关闭 关闭输入数据（其实关闭一个就可以了）
            accept.shutdownInput();

            //服务器端给与客户都安反馈，
            outputStream = accept.getOutputStream();
            outputStream.write("你好，照片我已经收到，谢谢".getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(outputStream!=null)
                    outputStream.close();
                if(fos!=null)
                    fos.close();
                if(inputStream!=null)
                    inputStream.close();
                if(serverSocket!=null)
                    serverSocket.close();
                if(accept!=null)
                    accept.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testUDPSend() throws IOException {
        DatagramSocket socket=new DatagramSocket();
        String str="我是UDP发送端的导弹";
        byte[] bytes = str.getBytes();
        InetAddress address=InetAddress.getLocalHost();
        DatagramPacket packet = new DatagramPacket(bytes,0,bytes.length,address,9090);
        socket.send(packet);
        socket.close();
    }
    @Test
    public void testUDPReceive() throws IOException {
        DatagramSocket socket=new DatagramSocket(9090);
        byte[] bytes=new byte[100];
        DatagramPacket packet=new DatagramPacket(bytes,0,bytes.length);
        socket.receive(packet);
        System.out.println(new String(packet.getData(),0,packet.getLength()));
        socket.close();
    }
    @Test
    public void testURL(){
        try {
            URL url=new URL("http://localhost:8080/test?name=xjh&age=28");
            String protocol = url.getProtocol();
            String host = url.getHost();
            int port = url.getPort();
            String path = url.getPath();
            String file = url.getFile();
            String query = url.getQuery();
            System.out.println(protocol);
            System.out.println(host);
            System.out.println(port);
            System.out.println(path);
            System.out.println(file);
            //http://localhost:8080/test#a?name=xjh&age=28 这种带锚点的查不出来 显示null
            System.out.println(query);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    //启动tomcat服务器，下载上面的资源
    @Test
    public void testURLDownload() {
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL("http://localhost:8080/examples/index.html");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            fileOutputStream = new FileOutputStream("index_copy.html");
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (inputStream!=null)
                inputStream.close();
                if(fileOutputStream!=null)
                fileOutputStream.close();
                if(urlConnection!=null)
                urlConnection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

/**
 * udp网络通信
 * 类DatagramSocket和DatagramPacket实现了基于UDP协议网络程序
 * UDP数据报通过数据报套接字DataGramSocket发送和接收，系统不保证UDP数据报一定能安全的送达到目的地，也不能确定什么时间可以抵达
 * DataGramPacket对象封装了UDP数据包，在数据报中包含了发送端的ip和端口号和接收端的ip和端口号
 * UDP协议中每个数据报都给出了完整的地址信息，因此无需建立发送方和接受方的连接，如同发快递包裹一样
 */

/**
 * URL网络编程
 * URL:统一资源定位符，它表示internet上某一资源的地址
 * 它是一种具体的uri,即url可以用来标识一个资源，而且还指明了如何locate这个资源
 * 通过url我们可以访问internet上的各种网络资源，比如常见的www,ftp站点，浏览器通过解析给定的url可以在网络上查找相应的文件或者而其他资源
 * URL的基本结构由5部分组成：
 *  传输协议://主机名:端口号/文件名#片段名?参数列表   片段名：即锚点 可以直接定位到某个位置     参数列表：k1=v1&k2=v2
 *  http://127.0.0.1/test1#a?name=xjh&age=28
 *      一个URL对象生成后，其属性是不能被改变的，但是可以通过他给定的方法来获取这些属性：
 *          getProtocol() 获取url协议名
 *          getHost() 获取url主机名
 *          getPort() 获取url端口号
 *          getPath() 获取url文件路径
 *          getFile() 获取url的文件名
 *          getQuery() 获取url的查询名
 */