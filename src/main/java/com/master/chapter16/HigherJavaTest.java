package com.master.chapter16;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: jdk9及其以上版本介绍
 * @date 2023-10-18 15:23
 */
public class HigherJavaTest {
}

/**
 * JDK9
 *  新特性
 *      模块化系统
 *      jShell命令
 *      多版本兼容jar包
 *      接口的私有方法
 *      钻石操作符的使用升级（范型）
 *      语法改进：try语句
 *      String存储结构变更
 *      便利的集合特性：of()
 *      增强的Stream API
 *      全新的http客户都安api
 *      Deprecated相关的api
 *      javac的html5支持
 *      javascript引擎升级：Nashorn
 *      java的动态编译器
 */

/**
 * jdk8及其以前的版本目录结构：bin db include jre（下级bib lib目录） lib
 *      bin:包含命令行开发和调试工具，例如javac,jar、javadoc
 *      include:包含在编译本地代码时使用的c/c++头文件
 *      lib:包含jdk工具的几个jar和其他类型的文件，它有一个tools.jar,其中包含javac编译器的java类
 *      jre/bin:包含基本命令，例如java命令，在windows平台上，它包含系统的运行时动态链接库DLL
 *      jre/lib:包含用户可以编辑的配置文件，如.properties和.policy文件，包含几个jar，rt.jar文件包含运行时的java类和资源文件
 * jdk8之后的目录结构：bin conf include jmods legal lib
 *      bin:包含所有命令，在windows平台上，它继续包含系统的运行时动态链接库
 *      conf:包含用户可编辑的配置文件
 *      include:包含要在以前编译本地代码时使用的c/c++头文件，它只存在与jdk中
 *      jmods:包含JMOD格式的平台模块，创建自定义运行时映像时需要它，它只存在于jdk中
 *      legal:包含法律声明
 *      lib:包含非windows平台上的动态链接本地库，其子目录和文件不应由开发人员直接编辑或使用
 * Module：模块化系统
 *      模块将由通常的类与新的模块声明文件（module-info.java）组成，该文件是位于java代码结构的顶层，该模块描述符明确的定义了我们的模块需要什么依赖关系
 *      以及哪些模块被外部使用，在exports子句中未提及到的包默认情况下将封装在模块中，不能在外部使用
 * Java的REPL工具：jshell命令
 *      像python,Scala之类的语言早就有了交互式编程环境REPL(read-evaluate-print-loop)了，以交互式的方式对语句和表达式进行求值，开发者只需要输入一些代码，
 *      就可以在编译前获得对程序的反馈，而之前的java版本想要执行代码，必须创建文件，声明类，提供测试方法才能实现
 *      设计理念：即写即得，快速运行
 *      实现目标:java9中终于拥有的REPL工具，jShell，让java可以像脚本语言一样执行，从控制台启动jShell，利用jShell在没有类的情况下直接声明变量，计算表达式
 *      执行语句，即开发时可以在命令行内直接运行java代码，而无需创建java文件，无需再书写你main方法了
 *          jShell也可以从文件中加载语句或者将语句保存在文件中
 *          jShell也可以是tab进行自动补全和自动添加分号
 *      如何使用，直接在对应jdk版本的bin目录下cmd,进入之后命令行直接运行jshell.exe
 *      /help 请求使用方法  /help intro
 *      /edit 调出刚才定义的一些操作记录
 *      可以定义变量 方法，类，导包，也可以导入第三方jar包
 *      /imports 默认导入的包
 *      方法，变量 类可以覆盖之前写的
 *      /open 文件路径，调用脚本文件（就是在命令行中的代码文件）
 *      jShell没有受检异常，本来应该强迫我们捕获的，但是jShell在后台为我们隐藏掉了，所以我们声明哪些带受检异常的操作时不需要try-catch了
 *      /exit 退出
 */