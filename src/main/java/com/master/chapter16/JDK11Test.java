package com.master.chapter16;

import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * @ClassName: JDK11Test
 * @Package: com.master.chapter16
 * @Description:
 * @Datetime: 2023/10/22 22:42
 * @author: ColorXJH
 */
public class JDK11Test {
    public static void main(String[] args) {
        System.out.println("测试java11的编译运行命令： java JDK11Test.java");
    }
    @Test
    public void testStringMethodAdd(){
        System.out.println(" ".isBlank());
        System.out.println(" javascript ".strip());
        System.out.println(" java ".stripTrailing());
        System.out.println(" java ".stripLeading());
        System.out.println("java".repeat(2));
        System.out.println("a\nb\nc\n".lines().count());
    }
    @Test
    public void testOptionalAdd(){
        Optional<Date> date = Optional.ofNullable(null);
        System.out.println(date.isEmpty());
        date.ifPresentOrElse(System.out::println,()-> System.out.println("xxxx---"));
        System.out.println(date.or(() -> Optional.of(new java.sql.Date(1697988084416L))));
        System.out.println(date.stream());
        System.out.println(date.orElseThrow(()->new NoSuchElementException()));
    }
    @Test
    public void testVarAnnotation(){
        //错误形式，必须要有类型，可以加上var
        //Consumer<String>cons1=(@Deprecated t)-> System.out.println(t);
        //使用var的好处是在使用lambda表达式的时候给参数加上注解
        Consumer<String>cons1=(@Deprecated var t)-> System.out.println(t);
    }
    @Test
    public void testHttpClientApi() throws IOException, InterruptedException {
        //HttpClient替换原有的HttpURLConnection 注意需要在module-info.java下 requires java.net.http;
        HttpClient client=HttpClient.newHttpClient();
        HttpRequest request=HttpRequest.newBuilder(URI.create("http://www.baidu.com")).build();
        HttpResponse.BodyHandler<String> stringBodyHandler1 = HttpResponse.BodyHandlers.ofString();
        HttpResponse<String>response=client.send(request, stringBodyHandler1);
        String body=response.body();
        System.out.println(body);
    }

}


/**
 * JDK11将带来ZGC Http Client等重要特性，一共包含17个JEP(jdk enhancement proposals JDK增强提案)
 * 总计更新不止17个，但是我们关注的是17个
 *
 * 从JVM GC的角度来说，JDK11引入了两种新的GC,其中包含也许是划时代意义的ZGC,虽然目前还是实验特性，但是从能力上来看，这是JDK的一个巨大突破，
 * 为特定生产环境的苛刻需求提供了一个可能的选择，例如对部分企业核心等存储产品，如果能够保存不超过10ms的GC暂停，可靠性会上一个大的台阶，这是我们
 * 过去GC调优几乎做不到的，是能与不能的问题
 *
 * 新的长期支持版本LTS 将会每三年发布一次
 *  1：新增了一系列字符串的处理方法
 *      判断字符串是否为空白=》“”.isBlank();//true
 *      去除收尾空白=》“ javascript ”.strip();//"javascript"
 *      去除尾部空格=》“ javascript  ”.stripTrailing();//“ javascript”
 *      去除首部空格=》“ javascript ”.stripLeading();//"javascript "
 *      复制字符串=》“java”.repeat(3);//"javajavajava";
 *      行数统计=》“a\nb\nc\n”.lines().count();//3
 *  2：Optional增强
 *      Optional也增强了几个非常酷的方法，现在可以很方便的将一个Optional转换为一个Stream，或者当一个空Optional时给他一个替代的
 *          boolean isEmpty();判断value是否为空
 *  3：局部变量类型推断升级
 *      在var上添加注解的语法格式，在jdk中是不能实现的，jdk11加入了这样的语法
 *  4：全新的http客户端api
 *      http，用于传输网页的协议，早在1997年就被采用在现在的1.1版本中，直到2015年http2才成为标准（1991:http/0.9  1996:http/1.0  1999:http/1.1  2015:http/2）
 *      http1.1和http2的主要区别是如何在客户端和服务器之间构架和传输数据：http1.1依赖于请求和响应周期，http/2允许服务器push数据。它可以发送比客户端请求更多的数据，这使得它可以
 *          优先处理并发送对于首先加载网页至关重要的数据
 *      java9开始引入了一个处理http请求的http clinet api,该api支持同步和异步，而在java11中已成正式可用状态
 *      它将替代仅适用于blocking模式的HttpUrlConnection(HttpUrlConnection是在http1.0的时代创建的，并使用了协议无关的方法)，并提供了对WebSocket和http/2的支持
 *  5：更简化的编译运行程序
 *      看下面的代码
 *          编译 javac javastack,java
 *          运行 java javastack
 *              在我们的认识里面，要运行一个java源代码必须要先编译，再运行，执行两步操作动作
 *      在jdk11的版本中，通过一个java命令就可以搞定：java javastack.java
 *          注意:一个命令编译运行源代码时候：执行源文件的第一个类，第一个类必须包含主方法
 *          并且不可以使用其他源文件的自定义类，本文件中的自定义类是可以使用的
 *          详情见JDK_11_TEST.java
 *  6：废弃Nashorn引擎
 *      后续版本考虑移除，使用GraalVM
 *  7：ZGC
 *      GC是java主要优势之一，然而当GC停顿时间太长，就会开始影响应用的响应时间，消除或者减少GC的停顿时间使得java将应用在更广泛的场景
 *      此外，现代系统的内存不断增长，用户和程序员希望JVM以高效的方式充分利用这些内存，并且无需长时间的GC暂停
 *      ZGC是一个并发的，基于region,压缩型的垃圾收集器，只有root扫描阶段会SWT，因此GC停顿时间不会随着堆的增长和存货对象的增长而变长
 *          优势：
 *              GC暂停时间不会超过10ms
 *              既能处理几百兆的小堆，也能处理几个T的大堆
 *              和G1相比，应用吞吐能力不会下降到15%（G1是历代jvm的重要垃圾回收器）
 *              为未来的GC功能和应用colord指针和load barriers优化奠定基础
 *              初始只支持64位系统
 *          设计目标：
 *              支持TB级的内存容量，暂停时间低，对整个程序的吞吐量影响小，将来还可以实现扩展机制
 *              例如多层堆（即热对象置于DRAM和冷对象置于NVMe闪存）或者压缩堆
 *  8：其他新特性
 *      Unicode 10
 *      Deprecate the Pack200 Tools and API
 *      新的Epsilon垃圾收集器
 *      完全支持Linux容器，包括Docker
 *      支持G1上的并行完全垃圾收集
 *      最新的HTTPS安全协议TLS 1.3
 *      Java Flight Recorder --飞行记录仪 类似于飞机的黑匣子
 *  9：展望
 *          直到11，目前还没看到一个标准化和轻量级的JSON API,新的货币API,ORACLE虽然定义了一套新的java货币API:JavaMoney，但是目前位置并没有加入jdk中
 *
 */