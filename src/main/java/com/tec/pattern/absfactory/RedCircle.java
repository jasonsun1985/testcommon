package com.tec.pattern.absfactory;

/**
 * @author sunlei
 * @Description: 具体颜色的Circle实现
 */
public class RedCircle extends Circle {
    @Override
    public void draw() {
        System.out.println("画红色圆");
    }
}
