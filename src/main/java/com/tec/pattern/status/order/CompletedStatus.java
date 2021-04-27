package com.tec.pattern.status.order;

/**
 * @author JASON
 * @version 1.0
 * @Description
 * @date 2021/4/23 18:50
 */
public class CompletedStatus extends Status {
    @Override
    void init() {
        this.context.switchState(new InitStatus());
        this.context.init();
    }

    @Override
    void toBePaid() {
        System.out.println("订单已支付，无法再支付");
    }

    @Override
    void completed() {
        System.out.println("订单完成操作");
    }
}
