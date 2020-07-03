package com.lwq.threadnio.service._thread;

/**
 * @Description:
 * @author: LinWeiQi
 */
public class MyThread1 extends Thread {
    @Override
    public void run() {
        super.run();
        System.out.println("MyThread1 run priority=" + this.getPriority());
        MyThread2 thread2 = new MyThread2();
        thread2.start();
    }
}
