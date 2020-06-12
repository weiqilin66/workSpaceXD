package com.lwq.threadnio.service.wait_notify;

/**
 * @Description: synchronized(Object) --class锁
 *
 * notify()执行后并不会立即释放锁
 *
 * @author: LinWeiQi
 */
public class ThreadB extends Thread {
    private Object lock;

    public ThreadB(Object lock) {
        super();
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    MyList.add();
                    System.out.println("添加了" + (i + 1) + "个元素!");
                    if (MyList.size() == 5) {
                        lock.notify();
                        System.out.println("已发出通知！");
//                        return;
                    }
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
