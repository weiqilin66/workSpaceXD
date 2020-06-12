package com.lwq.threadnio.service.wait_notify;

/**
 * @Description:
 * @author: LinWeiQi
 */
public class run {
    public static void main(String[] args) {
        try {
            Object lock = new Object();

            ThreadA a = new ThreadA(lock);
            a.start();

            Thread.sleep(50);

            ThreadB b = new ThreadB(lock);
            b.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
