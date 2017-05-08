package com.reb.comm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by rebby on 2017/1/9.
 */
@SuppressWarnings("all")
public class ClientSocket {

    public static void main(String[] args) {

        try {

            Socket socket = new Socket("127.0.0.1",8888);

            System.out.println("client start .......");

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            //socket发送信息
            PrintWriter writer = new PrintWriter(socket.getOutputStream());


            //socket 读取信息
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String readLine ;
            readLine= br.readLine();

            while(!readLine.equals("end")){
                writer.println(readLine);
                writer.flush();
                System.out.println("Clinet:" + readLine);
                System.out.println("Server:" + in.readLine());

                readLine = br.readLine();
            }
            writer.close();
            in.close();
            br.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
