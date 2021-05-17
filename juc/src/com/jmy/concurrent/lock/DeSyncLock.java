package com.jmy.concurrent.lock;

import java.util.concurrent.TimeUnit;

public class DeSyncLock {

    public static void main(String[] args) {
        DePhone phone1 = new DePhone();
        DePhone phone2 = new DePhone();


        new Thread(phone1::call).start();
        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(phone2::message).start();

    }

}

// 资源类与线程分离 使用匿名内部类解耦
class DePhone{
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
}
