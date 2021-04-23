package com.tec.pattern.status;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName StoppingState
 * @Description TODO
 * @Author jason
 * @Date 2021/4/23 16:04
 **/
@Slf4j
public class StoppingState extends State {

    @Override
    public void open() {
        this.context.switchState(Lift.openningState);
        this.context.open();
    }

    @Override
    public void close() {
        throw new StateLimitException("电梯已停止，无法关闭");
    }

    @Override
    public void run() {
        this.context.switchState(Lift.runningState);
        this.context.run();
    }

    @Override
    public void stop() {
        log.info("电梯已停止运行");
    }
}
