package com.jmy.concurrent.copyonwrite;

import java.util.concurrent.CopyOnWriteArrayList;

public class SyncCollection {
    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> integers = new CopyOnWriteArrayList<>();
        integers.add(1);
        System.out.println(integers.get(1));
    }
}
