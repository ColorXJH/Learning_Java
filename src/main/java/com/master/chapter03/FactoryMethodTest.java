package com.master.chapter03;

/**
 * @ClassName: FactoryMethodTest
 * @Package: com.master.chapter03
 * @Description:
 * @Datetime: 2023/8/15 20:11
 * @author: ColorXJH
 */
public class FactoryMethodTest {
    public static void main(String[] args) {
        Cars cs1=new AudiFactory().getCar();
        Cars cs2=new BYDFactory().getCar();
        cs1.run();
        cs2.run();
    }
}


interface Cars{
    void run();
}

class Audis implements Cars{

    @Override
    public void run() {
        System.out.println("audis在跑");
    }
}
class BYDS implements Cars{

    @Override
    public void run() {
        System.out.println("byds在跑");
    }
}

interface Factory{
    Cars getCar();
}
class AudiFactory implements Factory{

    @Override
    public Cars getCar() {
        return new Audis();
    }
}

class BYDFactory implements Factory{

    @Override
    public Cars getCar() {
        return new BYDS();
    }
}