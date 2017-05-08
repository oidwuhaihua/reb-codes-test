package com.reb.comm;


import java.io.*;
import java.net.Socket;

/**
 * Created by rebby on 2017/1/9.
 */
@SuppressWarnings("all")
public class ServerSocket {



    public static void main(String[] args) {
        try {

            /**
             * 0.0.0.0在本上下文中意味着“本地机器上的所有IP地址”（实际上可能是“本地机器上的所有IPv4地址”）。因此，如果您的Web服务器机器有两个IP地址，192.168.1.1和10.1.2.1，并且您允许像apache这样的webserver守护程序在0.0.0.0上侦听，它将在这两个IP上都可以访问。但只有什么可以联系这些IP和网络端口。

             注意，在不同的上下文（路由）0.0.0.0通常意味着默认路由（到“其余的”互联网的路由，除了本地网络中的路由等）。
             */
            java.net.ServerSocket server = new java.net.ServerSocket(8888);
            System.out.println("server start is ok....");
            //阻塞客户端请求
          Socket socket =   server.accept();

            String line = null;
            //读取客户端信息
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //输出客户客户端信息
            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Clinet:"+in.readLine());

            line = in.readLine();

            while(!line.equals("end")){
                writer.println(line);
                writer.flush();
                System.out.println("Server:" + line);
                System.out.println("Clinet:" + in.readLine());
                line = br.readLine();
            }
            br.close();
            writer.close();
            in.close();
            socket.close();
            server.close();














        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
