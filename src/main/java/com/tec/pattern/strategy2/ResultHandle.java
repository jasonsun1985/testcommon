package com.tec.pattern.strategy2;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author SUNLEI4
 * @Description
 * @date 2021/5/28
 */
public class ResultHandle {
    //创建一个关系型map，用来存储对象，value值为策略类
    private static Map<String, DeviceStatusStrategy> mapStrategy = new HashMap<String, DeviceStatusStrategy>();

    //初始化map对象，存储各个需要使用的具体类
    static {
        mapStrategy.put("1500", new resultDID1500());
        mapStrategy.put("2100", new saveDeviceState2100());
//        mapStrategy.put("5100", new saveWifiInfo5100());
//        mapStrategy.put("2200", new saveWifiState2200());
//        mapStrategy.put("2280", new seeWifiStateResult2280());
//        mapStrategy.put("1888", new saveDevcieStateInfo1888());
    }

    private DeviceStatusContext deviceStatusContext;

    /**
     * 第一种方式：获取对应的状态码，进行map查询，查询出对应的具体类对象
     *
     * @param message
     * @param type
     * @param data
     * @return
     */
    public boolean handleDeviceStatusStrategy(String message, String type, byte[] data) {
        deviceStatusContext = new DeviceStatusContext(mapStrategy.get(type));
        return deviceStatusContext.executeStrategy(message, type, data);
    }

    /**
     * 第二种方式：通过配置文件形式，获取对应的具体实现类，通过反射获取上下文对象，在进行调用
     * 这种方式，更加的灵活，当需要添加状态的时候，只需要添加类型，然后在配置文件中添加类地址就可以实现增加
     *
     * @param message
     * @param type
     * @param data
     * @return
     */
    public boolean handleStrategy(String message, String type, byte[] data) {
//        String value = PropertyUtils.getValue("device" + type);
//        this.CreateDeviceStateStrategy(value);
        return deviceStatusContext.executeStrategy(message, "123123123", data);
    }

    /**
     * 将具体类的对象地址通过反射生成上下文对象
     *
     * @param type
     */
    public void CreateDeviceStateStrategy(String type) {
        Class clz;
        try {
            clz = Class.forName(type);
            Constructor<?> constructor = clz.getConstructor();
            DeviceStatusStrategy deviceStatusStrategy = (DeviceStatusStrategy) constructor.newInstance(null);
            deviceStatusContext = new DeviceStatusContext(deviceStatusStrategy);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
