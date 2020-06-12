package com.lwq.threadnio.service.join;

/**
 * @Description: 主线程需要等待子线程执行完成之后再结束
 * Thread.sleep(2000)不会释放锁，threadTest.join(2000)会释放锁 。
 * @author: LinWeiQi
 */
public class JoinService {
    public static void main(String[] args) throws InterruptedException {

        MyThread threadTest = new MyThread();
        threadTest.start();

        //Thread.sleep(?);//因为不知道子线程要花的时间这里不知道填多少时间
        threadTest.join();
        System.out.println("我想当threadTest对象执行完毕后我再执行");
    }
    static public class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println("我想先执行");
        }

    }
}
