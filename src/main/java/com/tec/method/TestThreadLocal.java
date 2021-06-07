package com.tec.method;

public class TestThreadLocal {
    //本线程的变量值
    static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    public static void main(String[] args) throws InterruptedException {
        threadLocal.set(2021);
        for (int i = 0; i < 10 ; i++) {
            final int flag = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    threadLocal.set(flag);
                    System.out.println(Thread.currentThread().getName()+":"+threadLocal.get());
                }
            }, String.valueOf(i)).start();
        }
        //主线程值与子线程值互不干扰
        System.out.println(threadLocal.get());
    }

}
