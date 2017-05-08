package com.reb.comm;

import com.reb.pojo.Process;

import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by rebby on 2017/1/5.
 */
@SuppressWarnings("all")
public class ProcessTest {



    public static void main(String[] args) {



        //System.out.println("------------------------------------");
        //创建线程池
        ExecutorService  pool = Executors.newFixedThreadPool(3);

        Thread t1 = new MyProcess();
        Thread t2 = new MyProcess();
        Thread t3 = new MyProcess();
        pool.execute(t1);
       // pool.execute(t2);
       // pool.execute(t3);

        System.out.println(0xFFFF);

    }
    static class MyProcess extends  Thread{

        //进程
        private static volatile  Process  p1 = new Process();


        //进程消费CPU
        public static void executeCPU(String process,Process status){
            status.setProStatus("运行态");
            System.out.println("【"+process+"】进程 正在消费CPU，当前线程状态为："+status.getProStatus());

        }

        @Override
        public void run(){
            while(true) {
                Process p1 = new Process();
                p1.setProStatus("就绪态");

                if (p1.getProStatus().equals("就绪态")) {
                    executeCPU(Thread.currentThread().getName(), p1);

                } else {
                    p1.setProStatus("就绪态");
                    System.out.println("【" + Thread.currentThread().getName() + "】进程当前状态为：" + p1.getProStatus());

                }
            }
        }
    }
}
