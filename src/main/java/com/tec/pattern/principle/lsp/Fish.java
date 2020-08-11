package com.tec.pattern.principle.lsp;

/**
 * 鱼
 */
public class Fish implements ISwim {
    @Override
    public void swim() {
        System.out.println("鱼游泳");
    }
}