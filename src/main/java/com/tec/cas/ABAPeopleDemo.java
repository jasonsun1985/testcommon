package com.tec.cas;


import com.alibaba.fastjson.JSON;
import com.tec.method.People;
import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 解决ABA问题
 */
public class ABAPeopleDemo {
    static final int DEFAULT_NUM = 100;
    static final int DEFAULT_STAMP = 1;
    static AtomicStampedReference<Integer> reference = new AtomicStampedReference<>(DEFAULT_NUM, DEFAULT_STAMP);

    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("ABAPeopleDemo 耗时");
        new Thread(() -> {
            int stamp = reference.getStamp();
            System.out.println("第1次版本号:" + stamp);
            People p = new People();
            boolean b1 = reference.compareAndSet(100, 101, reference.getStamp(), reference.getStamp() + 1);
            System.out.println("T1" + b1);
            if (b1) {
                People p1 = new People("jason", 10);
                System.out.println(Thread.currentThread().getName()+ JSON.toJSON(p1));
                reference.set(DEFAULT_NUM, 1);
            }
        }, "T1").start();
        new Thread(() -> {
            People p = new People();
            boolean b1 = reference.compareAndSet(100, 101, reference.getStamp(), reference.getStamp() + 1);
            System.out.println("T2" + b1);
            if (b1) {
                People p1 = new People("jason", 10);
                System.out.println(Thread.currentThread().getName()+ JSON.toJSON(p1));
                reference.set(DEFAULT_NUM, 1);
            }
        }, "T2").start();
        new Thread(() -> {
            People p = new People();
            boolean b1 = reference.compareAndSet(100, 101, reference.getStamp(), reference.getStamp() + 1);
            System.out.println("T3" + b1);
            if (b1) {
                People p1 = new People("jason", 10);
                System.out.println(Thread.currentThread().getName()+ JSON.toJSON(p1));
                reference.set(DEFAULT_NUM, 1);
            }
        }, "T3").start();
        TimeUnit.SECONDS.sleep(3);
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
        System.out.println(stopWatch.getTotalTimeSeconds());
        System.out.println("最终信息为：" + reference.getReference() + "  " + reference.getStamp());
    }
}
