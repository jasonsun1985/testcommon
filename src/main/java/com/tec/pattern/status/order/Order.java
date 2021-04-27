package com.tec.pattern.status.order;


/**
 * @author JASON
 * @version 1.0
 * @Description
 * @date 2021/4/23 18:52
 */
public class Order {
    static final Status INIT_STATE = new InitStatus();
    static final Status TO_BE_PAID_STATE = new ToBePaidStatus();
    static final Status COMPLETED_STATE = new CompletedStatus();

    private Status status;

    public Order(Status status) {
        switchState(status);
    }

    void switchState(Status status) {
        this.status = status;
        this.status.setContext(this);
    }

    public void init() {
        status.init();

    }

    public void toBePaid() {
        status.toBePaid();
    }

    public void completed() {
        status.completed();
    }
}
