package com.lwq.threadnio.service.test_synchronized;

/**
 * @Description:
 * @author: LinWeiQi
 */
public class run {
        public static void main(String[] args) {
            try {
                TestSynchronized publicVarRef = new TestSynchronized();
                TestSynchronizedThreadA thread = new TestSynchronizedThreadA(publicVarRef);
                thread.start();

                Thread.sleep(200);//打印结果受此值大小影响

                publicVarRef.getValue();
                Thread.sleep(5000);
                publicVarRef.getValue();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
}
