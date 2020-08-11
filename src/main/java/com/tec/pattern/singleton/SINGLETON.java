package com.tec.pattern.singleton;

/**
 * @author sunlei
 * 一是 某个类只能有一个实例；
 * 二是 它必须自行创建这个实例；
 * 三是 它必须自行向整个系统提供这个实例。
 * @Description: 单例模式
 */
public class SINGLETON {
    public static void main(String[] args) {
        Runnable r = () -> {
            String threadName = Thread.currentThread().getName();
            Lazy l = Lazy.getInstance();
//			Hungry l = Hungry.getInstance();
            System.out.println("线程 " + threadName + " hashCode " + l.hashCode());
        };
        for (int i = 0; i < 100; i++) {
            new Thread(r, "").start();
        }
    }
}
