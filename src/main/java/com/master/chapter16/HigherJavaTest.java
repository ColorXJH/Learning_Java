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
 */