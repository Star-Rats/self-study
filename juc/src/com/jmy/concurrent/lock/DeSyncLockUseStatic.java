package com.jmy.concurrent.lock;

import java.util.concurrent.TimeUnit;

public class DeSyncLockUseStatic {
    public static void main(String[] args) {
        DePhoneUseStatic phone1 = new DePhoneUseStatic();
        DePhoneUseStatic phone2 = new DePhoneUseStatic();


        new Thread(() -> phone1.call()).start();
        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> phone2.message()).start();

    }

}

// 资源类与线程分离 使用匿名内部类解耦
class DePhoneUseStatic{
    static synchronized void call(){
        try {
            TimeUnit.SECONDS.sleep(4L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("打电话");
    }

    static synchronized void message(){
        System.out.println("发短信");
    }
}
