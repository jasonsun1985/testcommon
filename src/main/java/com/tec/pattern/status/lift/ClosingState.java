package com.tec.pattern.status.lift;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName ClosingState
 * @Description TODO
 * @Author jason
 * @Date 2021/4/23 15:58
 **/
@Slf4j
public class ClosingState extends State {

    @Override
    public void open() {
        this.context.switchState(Lift.OPENNING_STATE);
        this.context.open();
    }

    @Override
    public void close() {
        log.info("电梯正在关门，请稍等");
    }

    @Override
    public void run() {
        this.context.switchState(Lift.RUNNING_STATE);
        this.context.run();
    }

    @Override
    public void stop() {
        throw new StateLimitException("电梯正在关门，无法停止");
    }

}
