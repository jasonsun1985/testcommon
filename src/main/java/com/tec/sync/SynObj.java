package com.tec.sync;


public class SynObj {
    public synchronized void showA() {
        System.out.println("showA..");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void showB() {
        synchronized (this) {
            System.out.println("showB..");
        }
    }

    public void showC() {
        String s = "1";
        synchronized (s) {
            System.out.println("showC..");
        }
    }
}
