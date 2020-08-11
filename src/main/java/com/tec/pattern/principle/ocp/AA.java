package com.tec.pattern.principle.ocp;

public class AA extends A {

	@Override
	public int func1(int a, int b) {
		//原有方法
//		return super.func1(a, b);
		//不小心修改了
		return a+b;
	}
	//调用结果肯定是错误的！！！
	public int func2(int a, int b){
		return func1(a,b)+100;
	}
	
}
