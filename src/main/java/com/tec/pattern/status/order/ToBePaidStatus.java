package com.tec.pattern.status.order;

/**
 * @author JASON
 * @version 1.0
 * @Description
 * @date 2021/4/23 18:40
 */
public class ToBePaidStatus extends Status{

    @Override
    void init() {
        System.out.println("订单待支付，已经初始化");
    }

    @Override
    void toBePaid() {
        System.out.println("订单待支付操作");
    }

    @Override
    void completed() {
        System.out.println("订单待支付还没完成，请稍后再试");

    }
}
