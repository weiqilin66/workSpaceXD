package com.lwq.threadnio.mythread._lock.reentrantlock_condition;

/**
 * @Description:
 * @author: LinWeiQi
 */
public class MyThread extends Thread {
    private TestConditions testConditions;

    public MyThread(TestConditions testConditions){
        this.testConditions = testConditions;
    };

    @Override
    public void run() {
        testConditions.awaitA();
    }
}
