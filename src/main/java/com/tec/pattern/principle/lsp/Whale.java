package com.tec.pattern.principle.lsp;

/**
 * 鲸鱼
 */
public class Whale extends Mammal implements ISwim {

    @Override
    public void swim() {
        System.out.println("鲸鱼游泳");
    }
}
