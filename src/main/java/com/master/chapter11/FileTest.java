package com.master.chapter11;

import com.sun.org.apache.bcel.internal.generic.GETFIELD;

import java.io.File;
import java.io.FilenameFilter;

/**
 * @ClassName: FileTest
 * @Package: com.master.chapter11
 * @Description: 文件/文件目录路径类的抽象表示
 * @Datetime: 2023/9/9 20:44
 * @author: ColorXJH
 */
public class FileTest {
    public static void main(String[] args) {
        /**
         * 创建File类的实例
         *      绝对路径与相对路径
         *      windows/dos默认使用\ ==》\\
         *      Unix、URL使用/ ==>/
         *      java跨平台：seperator分隔符
         */
        //不同的创建file的方式
        File file=new File("test.properties");//相对于本项目
        File file1=new File("D:\\学习\\test-git\\waiter-service.yml");//绝对路径 关于分隔符\\ 实际上使用/这种unix写法 java-windows平台也能识别
        System.out.println(file);
        System.out.println(file1);

        File file2=new File("D:\\学习\\test-git","waiter-service.yml");
        File file3=new File(file2,"waiter-service.yml");
        System.out.println("---------------------------------");

        FileTest test=new FileTest();
        //测试文件的获取方法
        test.fileGetMethod();
        //test.testEndWithJpg1();
        //test.testEndWithJpg2();
        //test.testEndWithJpg3();
        //test.printSubFile(new File("D:\\学习\\电子书"));
        long directorySize = test.getDirectorySize(new File("D:\\学习\\电子书"));
        System.out.println("接下来是多少G：");
        System.out.println((double)(directorySize/1024/1024/1024));//G
        //test.deleteDirectory(new File("D:\\test"));

    }

    public void fileGetMethod(){
        File file=new File("D:\\学习\\test-git\\waiter-service.yml");
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getPath());
        System.out.println(file.getName());
        System.out.println(file.getParent());
        System.out.println(file.length());
        System.out.println(file.lastModified());
        System.out.println(file.list());
        System.out.println(file.listFiles());
    }

    public void testEndWithJpg1(){
        File srcFile=new File("D:\\学习\\电子书");
        String[] list = srcFile.list();
        for (String fileName:list){
            if(fileName.endsWith(".pdf")){
                System.out.println(fileName);
            }
        }
    }
    public void testEndWithJpg2(){
        File srcFile=new File("D:\\学习\\电子书");
        File[] files = srcFile.listFiles();
        for(File file:files){
            if(file.getName().endsWith(".pdf")){
                System.out.println(file.getAbsolutePath());
            }
        }
    }
    //FILE 类提供了两个文件过滤器方法
    //public String[] list(FilenameFilter filter)
    //public File[] listFies(FileFilter filter)
    public void testEndWithJpg3(){
        File srcFile=new File("D:\\学习\\电子书");
        File[] files = srcFile.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".pdf");
            }
        });
        for (File file:files){
            System.out.println(file.getAbsolutePath());
        }
    }
    //打印指定目录下的文件
    public static void printSubFile(File dir){
        File[] subfiles=dir.listFiles();
        for (File f:subfiles){
            if(f.isDirectory()){
                printSubFile(f);
            }else{
                System.out.println(f.getAbsolutePath());
            }
        }
    }
    //获取指定目录下的文件大小
    public long getDirectorySize(File file){
        long size=0;
        if(file.isFile()){
            size+=file.length();
        }else{
            File[] files = file.listFiles();
            for(File f:files){
                size+=getDirectorySize(f);
            }
        }
        return size;
    }
    //删除指定目录
    public void deleteDirectory(File file){
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for(File f:files){
                deleteDirectory(f);
            }
        }
        file.delete();
    }
}


/**
 * File类的常用功能
 *      获取功能
 *          public String getAbsolutePath():获取绝对路径
 *          public String getPath():获取路径
 *          public String getName():获取名称
 *          public String getParent():获取上层文件目录，若无返回null
 *          public long length():获取文件长度，字节数，不能获取目录的长度
 *          public long lastModified():获取最后一次修改的时间，毫秒值
 *              //如下两个方法适用于目录
 *          public String[] list():获取指定目录下的所有文件或文件目录的名称数组
 *          public File[] listFiles():获取指定目录下的所有文件或文件目录的File数组
 *      重命名功能
 *          public boolean renameTo(File dest):把文件重命名为指定的文件路径  相当于把文件从一个路径移动到另一个路径
 *              比如file1.renameTo(file2)
 *                  要想保证返回true，需要保证file1是存在的，且file2不存在
 *      判断功能
 *          public boolean isDirectory():判断是否是文件目录
 *          public boolean isFile():判断是否是文件
 *          public boolean exists():判断是否存在
 *          public Boolean canRead():判断是否可读
 *          public boolean canWrite():判断是否可写
 *          public boolean isHidden():判断是否隐藏
 *      创建功能
 *          public boolean createNewFile():创建文件，若文件存在则不创建，返回false
 *          public boolean mkdir():创建文件目录，如果存在则不创建，如果此文件目录的上层目录不存在，也不创建
 *          public boolean mkdirs():创建文件目录，如果上层文件目录不存在则一并创建
 *              如果创建的目录没有写盘符，则默认在项目路径下
 *      删除功能
 *          public Boolean delete():删除文件或者文件夹
 *              文件中的删除不走回收站
 *              要删除一个目录，请注意目录内不能包含文件或者文件目录
 *
 *      File类并未涉及到读取或写入文件内容的操作，如需相关操作，必须使用IO流来完成
 *      后续file类的对象常会作为参数传递到流的构造器中，指明读取或写入的“终点”
 */