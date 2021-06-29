package com.tec.java8.atomic;

import java.util.concurrent.CountDownLatch;

/**
 * @author SUNLEI4
 * @Description
 * @date 2021/6/29
 */
public class Counter2 {
    public volatile static int count = 0;

    public static void inc() {
        try {
            Thread.sleep(1); //延迟1毫秒
        } catch (InterruptedException e) { //catch住中断异常，防止程序中断
            e.printStackTrace();
        }
        count++;//count值自加1
    }

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Counter2.inc();
                    latch.countDown();
                }
            }).start();
        }
        latch.await();
        System.out.println("运行结果：" + Counter2.count);
    }
}
