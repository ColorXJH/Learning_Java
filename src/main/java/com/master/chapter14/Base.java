package com.master.chapter14;

import java.io.Serializable;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2023-10-12 8:45
 */
public class Base <T>implements Serializable {
    private char gender;
    public double weight;
    public Base(){}
    private void breath(){
        System.out.println("生物呼吸--");
    }
    public void eat(){
        System.out.println("生物吃东西");
    }
}
