package com.lwq.threadnio.mythread._daemon;

/**
 * @Description: setDaemon(true)设置守护线程 在start前设置
    在守护线程中产生的新线程也是守护线程
    不是所有的任务都可以分配给守护线程来执行，比如读写操作或者计算逻辑
 */
public class TestDeamon {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new MyThread();
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(5000);
        //
        System.out.println("main线程结束 守护线程跟随jvm虚拟机结束");
    }
}
