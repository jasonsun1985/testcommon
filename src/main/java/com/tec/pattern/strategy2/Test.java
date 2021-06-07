package com.tec.pattern.strategy2;

/**
 * @author SUNLEI4
 * @Description
 * @date 2021/5/28
 */
public class Test {
    public static void main(String[] args) {

        ResultHandle rh = new ResultHandle();
        //第一种方式，运行成功
        rh.handleDeviceStatusStrategy("1111111", "1500", new byte[0]);
        //第二种方式，也运行成功
        rh.handleStrategy("1111111", "2100", new byte[0]);
    }
}
