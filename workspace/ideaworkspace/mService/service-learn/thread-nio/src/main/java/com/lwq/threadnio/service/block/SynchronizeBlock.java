package com.lwq.threadnio.service.block;

/**
 * @Description:
 *  静态同步synchronized方法与synchronized(class)代码块持有的锁一样，都是Class锁，Class锁对对象的所有实例起作用。
 *  synchronized关键字加到非static静态方法上持有的是对象锁
 * @author: LinWeiQi
 */
public class SynchronizeBlock {

    public String t1(Object object){
        /**
         * 同步语句块
         * 当一个线程访问一个对象的synchronized同步代码块时，另一个线程任然可以访问该对象非synchronized同步代码块。
         * 执行到同步语句块时 阻塞
         */
        synchronized (this){
            //...
        }
        /**
         * 同步代码块
         * 线程类的构造方法中加入一个随意obj对象 同一个对象则是同步 ,不同obj监视器,不同步
         * obj最好不要String 因为字符串存在常量池 两个相同的字符串指向同一个地址 会获得相同的锁 导致只有一个线程可以运行
         * https://blog.csdn.net/qq_34337272/article/details/79670775?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522159097479319725219930497%2522%252C%2522scm%2522%253A%252220140713.130102334.pc%255Fblog.%2522%257D&request_id=159097479319725219930497&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~blog~first_rank_v2~rank_blog_default-5-79670775.pc_v2_rank_blog_default&utm_term=java%E5%A4%9A%E7%BA%BF%E7%A8%8B
         */
        synchronized (object){

        }
        /*public ThreadB(Service service, MyObject object) {
            super();
            this.service = service;
            this.object = object;
        }*/


        return null;
    }
}
