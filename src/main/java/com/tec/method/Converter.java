package com.tec.method;

/**
 * @author SUNLEI
 * @version V1.0
 * @Description: <p>创建日期：2020年1月3日 </p>
 * @see
 */
@FunctionalInterface
public interface Converter<F, T> {
    T covert(F from);
}
