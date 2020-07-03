package com.lwq.threadnio.service._synchronized_block;

/**
 * @Description: 同步代码块 当一个同步方法执行比较久时可以使用代码块优化
 * 同步synchronized方法获得的是对象(实例)锁
 *  静态(static)同步(synchronized)方法与 synchronized(class)代码块持有的锁一样，都是Class锁，Class锁对对象的所有实例起作用。
 *  synchronized关键字加到非static静态方法上持有的是对象锁
 * @author: LinWeiQi
 */
public class Synchronized_object {
    /**
     * 同步代码块(object) 锁的是object类
     * object为对象监视器 线程使用了同一个“对象监视器”,所以运行结果是同步的
     * 线程类的构造方法中加入一个随意obj对象 同一个对象则是同步 ,不同obj监视器,不同步
     * obj最好不要String 因为字符串存在常量池 两个相同的字符串指向同一个地址 会获得相同的锁 导致只有一个线程可以运行
     */
    public void t2(MyObject object){

        synchronized (object){//MyObject上锁
            try {
                System.out.println("testMethod1 ____getLock time="
                        + System.currentTimeMillis() + " run ThreadName="
                        + Thread.currentThread().getName());
                Thread.sleep(2000);
                System.out.println("testMethod1 releaseLock time="
                        + System.currentTimeMillis() + " run ThreadName="
                        + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("同对象监视器");
        Synchronized_object s1 = new Synchronized_object();
        MyObject object = new MyObject();
        MyThread t1 = new MyThread(s1,object);
        MyThread t2 = new MyThread(s1,object);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("(测试join)当前运行线程为"+Thread.currentThread().getName());
        System.out.println("不同对象监视器");//不同监视对象不阻塞了
        MyObject object2 = new MyObject();
        MyThread t13 = new MyThread(s1,object);
        MyThread t23 = new MyThread(s1,object2);
        t13.start();
        t23.start();
    }
}
