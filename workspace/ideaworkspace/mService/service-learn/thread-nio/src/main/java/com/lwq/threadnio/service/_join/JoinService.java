package com.lwq.threadnio.service._join;

/**
 * @Description: 主线程需要等待子线程执行完成之后再结束
join(long millis)、join(long millis, int nanos)两个具有超时特性的方法。
这两个超时方法表示，如果线程thread在指定的超时时间没有终止，那么将会从该超时方法中返回。
 * Thread.sleep(2000)不会释放锁，threadTest.join(2000)会释放锁 。
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
