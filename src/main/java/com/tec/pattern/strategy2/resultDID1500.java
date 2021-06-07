package com.tec.pattern.strategy2;

/**
 * @author SUNLEI4
 * @Description
 * @date 2021/5/28
 */
public class resultDID1500 implements DeviceStatusStrategy{
    @Override
    public boolean processingStatus(String message, String deviceInfo, byte[] data) {
        System.out.println("");
        return true;
    }
}
