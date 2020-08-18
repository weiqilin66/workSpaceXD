package com.lwq.threadnio.mythread._synchronized;

/**
 * @Description:
 * @author: LinWeiQi
 */
public class MyThread extends Thread {
    private TestSynchronized obj;

    public MyThread(TestSynchronized obj) {
        super();
        this.obj = obj;
    }
    //设置 对象的属性值
    @Override
    public void run() {
        super.run();
        obj.setValue("B", "BB");
    }
}
