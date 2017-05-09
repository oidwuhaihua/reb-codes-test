package com.reb.nio;

import java.io.*;
import java.net.Socket;

/**
 * Created by rebby on 2017/5/9.
 */
public class SocketTest {
    public static void main(String[] args) throws IOException, InterruptedException {


        Socket socket  = new Socket("127.0.0.1",1234);

        //读取服务器端数据
        DataInputStream input = new DataInputStream(socket.getInputStream());
        //向服务器端发送数据
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        System.out.print("请输入: \t");
        String str = new BufferedReader(new InputStreamReader(System.in)).readLine();
        out.writeUTF(str);

        String ret = input.readUTF();
        System.out.println("服务器端返回过来的是: " + ret);
        // 如接收到 "OK" 则断开连接
        if ("OK".equals(ret)) {
            System.out.println("客户端将关闭连接");
            Thread.sleep(500);

        }

        out.close();
        input.close();
    }
}
