package com.lwq.threadnio.service;

import org.springframework.stereotype.Service;

/**
 * @Description:
 * 使用线程池的方式也是最推荐的一种方式
 * 推荐实现Runnable接口方式开发多线程，因为Java单继承但是可以实现多个接口
 * 不推荐继承thread
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
