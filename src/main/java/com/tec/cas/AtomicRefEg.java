package com.tec.cas;


import java.util.concurrent.atomic.AtomicReference;

public class AtomicRefEg {

    static AtomicReference<Person> p = new AtomicReference<>(new Person(20));

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                p.set(new Person(p.get().age + 10));
                //30 40 50
                System.out.println("Atomic Check by first thread: " + Thread.currentThread().getName() + " is " + p.get().age);
            }
        });

        Thread t2 = new Thread(() -> {
            Person per = p.get();
            for (int i = 1; i <= 3; i++) {
                //不会进行年龄增加，因为t1将 p年龄增加到了50，per获取的是原始age20的对象，所以不匹配不更新
                System.err.println(p.get().equals(per) + "_" + per.age + "_" + p.get().age);
                p.compareAndSet(per, new Person(p.get().age + 10));
                System.out.println("Atomic Check by second thread : " + Thread.currentThread().getName() + " is " + p.get().age);
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final value: " + p.get().age);
    }
}

class Person {
    int age;

    public Person(int age) {
        this.age = age;
    }
}

