package com.tec.cas;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 解决ABA问题
 */
public class ABADemo2 {

    static AtomicStampedReference<Integer> reference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {

        new Thread(() -> {
            int stamp = reference.getStamp();
            System.out.println("第1次版本号:" + stamp);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //使用stamp 替代reference.getStamp() 能解决ABA问题
            boolean b1 = reference.compareAndSet(100, 101, reference.getStamp(), reference.getStamp() + 1);
            System.out.println("第2次版本号:" + reference.getStamp() + " 值为： " + reference.getReference()+"  更新结果：" + b1);
            boolean b2 = reference.compareAndSet(101, 100, reference.getStamp(), reference.getStamp() + 1);
            System.out.println("第3次版本号:" + reference.getStamp() + " 值为： " + reference.getReference()+"  更新结果：" + b2);
        }, "t3").start();

        new Thread(() -> {
            int stamp = reference.getStamp();
            System.out.println("第1次版本号:" + stamp);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //由于原有值100上面已经被修改为101，所以不会修改为2019
            boolean result = reference.compareAndSet(101, 2019, reference.getStamp(), reference.getStamp() + 1);
            System.out.println("版本号："+reference.getStamp());
            System.out.println(Thread.currentThread().getName() + "修改是否成功:" + result + "\t当前最新实际版本号:" + reference.getStamp() + " 值为： " + reference.getReference());
            System.out.println(Thread.currentThread().getName() + "\t当前实际最新值:" + reference.getReference() + " 值为： " + reference.getReference());
        }, "t4").start();

    }
}
