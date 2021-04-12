package com.tec.java8.growing;

/**
 * 默认方法
 *
 * @author biezhi
 * @date 2018/2/8
 */
@FunctionalInterface
public interface FunctionalDefaultMethods {

    void method();

    default void defaultMethod() {
    }
}