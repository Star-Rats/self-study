package com.jmy.concurrent.lock;

import java.util.concurrent.TimeUnit;

public class SyncLock {

    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(phone::call).start();
        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("1s过去了······");
        new Thread(phone::message).start();

        new Thread(phone::say).start();

    }

}

// 资源类与线程分离 使用匿名内部类解耦
class Phone{
    synchronized void call(){
        try {
            TimeUnit.SECONDS.sleep(4L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("打电话");
    }

    synchronized void message(){
        System.out.println("发短信");
    }

    void say(){
        System.out.println("你锁你的和我没关系···");
    }
}