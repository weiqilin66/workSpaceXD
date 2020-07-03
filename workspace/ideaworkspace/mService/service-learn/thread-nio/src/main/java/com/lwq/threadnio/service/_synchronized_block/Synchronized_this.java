package com.lwq.threadnio.service._synchronized_block;

/**
 * @Description: 同步代码块 当一个同步方法执行比较久时可以使用代码块优化
 * 同步synchronized方法获得的是对象(实例)锁
 * synchronized(this) 同步语句块 是对象锁
 * @author: LinWeiQi
 */
public class Synchronized_this {

    public void t1(Object object){
        /**
         * 同步语句块(this)             --> this其实是Synchronized_this类实例 所以锁的是当前对象实例
         * 当一个线程访问一个对象的synchronized同步代码块时，另一个线程任然可以访问该对象非synchronized同步代码块。
         * 执行到同步语句块时 阻塞
         */

        String privateGetData1 = "长时间处理任务后从远程返回的值1 threadName="
                + Thread.currentThread().getName();
        String privateGetData2 = "长时间处理任务后从远程返回的值2 threadName="
                + Thread.currentThread().getName();
        // 另一个线程任然可以访问该对象synchronized同步代码块之前阻塞
        synchronized (this) {
            String s1 = privateGetData1;
            String s2 = privateGetData2;
        }
    }

}
