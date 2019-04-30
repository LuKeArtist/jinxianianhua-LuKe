package com.jinnian.channel.controller;

/**
 * @author liuqi
 * @date 2019/4/16 22:12
 * 学生实体类
 */
public class Sun {

    //--->名字、生命、攻击、怒气值

    String name;
    int life;
    int attack;
    int anger;

    //普通攻击---3

    public void pA(Sun p) {
        p.life -= attack;
        anger += 3;
    }

    //元气弹---5

    public void Yuan(Sun p) {
        p.life -= attack * 5;
        anger -= 5;
    }

    //龟派气功--->10

    public void Gui(Sun p) {
        p.life -= attack * 10;
        anger -= 10;
    }


}
