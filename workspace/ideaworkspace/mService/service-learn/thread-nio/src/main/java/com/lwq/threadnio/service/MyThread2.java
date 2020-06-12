package com.lwq.threadnio.service;

/**
 * @Description:
 * @author: LinWeiQi
 */
public class MyThread2 extends Thread {
    @Override
    public void run() {
        System.out.println("MyThread2 run priority=" + this.getPriority());
    }
}
