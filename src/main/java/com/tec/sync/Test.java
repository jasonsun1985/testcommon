package com.tec.sync;

public class Test {
    public static void main(String[] args) {
        final SynObj sy = new SynObj();
        new Thread(new Runnable() {

            @Override
            public void run() {
                sy.showA();
            }
        }).start();
        new Thread(new Runnable() {

            @Override
            public void run() {
                sy.showB();
            }
        }).start();
        new Thread(new Runnable() {

            @Override
            public void run() {
                sy.showC();
            }
        }).start();
        //synchronized (this) 和方法级别锁一致，锁整个方法
    }
    /**
     * 结论是A和B用的同一把锁，A执行sleep 3秒，所以B需要等到3秒后才能用
     */
}
