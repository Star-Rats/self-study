package com.jmy.leetcode;

import java.util.ArrayList;
import java.util.List;

public class PositionsofLargeGroups {
   /* public static void main(String[] args) {
        *//*String s = "babaaaabbb";
        System.out.println(largeGroupPositions(s));*//*
        String a = "hello";
        String b = "hello";
        System.out.println(a==b);
        String c = new String("hello");
        System.out.println(a==c);
        StringBuffer d = new StringBuffer("hello");
        System.out.println(a==d.toString());
        String e = "he" + "llo";
        System.out.println(b==e);

    }*/

    public static void main(String[] args) {
        System.out.println(B.c);
    }





    public static List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> result = new ArrayList();
        List<Integer> bigGroup = new ArrayList();
        char ch = ' ';
        int sum = 0;
        for( int i=0; i<s.length(); i++){
            if(i > 0){
                ch=s.charAt(i-1);
            }else{
                ch=s.charAt(i);
            }


            char c = s.charAt(i);
            if(ch == c && i > 0){
                sum++;
            }else{
                if(sum >= 2){
                    bigGroup.add(0,i-1-sum);
                    bigGroup.add(1,i-1);
                    result.add(bigGroup);
                }
                sum = 0;
            }

        }

        if (sum > 1 && sum == s.length() - 1) {
            List<Integer> bg = new ArrayList();
            bg.add(0);
            bg.add(sum);
            result.add(bg);
        } else if (sum > 1) {
            List<Integer> bg = new ArrayList();
            bg.add(s.length() - 1 -sum);
            bg.add(s.length() - 1);
            result.add(bg);
        }
        return result;
    }
}

class A {
    static {
        System.out.println("A");
    }
}

class B extends A {
    static {
        System.out.println("B");
    }

    public static String c = new String("C");
}
