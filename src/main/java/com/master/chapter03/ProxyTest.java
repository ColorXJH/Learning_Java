package com.master.chapter03;

/**
 * @ClassName: ProxyTest
 * @Package: com.master.chapter03
 * @Description: 代理模式
 * @Datetime: 2023/8/15 19:40
 * @author: ColorXJH
 */
public class ProxyTest {
    public static void main(String[] args) {
        new ProxyServer(new Server()).browser();
    }
}

interface NetWork{

    void browser();

}
class Server implements NetWork{

    @Override
    public void browser() {
        System.out.println("真实的服务器访问网络");
    }
}

class ProxyServer implements NetWork{
    private NetWork netWork;
    public ProxyServer(NetWork work){
        netWork=work;
    }
    public void check(){
        System.out.println("联网之前的检查工作");
    }
    @Override
    public void browser() {
        check();
        netWork.browser();
    }
}