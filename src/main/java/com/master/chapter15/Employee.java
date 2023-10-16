package com.master.chapter15;

import java.util.Objects;

/**
 * @ClassName: Employee
 * @Package: com.master.chapter15
 * @Description:
 * @Datetime: 2023/10/16 20:40
 * @author: ColorXJH
 */
public class Employee {
    private int age;
    private String name;
    private double price;
    private  int id;

    public Employee(int id){
        this.id=id;
        System.out.println("构造器被调用了");
    }
    public Employee(int id,String name){
        this.id=id;
        this.name=name;
        System.out.println("构造器被调用了123");
    }

    public Employee(){
        System.out.println("构造器被调用了");
    }
    public Employee(int age, String name, double price, int id) {
        this.age = age;
        this.name = name;
        this.price = price;
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return age == employee.age && Double.compare(employee.price, price) == 0 && id == employee.id && Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name, price, id);
    }
}
