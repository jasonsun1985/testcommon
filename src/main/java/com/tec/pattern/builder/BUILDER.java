package com.tec.pattern.builder;

/**
* @Description: 建造者模式
* @author sunlei
*	使用多个简单的对象一步一步构建成一个复杂的对象
*/
public class BUILDER {
	public static void main(String[] args) {
		Person p = new Person.Builder().name("Cristiano Ronaldo").age(34).gender("male").hair("black").colour("white").height(185).build();
		System.out.println(p.toString());
	}
}