package com.reb.io;

import java.io.*;
import java.nio.channels.Channel;

/**
 * http://blog.csdn.net/yczz/article/details/38761237
 * Created by rebby on 2017/5/2.
 */
@SuppressWarnings("all")
public class IoTest {

    public static void main(String[] args) {


        byte[] buffer = new byte[512];
        int numberRead = 0;
        FileInputStream input = null;
        FileOutputStream out = null;

        try {
            input = new FileInputStream("/Users/rebby/Documents/WechatIMG2.jpeg");
            out = new FileOutputStream("/Users/rebby/Documents/test/WechatIMG21.jpeg");

           // int buleng =  input.read(buffer);


            while ((numberRead=input.read(buffer))!=-1){
                out.write(buffer,0,numberRead);
            }
            out.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
