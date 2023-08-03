package com.master.chapter02;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2023-08-03 10:29
 */
public class InstanseTest {
    public static void main(String[] args) {
        Instance instance=new Instance();
        instance.play();
        //匿名对象
        new Instance().play();
        new Persons(){
            @Override
            public void playGames() {
                System.out.println("我想打游戏");
            }
        };
    }
}
class Instance{
    private String name;
    private String band;
    public void play(){
        System.out.println("play-game");
    }
}
interface Persons{
    void playGames();
}