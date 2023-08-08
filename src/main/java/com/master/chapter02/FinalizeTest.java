package com.master.chapter02;

import java.util.Objects;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2023-08-08 15:23
 */
public class FinalizeTest {
    public static void main(String[] args) {
        PersonName personName=new PersonName("Color");
        System.out.println(personName);
        //此时对象实体就是垃圾对象，等待回收，但是时间不确定
        personName=null;
        //强制释放空间（并不保证一定立即执行）
        //System.gc();
        Runtime.getRuntime().gc();
        String str1=new String("Color");
        String str2=new String("Color");
        System.out.println(str1==str2);
        String ss1="Color1";
        String ss2="Color1";
        System.out.println(ss1==ss2);
    }
}

class PersonName{
    private String name;
    private String id;

    public PersonName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PersonName{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonName that = (PersonName) o;
        return Objects.equals(name, that.name) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
//子类重写此方法，可在对象被回收前，进行某些操作

    @Override
    protected void finalize() throws Throwable {
        System.out.println("对象被回收：-->"+this);
    }
}