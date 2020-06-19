package com.tec.method;

/** 
 * @Description:
 * <p>创建日期：2020年1月3日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
@FunctionalInterface
public interface Converter<F,T> {
	T covert(F from);
}
