package com.tec.pattern.status.order;

/**
 * @author JASON
 * @version 1.0
 * @Description
 * @date 2021/4/23 19:05
 */
public class TestOrder {
    public static void main(String[] args) {
        Order order = new Order(new CompletedStatus());
        order.completed();
        order.toBePaid();
        order.init();
    }
}
