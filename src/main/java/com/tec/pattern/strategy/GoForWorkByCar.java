package com.tec.pattern.strategy;


public class GoForWorkByCar implements GoForWork {
    @Override
    public void gotoWork() {
        System.out.println("go for work by car!");
    }
}
