package com.master.chapter02;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2023-08-08 14:54
 */
public class CloneTest {
    public static void main(String[] args) {
        Animal a1=new Animal();
        a1.setName("Color");
        try {
            Animal a2=  (Animal) a1.clone();
            System.out.println(a2);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

}

class Animal implements Cloneable{
    private String name;

    public Animal() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                '}';
    }
}
