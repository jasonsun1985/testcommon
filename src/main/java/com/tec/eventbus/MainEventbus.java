package com.tec.eventbus;

/**
 * @ClassName MainEventbus
 * @Description TODO
 * @Author sunlei4
 * @Date 2021/4/20 17:08
 **/
public class MainEventbus {
    public static void main(String[] args) {
        EventCenter.getInstance().register(new TestClass());
        EventCenter.getInstance().post("asdasda");
    }
}
