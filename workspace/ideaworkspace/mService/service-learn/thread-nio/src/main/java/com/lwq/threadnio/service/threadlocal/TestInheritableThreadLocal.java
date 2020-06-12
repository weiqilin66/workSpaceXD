package com.lwq.threadnio.service.threadlocal;

import java.util.Date;

/**
 * @Description: inheritableThreadLocal 子线程获取父线程的值
 * childValue方法取父线程值 并且可以修改
 * @author: LinWeiQi
 */
public class TestInheritableThreadLocal {
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
        public static inheri tl = new inheri();
    }
    static public class ThreadA extends Thread{
        @Override
        public void run() {
            System.out.println(Tools.tl.get());
        }
    }
    public static void main(String[] args) {
        try {
            for (int i = 0; i < 3; i++) {
                System.out.println("       在Main线程中取值=" + Tools.tl.get());
                Thread.sleep(100);
            }
            Thread.sleep(5000);
            ThreadA a = new ThreadA();
            a.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
