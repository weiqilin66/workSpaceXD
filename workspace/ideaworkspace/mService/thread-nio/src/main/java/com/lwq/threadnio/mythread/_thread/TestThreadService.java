package com.lwq.threadnio.mythread._thread;

import org.springframework.stereotype.Service;

/**
 * @Description:
 * start()后线程处于就绪状态(runnable),
 * 如果就绪状态线程获得了CPU,开始执行run方法的线程执行体,则该线程处于运行状态(running)
 * 阻塞(blocked)线程由于某些原因放弃了CPU使用权,让出cpu timeslice暂时停止运行
 * 直到线程进入runnable才会机会再次获得cpu timeslice转到运行(running)状态
 * 等待阻塞(o.wait->等待队列)
 * 同步阻塞(lock->锁池)
 * 其他阻塞(sleep,join)
 * 线程死亡(dead)
 * 正常结束,异常结束,stop()结束[可能死锁,不推荐使用]
 *
 * 使用线程池的方式也是最推荐的一种方式
 * 推荐实现Runnable接口方式开发多线程，因为Java单继承但是可以实现多个接口
 * 不推荐继承thread来创建线程
 * new Thread()创建线程 start()开启线程
 * synchronized 可解决单对象实例变量的线程安全问题
 * @author: LinWeiQi
 */
@Service
public class TestThreadService implements Runnable {
    int count = 5;

    @Override
    synchronized public void run() {
        while (count > 0) {
            count--;
            System.out.println(" 计算，count=" + count);
        }
    }

    public static void main(String[] args) {
        TestThreadService t =new TestThreadService();
        Thread thread = new Thread(t);
        Thread thread1 = new Thread(t);
        thread.start();
        thread1.start();
    }
  /* 结果
   计算，count=3
    计算，count=2
    计算，count=3
    计算，count=1
    计算，count=0*/

}
