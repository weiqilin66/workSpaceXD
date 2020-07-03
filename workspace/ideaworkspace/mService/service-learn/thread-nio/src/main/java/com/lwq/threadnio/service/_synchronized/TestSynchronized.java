package com.lwq.threadnio.service._synchronized;

/**
 * @Description: 非静态synchronized取得的锁都是对象锁，而不是把一段代码或方法当做锁
 * @author: LinWeiQi
 */
public class TestSynchronized {
    public String username = "A";
    public String password = "AA";

    synchronized public void setValue(String username, String password) {
        try {
            this.username = username;
            System.out.println("name="+Thread.currentThread().getName()+" set userName 完成");
            Thread.sleep(5000);
            this.password = password;

            System.out.println("setValue method thread name="
                    + Thread.currentThread().getName() + " username="
                    + username + " password=" + password);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //该方法前加上synchronized关键字就同步了
    public void getValue() {
        System.out.println("getValue method thread name="
                + Thread.currentThread().getName() + " username=" + username
                + " password=" + password);
    }

    synchronized public void getSynValue() {
        System.out.println("getValue method thread name="
                + Thread.currentThread().getName() + " username=" + username
                + " password=" + password);
    }
}
