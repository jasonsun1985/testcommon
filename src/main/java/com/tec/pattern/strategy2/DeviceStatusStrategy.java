package com.tec.pattern.strategy2;

/**
 * @ClassName DeviceStatusStrategy
 * @Description TODO
 * @Author sunlei4
 * @Date 2021/5/28 23:14
 **/

public interface DeviceStatusStrategy {
    /**
     * 处理状态接口
     *
     * @param message
     * @param deviceInfo
     * @param data
     * @return
     */
    public boolean processingStatus(String message, String deviceInfo, byte[] data);
}