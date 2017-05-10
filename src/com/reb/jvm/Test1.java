package com.reb.jvm;

import java.io.IOException;

/**
 * jps -lvm | grep Test1
 * jmap -histo 7554 | head -30
 * Created by rebby on 2017/3/6.
 */
public class Test1 {
    public static void main(String[] args) throws IOException {
       // String src = "1234343242";1936 46464
       String src = "12343432421212321321";
        src= "12345343232121231321";
        src= "12345343232121231321";
        src= "12345343232121231321";
        src= "123453432321212313211234";
        System.out.println(src);
        System.in.read();
    }
}
