package com.tec.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * @author SUNLEI
 * @version V1.0
 * @Description: <p>创建日期：2019年12月20日 </p>
 * @see
 */
public class TestClass {
    @Subscribe
    public void getEventbusInfo(String str) {
        System.out.println("received : " + str);
    }
}
