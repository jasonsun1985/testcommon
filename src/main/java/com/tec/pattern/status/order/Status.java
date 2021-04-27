package com.tec.pattern.status.order;

/**
 * @author JASON
 * @version 1.0
 * @Description
 * @date 2021/4/23 18:27
 */
public abstract class Status {
    protected Order context;
    void setContext(Order order){
        this.context = order;
    }

    abstract void init();

    abstract void toBePaid();

    abstract void completed();

}
