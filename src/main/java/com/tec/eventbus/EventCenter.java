package com.tec.eventbus;

import com.google.common.eventbus.EventBus;

/**
 * @author sunlei
 * @ClassName: EventBusCenter
 * @Description: (这里用一句话描述这个类的作用)
 */
public class EventCenter {
    /**
     * <code>eventBus</code> - {description}.
     */
    private static EventBus eventBus = new EventBus();

    /**
     * Constructors.
     */
    private EventCenter() {
    }

    /**
     * {method description}.
     *
     * @return EventBus
     */
    public static EventBus getInstance() {
        return eventBus;
    }
}