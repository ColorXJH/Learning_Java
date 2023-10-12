package com.master.chapter14;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2023-10-12 16:41
 */
public class StudentDao extends Dao<Student>{
    //这里就有需求：拿到当前类的父类的范型类型
            //通过这个泛型类型,可以创建一个类的对象，不同的类的对象不同

    public static void main(String[] args) {
        StudentDao test=new StudentDao();
        test.getIndex(1);
    }

    @Override
    public Student getIndex(int index) {
        Class<StudentDao> studentDaoClass = StudentDao.class;
        Type genericSuperclass = studentDaoClass.getGenericSuperclass();
        ParameterizedType parameterizedType=(ParameterizedType) genericSuperclass;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        String typeName = actualTypeArguments[0].getTypeName();
        try {
            //得到javabean类对象-他就是父类的范型类型
            Class<?> aClass = Class.forName(typeName);
            Method show = aClass.getDeclaredMethod("show");

            Object o = aClass.newInstance();
            show.invoke(o);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        return super.getIndex(index);
    }
}
