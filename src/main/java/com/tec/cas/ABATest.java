package com.tec.cas;

/**
 * @author JASON
 * @version 1.0
 * @Description
 * @date 2021/4/27 13:55
 */
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABATest {

    private static AtomicInteger atomicInt = new AtomicInteger(100);

    private static AtomicStampedReference<Integer> atomicStampedRef = new AtomicStampedReference<Integer>(100, 0);

    public static void main(String[] args) throws InterruptedException {

        Thread intT1 = new Thread(new Runnable() {

            @Override
            public void run() {
                atomicInt.compareAndSet(100, 101);
                atomicInt.compareAndSet(101, 100);
            }

        });

        Thread intT2 = new Thread(new Runnable() {

            @Override

            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {

                }
                boolean c3 = atomicInt.compareAndSet(100, 101);
                System.out.println("intT2"+c3); // true
            }
        });

        intT1.start();
        intT2.start();
        intT1.join();
        intT2.join();
        Thread refT1 = new Thread(new Runnable() {

            @Override
            public void run(){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                }
                System.out.println("refT1 1): "+atomicStampedRef.compareAndSet(100, 101, atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1));
                System.out.println("refT1 1): "+atomicStampedRef.compareAndSet(101, 100, atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1));
            }

        });

        Thread refT2 = new Thread(new Runnable() {

            @Override
            public void run() {

                int stamp = atomicStampedRef.getStamp();

                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                }
                boolean c3 = atomicStampedRef.compareAndSet(100, 101, stamp, stamp + 1);
                System.out.println("refT2"+c3); // false
            }
        });

        refT1.start();
        refT2.start();
        System.out.println(atomicStampedRef.getStamp());
    }
}