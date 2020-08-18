package com.lwq.threadnio.mythread._priority;

import com.lwq.threadnio.mythread._thread.MyThread1;

/**
 * @Description: priority继承性 随机性
 * @author: LinWeiQi
 */
public class TestPriority extends Thread {
    public static void main(String[] args) {
        System.out.println("start ---"+Thread.currentThread().getPriority());
        Thread.currentThread().setPriority(6);
        System.out.println("end ---" +Thread.currentThread().getPriority()+ "src/main" +Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getId());
        MyThread1 t1 = new MyThread1();
        t1.start();
        System.out.println(t1.getName());;


    }

}
