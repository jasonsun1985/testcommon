package com.tec.method;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @ClassName TestOthers
 * @Description TEST
 * @Author JASON
 * @Date 2021/3/3 15:26
 **/
public class TestOthers {
    public static void main(String[] args) {
        testObjectToMap();
    }

    private static void testObjectToMap() {
        SortedMap sortedMap = new TreeMap();
        Dish dish = new Dish("test", true, 10);
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(dish.getClass());
            PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();
            if (properties != null && properties.length > 0) {
                for (PropertyDescriptor prop : properties) {
                    System.out.println(prop.toString());
                    //2.得到属性名
                    String name = prop.getName();
                    //3.过滤class属性
                    if (!"class".equals(name)) {
                        //4.得到属性的get方法
                        Method getter = prop.getReadMethod();
                        //5.获取属性值
                        Object value = getter.invoke(dish);
                        //6.放入map中
                        if (value != null) {
                            sortedMap.put(name, value);
                        }
                    }
                }
            }
        } catch (Exception e) {

        }
        System.out.println(sortedMap);
        System.out.println("|||||||||||||||||||||||||||||||||testObjectToMap||||||||||||||||||||||||||||");
    }
}
