package com.master.chapter10;

import java.util.*;

/**
 * @ClassName: GenericTest
 * @Package: com.master.chapter10
 * @Description:
 * @Datetime: 2023/9/9 19:50
 * @author: ColorXJH
 */
public class GenericTest {
    public static void main(String[] args) {
        DAO<User>dao=new DAO<>();
        dao.save("1001",new User(1001,34,"周杰伦"));
        dao.save("1002",new User(1002,34,"王力宏"));
        dao.save("1003",new User(1003,34,"林俊杰"));
        List<User>list=dao.list();
        list.forEach(System.out::println);
    }
}



class DAO<T>{
    private Map<String,T> map=new HashMap<>();
    public void save(String id,T entity){
        map.put(id,entity);
    }
    public T get(String id){
        return map.get(id);
    }
    public void update(String id,T entity){
        if(map.containsKey(id)){
            map.put(id,entity);
        }
    }
    public List<T> list(){
        ArrayList<T>list=new ArrayList<>();
        //1:
//        Set<Map.Entry<String, T>> entries = map.entrySet();
//        Iterator<Map.Entry<String, T>> iterator = entries.iterator();
//        while (iterator.hasNext()){
//            Map.Entry<String, T> next = iterator.next();
//            list.add(next.getValue());
//        }
        //2:
        Collection<T> values = map.values();
        for (T t:values){
            list.add(t);
        }
        return list;
    }
    public void delete(String id){
        map.remove(id);
    }
}

class User{
    private int id;
    private int age;
    private String name;
    public User(){}
    public User(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && age == user.age && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, age, name);
    }
}