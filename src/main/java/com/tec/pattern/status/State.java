package com.tec.pattern.status;

/**
 * @ClassName State
 * @Description TODO
 * @Author jason
 * @Date 2021/4/23 15:59
 **/
public abstract class State {
    protected Lift context;

    void setContext(Lift context) {
        this.context = context;
    }

    /**
     * 开门
     */
    abstract void open();

    /**
     * 关门
     */
    abstract void close();

    /**
     * 启动
     */
    abstract void run();

    /**
     * 停止
     */
    abstract void stop();
}
