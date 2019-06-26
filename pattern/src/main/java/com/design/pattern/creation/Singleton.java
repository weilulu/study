package com.design.pattern.creation;

/**
 * @Author:weilu
 * @Date: 2019/5/6 21:10
 * @Description: 单例模式,一个系统中只能有一个实例
 * 三个要点：
 * 一、某个类只能有一个对象
 * 二、它必须自行创建这个实例
 * 三、它必须自行向整个系统提供这个实例
 *
 * 模式中包含的角色：
 * 一、Singleton：单例
 */
public class Singleton {

    private static Singleton singleton = new Singleton();
    private Singleton(){}
    public static Singleton getSingleton(){
        return singleton;
    }
}
