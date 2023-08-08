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
## 7 面向对象
    1：封装和隐藏
    2：四种权限修饰符 private 缺省 protected public  类只能使用public 缺省 abstract final 修饰符
        四种权限修饰符可以修饰：类以及类的内部结构：属性 方法 构造器 内部类
    3：一旦提供了形参构造器，系统就不会显初始化默认的无参构造器了
### 8 属性的赋值过程
    1：默认初始化 （各种类型默认的初始化值）
    2：显式初始化 （属性后面直接赋值）
    3：构造器中初始化
    4：通过对象.属性  对象.方法 赋值
        默认的构造器的权限和类的权限一致
### 9 import
    使用的类或接口时本包或者java.lang包下定义的，则可以省略import导入
    使用不同包的同类名，需要写全类名
    import static:导入指定类、接口中的静态结构
    抽象类也有构造器，任何类都有构造器
    javabean需要public类，无参构造器，属性、get/set方法
### 10 方法重写
    父子同方法名称，参数列表
    子类重写的方法的返回值不能大于父类方法返回值，如果是void,则必须返回void,基本数据类型则必须相同
    子类方法的访问权限必须大于或等于父类方法
    private方法不能被重写
    子类方法抛出的异常不能大于父类被重写方法的异常
    static方法属于类的，不能被重写（同private情况类似，重写无效果）
### 11 super与this
    this:当前对象
    super:当前对象的父对象
    this与super同时在构造方法时，都必须在最前面，所以调用构造器时只能二选一
    子类构造器中默认首行是super();子类至少有一个构造器中使用了super()，不可能全部是this()
### 12 子类对象实例化全过程
    从结果上来看就是继承性，获取了父类中声明的属性和方法
    创建子类的对象，在堆空间中，就会加载所有父类中声明的属性
    从过程来看，通过子类构造器，一直向上调用父类构造器初始化属性以及方法
    虽然调用了很多父类的构造器，但是只是创建了一个子类对象
### 13 多态
    对象的多态性，父类引用指向子类对象
    多态的使用：虚拟方法调用：编译器以左边为主，执行期实际执行右边子类重写的方法
    多态性不适用于属性
### 14 toString()
    String Date File 包装类都重写了Object的toString()方法
    默认getClass()+"@"+Integer.toHexString(hashCode())
### 15 equals
    基本数据类型比较实用== 比较值的大小是否相等，不需要类型一致，可以存在类型提升
    应用数据类型比较默认使用==,自己重写该Object类的equals()方法，比较他们的数据大小
    注意在判断是否是同一个类时，一般使用 getClass()==0.getClass()会比较安全
    不建议使用a instanceof ClassB 这样会存在父子类型，如果子类重写了了equals()方法（直接返回false）
    会导致父类equals子类为true,子类equals父类为false,而且夸类的比较返回true也不合适
    简直直接使用idea默认生成的equals方法