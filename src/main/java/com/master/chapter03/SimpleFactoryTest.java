package com.master.chapter03;

/**
 * @ClassName: SimpleFactoryTest
 * @Package: com.master.chapter03
 * @Description:
 * @Datetime: 2023/8/15 20:01
 * @author: ColorXJH
 */
public class SimpleFactoryTest {
    public static void main(String[] args) {
        Car car1= CarFactory.getCar("audi");
        Car car2= CarFactory.getCar("BYD");
        car1.run();
        car2.run();
    }
}

interface Car{
    void run();
}

class Audi implements Car {

    @Override
    public void run() {
        System.out.println("audi在跑");
    }
}

class BYD implements Car{

    @Override
    public void run() {
        System.out.println("BYD在跑");
    }
}

class CarFactory{
    public static Car getCar(String type){
        if("audi".equals(type)){
            return new Audi();
        }else if("BYD".equals(type)){
            return new BYD();
        }else {
            return null;
        }
    }
}