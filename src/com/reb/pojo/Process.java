package com.reb.pojo;

/**
 * Created by rebby on 2017/1/5.
 */
@SuppressWarnings("all")
public class Process {
    //进程名称
    private String proName;
    //进程状态
    private String proStatus;
    //等待原因
    private String proWait;

    //进程程序存放位置
    private String proOrderLocation;
    //进程数据存放位置
    private String proDataLocation;
    //通用寄存器内容
    private String proCurrency;
    //控制寄存器内容
    private String proControl;
    //程序状态字寄存器内容
    private String proPWS;
    //进程优先数
    private String proPriority;
    //队列指针
    private String proQueue;

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProStatus() {
        return proStatus;
    }

    public void setProStatus(String proStatus) {
        this.proStatus = proStatus;
    }

    public String getProWait() {
        return proWait;
    }

    public void setProWait(String proWait) {
        this.proWait = proWait;
    }

    public String getProOrderLocation() {
        return proOrderLocation;
    }

    public void setProOrderLocation(String proOrderLocation) {
        this.proOrderLocation = proOrderLocation;
    }

    public String getProDataLocation() {
        return proDataLocation;
    }

    public void setProDataLocation(String proDataLocation) {
        this.proDataLocation = proDataLocation;
    }

    public String getProCurrency() {
        return proCurrency;
    }

    public void setProCurrency(String proCurrency) {
        this.proCurrency = proCurrency;
    }

    public String getProControl() {
        return proControl;
    }

    public void setProControl(String proControl) {
        this.proControl = proControl;
    }

    public String getProPWS() {
        return proPWS;
    }

    public void setProPWS(String proPWS) {
        this.proPWS = proPWS;
    }

    public String getProPriority() {
        return proPriority;
    }

    public void setProPriority(String proPriority) {
        this.proPriority = proPriority;
    }

    public String getProQueue() {
        return proQueue;
    }

    public void setProQueue(String proQueue) {
        this.proQueue = proQueue;
    }
}
