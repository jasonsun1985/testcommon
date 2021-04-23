package com.tec.pattern.status;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName OpenningState
 * @Description TODO
 * @Author jason
 * @Date 2021/4/23 16:01
 **/
@Slf4j
public class OpenningState extends State {

    @Override
    public void open() {
        log.info("电梯正在开门...");
    }

    @Override
    public void close() {
        this.context.switchState(Lift.closingState);
        this.context.close();
    }

    @Override
    public void run() {
        throw new StateLimitException("电梯正在开门，无法运行");
    }

    @Override
    public void stop() {
        throw new StateLimitException("电梯正在开门，无法停止");
    }
}
