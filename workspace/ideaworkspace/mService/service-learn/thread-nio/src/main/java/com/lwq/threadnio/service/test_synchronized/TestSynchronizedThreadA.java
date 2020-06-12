package com.lwq.threadnio.service.test_synchronized;

/**
 * @Description:
 * @author: LinWeiQi
 */
public class TestSynchronizedThreadA extends Thread {
    private TestSynchronized publicVar;

    public TestSynchronizedThreadA(TestSynchronized publicVar) {
        super();
        this.publicVar = publicVar;
    }

    @Override
    public void run() {
        super.run();
        publicVar.setValue("B", "BB");
    }
}
