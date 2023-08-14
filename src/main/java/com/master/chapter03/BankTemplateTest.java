package com.master.chapter03;

/**
 * @ClassName: BankTemplateTest
 * @Package: com.master.chapter03
 * @Description:
 * @Datetime: 2023/8/14 20:29
 * @author: ColorXJH
 */
public class BankTemplateTest {
    public static void main(String[] args) {
        BankTemplateMethod method1=new DrawMoney();
        BankTemplateMethod method2=new ManageMoney();
        method1.process();
        method2.process();
    }
}
abstract class BankTemplateMethod{
    public void takeNumber(){
        System.out.println("排队取号");
    }
    public abstract void transact();//办理具体的业务，钩子方法
    public void evaluate(){
        System.out.println("反馈评分");
    }

    //模板方法，把基本操作组合到一起，子类一般不能重写
    public final void process(){
        takeNumber();
        transact();
        evaluate();
    }
}

class DrawMoney extends BankTemplateMethod{

    @Override
    public void transact() {
        System.out.println("我要取款");
    }
}

class ManageMoney extends BankTemplateMethod{

    @Override
    public void transact() {
        System.out.println("我要理财");
    }
}