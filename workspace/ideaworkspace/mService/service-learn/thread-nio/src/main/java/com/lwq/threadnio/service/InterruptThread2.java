package com.lwq.threadnio.service;

import org.springframework.stereotype.Service;

/**
 * @Description:
 * @author: LinWeiQi
 */
@Service
public class InterruptThread2 extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 500000; i++) {
            if (this.interrupted()) {
                System.out.println("已经是停止状态了!我要退出了!");
                break;
            }
            System.out.println("i=" + (i + 1));
        }
        System.out.println("看到这句话说明线程并未终止------");
    }
//for循环虽然停止执行了，但是for循环下面的语句还是会执行，说明线程并未被停止。
    public static void main(String[] args) {
        try {
            InterruptThread2 thread = new InterruptThread2();
            thread.start();
            Thread.sleep(2000);
            thread.interrupt();
        } catch (InterruptedException e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
    }

}
