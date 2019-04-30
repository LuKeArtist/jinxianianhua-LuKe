package com.jinnian.channel.controller;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * 点名器
 *
 * @author liuqi
 * @date 2019/4/16 21:24
 */

public class Student_Test {

    public static void main(String[] args) {
        ArrayList<Student> arr = new ArrayList<Student>();
        add(arr);
        print(arr);
        while (true) {
            random(arr);
        }
    }

    public static void add(ArrayList<Student> arr) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            Student s = new Student();
            System.out.println("请输入学生姓名：");
            s.setName(sc.next());
            System.out.println("请输入学生年龄：");
            s.setAge(sc.nextInt());
            arr.add(s);
            System.out.println("是否继续结束输入？ 结束输入请按1");
            if (sc.nextInt() == 1) {
                break;
            } else {
                continue;
            }
        }

    }

    public static void print(ArrayList<Student> arr) {
        for (int i = 0; i < arr.size(); i++) {
            Student p = arr.get(i);
            System.out.println(p.getName() + "  " + p.getAge());
        }
    }

    public static void random(ArrayList<Student> arr) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要点名的人数：");
        int y = sc.nextInt();
        ArrayList<Integer> arr1 = new ArrayList<Integer>();
        Random ran = new Random();
        a:
        for (int i = 0; i < y; i++) {
            int x = ran.nextInt(arr.size());
            for (int j = 0; j < arr1.size(); j++) {
                if (x + 1 == arr1.get(j)) {
                    i--;
                    continue a;
                }
            }
            arr1.add(x + 1);
            System.out.println(arr.get(x).getName());
        }

    }


}
