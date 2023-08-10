package com.master.chapter02;

/**
 * @ClassName: BlockTest
 * @Package: com.master.chapter02
 * @Description:
 * @Datetime: 2023/8/10 21:31
 * @author: ColorXJH
 */
public class BlockTest {

    public static void main(String[] args) {
        String ad=BlockTest.AD;
        System.out.println(AD);
        BlockTest test=new BlockTest();
        System.out.println(test.getName());
    }


    {
        name="block-color";
        System.out.println("block--system");
    }
    private String name="xjh";

    //静态属性的值是多少取决于静态代码块与静态属性的位置，以最下方为准  静态优先于非静态执行  静态只能调用静态 非静态可以调用静态和非静态
    static {
        System.out.println("static block--system");
        AD="static advantage";
    }
    private static String AD="advantage";
    static {
        System.out.println("static block--system");
        AD="static advantage after";
    }


    public BlockTest(){
        System.out.println("construct--system");
       // this.name="cons-color";
    }
    //非静态代码块与属性的执行顺序要永远早于构造器，前两只之间的执行顺序从上向下，以最下方为准
    {
        name="block-color-after";
        System.out.println("block--system-after");
    }

    public String getName(){
        return this.name;
    }

    //静态代码块的作用：常用于创建数据库连接池，然后获取链接
        //1：创建数据库连接池属性 database
        //2：静态代码块获取数据库连接池 init database
        //3：静态方法获取数据库链接 conn=db.getCon...
    //由父及子， 静态先行


}