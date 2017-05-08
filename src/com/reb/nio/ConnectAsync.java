package com.reb.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * Created by rebby on 2017/5/4.
 */
@SuppressWarnings("all")
public class ConnectAsync {
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 80 ;

        if (args.length == 2){
            host = args [0];
            port = Integer.parseInt(args[1]);
        }
        InetSocketAddress addr = new InetSocketAddress(host,port);
        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(false);
        System.out.println("initiating connection");
        //sc.connect(addr);
        sc.bind(addr);
       /* while (!sc.finishConnect()){
            doSomethingUserFul();
        }*/
       boolean flag = sc.isConnected();
        System.out.println(flag);
        System.out.println ("connection established");

       // sc.close( );


    }
    private static void doSomethingUserFul(){
        System.out.println("doing something useless");
    }
}
