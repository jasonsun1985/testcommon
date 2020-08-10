package com.tec.pattern.strategy;


public class TestStrategy {
    public static void main(String[] args) {
        GoForWorkByCar goForWorkByCar = new GoForWorkByCar();
        Context context = new Context();
        context.setContext(goForWorkByCar);
        context.go();
    }
}
