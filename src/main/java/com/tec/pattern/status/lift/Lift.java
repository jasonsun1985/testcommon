package com.tec.pattern.status.lift;

/**
 * @ClassName Lift
 * @Description TODO
 * @Author jason
 * @Date 2021/4/23 15:59
 **/
public class Lift {
    static final State OPENNING_STATE = new OpenningState();
    static final State CLOSING_STATE = new ClosingState();
    static final State RUNNING_STATE = new RunningState();
    static final State STOPPING_STATE = new StoppingState();

    private State state;

    public Lift(State state) {
        switchState(state);
    }

    void switchState(State state) {
        this.state = state;
        this.state.setContext(this);
    }

    public boolean open() {
        try{
            state.open();
        } catch (StateLimitException ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean close() {
        try{
            state.close();
        } catch (StateLimitException ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean run() {
        try{
            state.run();
        } catch (StateLimitException ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean stop() {
        try{
            state.stop();
        } catch (StateLimitException ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }
}
