package com.tec.guava;

import java.util.Date;

public abstract class DomainEvent {
    private Date occurredTime;

    protected abstract String identify();

    public DomainEvent() {
        occurredTime = new Date();
    }

    public Date getOccurredTime() {
        return occurredTime;
    }
}
