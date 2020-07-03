package com.lwq.threadnio.service._synchronized_block;

/**
 * @Description: 测试同步代码块
 * @author: LinWeiQi
 */
public class MyThread extends Thread {
    private MyObject object;
    private Synchronized_object synchronized_object;

    public MyThread(Synchronized_object synchronized_object,MyObject object){
        this.object = object;
        this.synchronized_object = synchronized_object;
    }
    @Override
    public void run() {
        synchronized_object.t2(object);
    }
}
