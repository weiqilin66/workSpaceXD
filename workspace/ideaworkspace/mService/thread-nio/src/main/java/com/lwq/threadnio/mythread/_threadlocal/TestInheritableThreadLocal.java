package com.lwq.threadnio.mythread._threadlocal;

import java.util.Date;

/**
 * @Description: inheritableThreadLocal 子线程获取父线程的值 childValue方法 取父线程值并且可以修改(childValue方法中修改)
 * ThreadLocal类固然很好，但是子线程并不能取到父线程的ThreadLocal类的变量，InheritableThreadLocal类就是解决这个问题的。
 * @author: LinWeiQi
 */
public class TestInheritableThreadLocal {
    // 继承InheritableThreadLocal方式来initialValue初始化,childValue来return值给子线程获取
    static public class inheri extends InheritableThreadLocal{
        @Override
        protected Object initialValue() {
            return new Date().getTime();
        }

        @Override
        protected Object childValue(Object parentValue) {
            return "老子通过子线程获得的值:"+parentValue;
        }
    }

    static public class Tools {
        public static inheri tl = new inheri();//==>子线程get()返回的为childValue返回值
    }
    // 作为子线程获取main(父线程中)
    static public class ThreadA extends Thread{
        @Override
        public void run() {
            System.out.println(Tools.tl.get());
        }
    }

    public static void main(String[] args) {

        Thread.currentThread().setName("thread-main");
        try {
            for (int i = 0; i < 3; i++) {
                System.out.println("       在"+Thread.currentThread().getName()+"线程中取值=" + Tools.tl.get());
                Thread.sleep(100);
            }
            Thread.sleep(1000);
            ThreadA a = new ThreadA();
            a.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
