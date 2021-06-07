package com.tec.pattern.strategy2;

/**
 * @author SUNLEI4
 * @Description
 * @date 2021/5/28
 */
public class DeviceStatusContext {
    /**
     * 声明一个策略类接口，用于调用具体的实现类
     */
    private DeviceStatusStrategy deviceStatusStrategy;

    public DeviceStatusContext(DeviceStatusStrategy deviceStatusStrategy) {
        this.deviceStatusStrategy = deviceStatusStrategy;
    }

    /**
     * 执行策略类接口，会调用具体的实现类中的方法
     *
     * @param message
     * @param deviceInfo
     * @param data
     * @return
     */
    public boolean executeStrategy(String message, String deviceInfo, byte[] data) {
        return deviceStatusStrategy.processingStatus(message, deviceInfo, data);
    }

}