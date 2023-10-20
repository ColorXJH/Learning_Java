package com.master.chapter16;

//引入需要的模块的包下面的类

import com.masters.xjh.bean.Persons;

/**
 * @ClassName: ModuleTest
 * @Package: com.master.chapter16
 * @Description: java9及其以上引用其他模块的类
 * @Datetime: 2023/10/19 23:11
 * @author: ColorXJH
 */
public class ModuleTest {
    public static void main(String[] args) {
        //引入需要的模块的包下面的类，如上方的import
        Persons persons=new Persons("Tom",14);
        System.out.println(persons);
    }

}

/**
 * java9及其以上引用其他模块的类
 *  1：首先各个project jdk必须是jdk9及其以上
 *  2：在项目下创建模块：JAVA9TEST,作为对外暴露方
 *  3: 在项目文件chapter16下像引用JAVA9TEST中的类怎么办
 *         3.1：在JAVA9TEST对外暴露的模块的src目录下创建module-info.java,在其中书写exports  com.masters.xjh.bean(对外暴露的包);
 *         3.2：在项目应用文件夹下（chapter16） 创建module-info.java,在其中书写requires JAVA9TEST(引用需要的模块);
 *         3.3：在项目类ModuleTest中引用外部模块（JAVA9TEST）的类，此时止血药引用其对应的包下面的类就可以：import com.masters.xjh.bean.Persons;
 */