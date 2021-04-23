package com.tec.pattern.status;

/**
 * @ClassName TestLift
 * @Description TODO
 * @Author jason
 * @Date 2021/4/23 16:17
 **/
public class TestLift {
    public static void main(String[] args) {
//        Lift lift = new Lift(new OpenningState());
        Lift lift = new Lift(new ClosingState());
        lift.run();
        lift.open();
        lift.stop();
    }
}
