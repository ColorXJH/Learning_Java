package com.master.chapter03;

/**
 * @ClassName: TemplateTest
 * @Package: com.master.chapter03
 * @Description:
 * @Datetime: 2023/8/14 20:16
 * @author: ColorXJH
 */
public class TemplateTest {
    public static void main(String[] args) {
        Template template=new SubTemplate();
        template.spendTime();
    }
}

abstract class Template{
    /**
     * Description:  计算某段代码执行所花费的时间
     * @Author: ColorXJH
     * @Date: 2023/8/14 20:20
     * @param
     * @Return: void
     **/
    public void spendTime(){
        long start=System.currentTimeMillis();
        code();
        long end=System.currentTimeMillis();
        System.out.println("花费的时间为："+(end-start));
    }

    public abstract void code();
}

class SubTemplate extends Template{

    @Override
    public void code() {
        for (int i=2;i<1000;i++){
            boolean flag=true;
            for(int j=2;j<=Math.sqrt(i);j++){
                if(i%j==0){
                    flag=false;
                    break;
                }
            }
            if(flag){
                System.out.println(i);
            }
        }
    }
}