# note
## 1 dos删除非空文件夹小技巧
    del team1
    rd team1
## 2 test
    javac 编译带包结构的文件为.class文件执行时候报错:没找到主类：
    java代码有package，所以不能在编译出来的class同级目录执行java HelloWorld
    需要java com.master.chapter01.Helloworld 这样执行才行
## 3 文档注释
    文档注释/** */ 可以被javadoc 命令所生成获取  javadoc -d 文件名 -author -version xxx.java
    [a,b] :（int）(Math.random()*(b-a+1)+a)
## 4 局部变量
    没有默认初始化值，调用之前一定要显示赋值，形参在调用时显式赋值
## 5 类属性
    默认初始化值：整型：byte short int long 0
               浮点型： float double 0.0
               字符型：char 0 '\u0000' char在java实现的时候也是按照int类型来处理的，占用两个字节
               布尔型 false 
               引用数据类型：String这样的类，数组，接口 null
    属性加载到堆内存，局部变量加载到虚拟机栈中，static属性加载到方法区
## 6 方法的声明
    权限修饰符 方法修饰符 方法返回值 方法名(形参列表){方法体}
    public protected 缺省（default） private
    abstract final static
    数据类型 void
    
