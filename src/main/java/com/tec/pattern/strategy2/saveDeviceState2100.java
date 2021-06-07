package com.tec.pattern.strategy2;

/**
 * @author SUNLEI4
 * @Description
 * @date 2021/5/28
 */
public class saveDeviceState2100 implements DeviceStatusStrategy{
    @Override
    public boolean processingStatus(String message, String deviceInfo, byte[] data) {
        return true;
    }
}
