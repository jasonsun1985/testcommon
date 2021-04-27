package com.tec.pattern.status.order;

/**
 * @author JASON
 * @version 1.0
 * @Description
 * @date 2021/4/23 18:35
 */
public class InitStatus extends Status {
    @Override
    void init() {
        System.out.println("订单初始化操作开始");
    }

    @Override
    void toBePaid() {
        System.out.println("订单还在初始化，无法进行支付");
    }

    @Override
    void completed() {
        System.out.println("订单还在初始化，无法完成");
    }
}
