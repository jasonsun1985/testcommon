package com.tec.pattern.strategy;


public class GoForWorkByBike implements GoForWork {
    @Override
    public void gotoWork() {
        System.out.println("go for work by bike!");
    }
}
