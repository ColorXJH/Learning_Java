package com.master.chapter12;

import java.io.*;
import java.util.Date;

/**
 * @author ColorXJH
 * @version 1.0 文件
 * @description:
 * @date 2023-09-14 16:29
 */
public class ObjectStreamTest {
    public static void main(String[] args) {
        ObjectStreamTest test=new ObjectStreamTest();
        test.testObjectStream();
    }
    public void testObjectStream() {
        //序列化，将内存中的java对象保存至磁盘中/传递到网络==》ObjectOutputStream
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream=null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream("person.data"));
            outputStream.writeObject(new Person("ColorXJH", 29, new Date()));
            outputStream.flush();
            inputStream=new ObjectInputStream(new FileInputStream("person.data"));
            Person person = (Person) inputStream.readObject();
            System.out.println(person);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if(outputStream!=null)
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(inputStream!=null)
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

class Person implements Serializable{

    //序列版本号  自定义异常类也有一个 公有私有都可以,不写会会根据运行时环境类自动生成一个静态变量，但是如果类的结构做了修改
    //则serialVersionUID可能发生变化，故建议显示声明
    private static final long serialVersionUID = 4343435342L;
    //保证其内部所有属性也是可序列化的
    private String name;
    private int age;
    private Date date;
    public Person(){}
    public Person(String name,int age,Date date){
        this.age=age;
        this.name=name;
        this.date=date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", date=" + date +
                '}';
    }
}
/**
 * 对象流:ObjectInputStream ObjectOutputStream
 * 用于存储基本数据类型数据或对象的处理流，可以将对象写入到数据源中
 * 也可以从数据源中读出对象
 * 序列化：用ObjectOutputStream类保存基本类型数据或对象的机制  内存写出到文件
 * 反序列化：用ObjectInputStream类读取基本数据类型或对象的机制   从文件读取到内存
 * 注意：不能序列化和反序列化static和transient修饰的成员变量
 */


/**
 * 序列化机制：将内存中的java对象通过序列化机制以二进制流的形式存储到文本文件或在网络间传输，
 * 其他程序接受这个二进制流将其恢复为java对象
 * 将实现了java的Serializable/Externalizable接口的对象转换为字节数据
 */
