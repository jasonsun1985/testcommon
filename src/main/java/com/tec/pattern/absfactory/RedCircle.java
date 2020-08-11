package com.tec.pattern.absfactory;

/**
* @Description: 具体颜色的Circle实现
* @author sunlei
*
*/
public class RedCircle extends Circle {
	@Override
	public void draw() {
		System.out.println("画红色圆");
	}
}
