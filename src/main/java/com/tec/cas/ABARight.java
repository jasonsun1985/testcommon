package com.tec.cas;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author JASON
 * @version 1.0
 * @Description
 * @date 2021/5/6 22:39
 */
public class ABARight {
    /*private AtomicStampedReference<Integer> count = new AtomicStampedReference<Integer>(0, 0);

    public int getCount() {
        return count.getReference();
    }
    public int increment() {
        int[] stamp = new int[1];
        while(true) {
            Integer value = count.get(stamp); //同时获取时间戳和数据，防止获取到数据和版本不是一致的
            int newValue = value + 1;
            boolean writeOk = count.compareAndSet(value, newValue, stamp[0], stamp[0] + 1);
            if(writeOk) {
                return newValue;
            }
        }
    }

    public int decrement() {
        int[] stamp = new int[1];
        while(true) {
            Integer value = count.get(stamp);//同时获取时间戳和数据，防止获取到数据和版本不是一致的
            int newValue = value - 1;
            boolean writeOk = count.compareAndSet(value, newValue, stamp[0], stamp[0] + 1);
            if(writeOk) {
                return newValue;
            }
        }
    }
*/

    private AtomicStampedReference<Integer> count = new AtomicStampedReference<Integer>(0, 0);

    public int getCount() {
        return count.getReference();
    }

    public int increment() {
        while (true) {
            //必须先获取stamp，然后取值，顺序不能反，否则仍然会有ABA的问题
            int stamp = count.getStamp();
            Integer value = count.getReference();
            int newValue = value + 1;
            boolean writeOk = count.compareAndSet(value, newValue, stamp, stamp + 1);
            System.out.println("increment():value- " + value + " newValue-" + newValue + " stamp-" + stamp);
            System.out.println(Thread.currentThread().getName() + " increment() writeOk:" + writeOk+" 最终版本号："+stamp+" count.getStamp():"+count.getStamp());
            if (writeOk) {
                return newValue;
            }
        }
    }

    public int decrement() {
        while (true) {
            //必须先获取stamp，然后取值，顺序不能反，否则仍然会有ABA的问题
            int stamp = count.getStamp();
            Integer value = count.getReference();
            int newValue = value - 1;
            boolean writeOk = count.compareAndSet(value, newValue, stamp, stamp + 1);
            System.out.println("decrement():value- " + value + " newValue-" + newValue + " stamp-" + stamp);
            System.out.println(Thread.currentThread().getName() + " decrement() writeOk:" + writeOk+" 最终版本号："+stamp+" count.getStamp():"+count.getStamp());
            if (writeOk) {
                return newValue;
            }
        }
    }

    public static void main(String[] args) {
        ABARight abaRight = new ABARight();
        new Thread(() -> System.out.println("abaRight.increment(): " + abaRight.increment()), "A").start();
        new Thread(() -> System.out.println("abaRight.increment(): " + abaRight.increment()), "B").start();
        new Thread(() -> System.out.println("abaRight.increment(): " + abaRight.increment()), "C").start();

        new Thread(() -> System.out.println("abaRight.decrement(): " + abaRight.decrement()), "D").start();
        new Thread(() -> System.out.println("abaRight.decrement(): " + abaRight.decrement()), "E").start();
        new Thread(() -> System.out.println("abaRight.decrement(): " + abaRight.decrement()), "F").start();


    }
}
