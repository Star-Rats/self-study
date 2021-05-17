package com.jmy.leetcode;

// 懒汉式
/*
public class Singleton {
    private static Singleton instance;
    private Singleton (){}

    */
/**
     * 懒汉式
     * 同步方法保证线程安全
     * @return
     *//*

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}*/

// 饿汉式
public class Singleton {
    // 类加载机制保证线程安全
    private static Singleton instance = new Singleton();
    private Singleton (){}
    public static Singleton getInstance() {
        return instance;
    }
}
