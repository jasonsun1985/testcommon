package com.tec.utils;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import io.jsonwebtoken.lang.Strings;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.ReflectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


public class Beans {
    /**
     * 封装 ，惯用与直接将转换结果返回
     * <p>
     * <pre>
     *      UserBean userBean = new UserBean("username");
     *      return BeanUtil.transform(UserDTO.class, userBean);
     * </pre>
     */
    public static <T> T transform(Class<T> clazz, Object src) {
        if (src == null) {
            return null;
        }
        T instance = null;
        try {
            instance = clazz.newInstance();
        } catch (Exception e) {

            throw new IllegalArgumentException(e);
        }
        org.springframework.beans.BeanUtils.copyProperties(src, instance, getNullPropertyNames(src));
        return instance;
    }


    public static <T> T transform(Class<T> clazz, Object src, String... ignoreProperties) {
        if (src == null) {
            return null;
        }
        T instance = null;
        try {
            instance = clazz.newInstance();
        } catch (Exception e) {

            throw new IllegalArgumentException(e);
        }
        org.springframework.beans.BeanUtils.copyProperties(src, instance, ignoreProperties);
        return instance;
    }


    public static <T> List<T> batchTransform(final Class<T> clazz, List srcList) {
//        Function<Object,T> result = src -> transform(clazz, src);
//        return Lists.transform(srcList, result);
        return Lists.transform(srcList, new Function() {
            @Override
            public Object apply(Object src) {
                return transform(clazz, src);
            }
        });
    }

    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];

        return emptyNames.toArray(result);
    }

    public static String getChangeListString(Object newUser, Object oldUser, Class clazz) {
        StringBuilder sb = new StringBuilder();
        for (Field field : clazz.getDeclaredFields()) {
            ReflectionUtils.makeAccessible(field);
            Object newField = ReflectionUtils.getField(field, newUser);
            Object oldField = ReflectionUtils.getField(field, oldUser);
            if (newField != null && !Objects.equals(newField, oldField)) {
                sb.append("属性");
                sb.append(field.getName());
                sb.append(": 由[");
                sb.append(oldField);
                sb.append("] 改为->[");
                sb.append(newField);
                sb.append("];");
            }
        }
        if (!Strings.hasText(sb.toString())) {
            sb.append("没有修改内容");
        }
        return sb.toString();
    }
}
