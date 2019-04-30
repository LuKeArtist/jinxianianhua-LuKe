package com.jinnian.channel.controller;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * 游戏测试
 *
 * @author liuqi
 * @date 2019/4/16 22:20
 */
public class Sun_Test {

    public static void main(String[] args) {

        //使用集合
        ArrayList<Sun> arr = new ArrayList<Sun>();
        add(arr);
        fight(arr);

        //要么取经成功，要么取经失败
        while (true) {
            sun(arr);
            if (arr.get(1).life <= 0) {
                System.out.println("银角大王战死，孙悟空获胜！取经成功！");
                break;
            }
            yin(arr);
            if (arr.get(0).life <= 0) {
                System.out.println("孙悟空战死，银角大王获胜！取经失败！");
                break;
            }
        }
    }

    //添加角色
    public static void add(ArrayList<Sun> arr) {
        Sun p1 = new Sun();
        Sun p2 = new Sun();

        p1.name = "孙悟空";
        p1.life = 500;
        p1.attack = 10;
        p1.anger = 0;

        p2.name = "银角大王";
        p2.life = 200;
        p2.attack = 15;
        p2.anger = 0;

        arr.add(p1);
        arr.add(p2);
    }

    //战斗信息
    public static void fight(ArrayList<Sun> arr) {
        //使用随机攻击
        Random r = new Random();
        Scanner sc = new Scanner(System.in);
        System.out.println("西天取经路上，孙悟空偶遇银角大王。一场惊心动魄的战斗就此打响！");
        System.out.println("敌我信息如下：");
        System.out
                .println("孙悟空    血量：" + arr.get(0).life + "   攻击：" + arr.get(0).attack + "    怒气：" + arr.get(0).anger);
        System.out.println("银角大王    血量：" + arr.get(1).life + "   攻击：" + arr.get(1).attack + "    怒气：无");

    }

    public static void sun(ArrayList<Sun> arr) {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("...........轮到孙悟空了..........");
        Random r = new Random();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("轮到你出手了！请选择攻击手段： 1.普通攻击 （0）         2.元气弹（5）                 3.龟派气功（10）");
            int x = sc.nextInt();
            if (x == 1) {
                int y = r.nextInt(3);
                if (y >= 1) {
                    arr.get(0).pA(arr.get(1));
                    System.out.println("你对银角大王进行了一次普通攻击！你的怒气值为" + arr.get(0).anger);
                    print(arr.get(1));
                    break;
                } else {
                    System.out.println("你的攻击miss！你的怒气值为" + arr.get(0).anger);
                    print(arr.get(1));
                    break;
                }
            } else if (x == 2) {
                if (arr.get(0).anger >= 5) {
                    arr.get(0).Yuan(arr.get(1));
                    System.out.println("你对银角大王进行了一次元气弹攻击！你的怒气值为" + arr.get(0).anger);
                    print(arr.get(1));
                    break;
                } else {
                    System.out.println("你的怒气值不足！");
                    continue;
                }
            } else if (x == 3) {
                if (arr.get(0).anger >= 10) {
                    arr.get(0).Gui(arr.get(1));
                    System.out.println("你对银角大王进行了一次龟派气功攻击！你的怒气值为" + arr.get(0).anger);
                    print(arr.get(1));
                    break;
                } else {
                    System.out.println("你的怒气值不足！");
                    continue;
                }
            } else {
                System.out.println("该技能没解锁！");
                continue;
            }
        }
    }

    public static void yin(ArrayList<Sun> arr) {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(".........轮到银角大王了............");
        Random r = new Random();
        int e = r.nextInt(10);
        if (e <= 5) {
            int q = r.nextInt(2);
            if (q == 1) {
                arr.get(1).pA(arr.get(0));
                System.out.println("银角大王对你进行了一次普通攻击！");
                print(arr.get(0));
            } else {
                System.out.println("银角大王的普通攻击miss！");
                print(arr.get(0));
            }
        } else if (e > 5 && e <= 8) {
            arr.get(1).Yuan(arr.get(0));
            System.out.println("银角大王对你进行了一次元气弹攻击！");
            print(arr.get(0));
        } else {
            arr.get(1).Gui(arr.get(0));
            System.out.println("银角大王对你进行了一次龟派气功攻击！");
            print(arr.get(0));
        }
    }

    public static void print(Sun p) {
        System.out.println(p.name + "   血量：" + p.life);

    }

}
