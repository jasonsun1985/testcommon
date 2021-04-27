package com.tec.pattern.status.lift;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName RunningState
 * @Description TODO
 * @Author jason
 * @Date 2021/4/23 16:03
 **/
@Slf4j
public class RunningState extends State {

    @Override
    public void open() {
        throw new StateLimitException("电梯正在运行，无法进行开门操作");
    }

    @Override
    public void close() {
        throw new StateLimitException("电梯正在运行，无法进行关门操作");
    }

    @Override
    public void run() {
        log.info("电梯正在运行中...");
    }

    @Override
    public void stop() {
        this.context.switchState(Lift.STOPPING_STATE);
        this.context.stop();
    }
}
