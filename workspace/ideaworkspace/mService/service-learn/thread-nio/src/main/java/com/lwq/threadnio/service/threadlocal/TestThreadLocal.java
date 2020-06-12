package com.lwq.threadnio.service.threadlocal;

/**
 * @Description:
 * 实现每一个线程都有自己的共享变量 --隔离性
 * @author: LinWeiQi
 */
public class TestThreadLocal {
    public static ThreadLocal<String> t1 = new ThreadLocal<String>();

    public static void main(String[] args) {
        if (t1.get() == null) {
            System.out.println("为ThreadLocal类对象放入值:aaa");
            t1.set("aaa");
        }
        System.out.println(t1.get());//aaa
        t1.remove();
        System.out.println(t1.get());

    }
}
