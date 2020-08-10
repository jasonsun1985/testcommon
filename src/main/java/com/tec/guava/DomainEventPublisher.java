package com.tec.guava;

/**
 * 领域事件发射器
 */
public interface DomainEventPublisher<T extends DomainEvent> {
    String identify();

    void register(Object listener);

    void publish(T event);

    void asyncPublish(T event);
}