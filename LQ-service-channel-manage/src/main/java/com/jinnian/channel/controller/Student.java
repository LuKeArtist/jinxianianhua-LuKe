package com.jinnian.channel.controller;


/**
 * @author liuqi
 * @date 2019/4/16 21:42
 * 学生实体类
 */

public class Student {
    private String name;
    private int age;

    //get/set方法

    public void setAge(int age) {
        while (true) {
            if (age < 0 || age > 150) {
                System.out.println("您的年龄输入有误，请重新输入！");
                continue;
            } else {
                this.age = age;
                break;
            }
        }
    }


    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
