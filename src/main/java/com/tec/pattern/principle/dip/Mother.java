package com.tec.pattern.principle.dip;

public class Mother {
	public void read(IReader reader){
		System.out.println("妈妈开始讲故事");
		System.out.println(reader.getContent());
	}
	public static void main(String[] args) {
		Mother m = new Mother();
		m.read(new Book());
		m.read(new Newspaper());
		// 这样修改后，无论以后怎样扩展，都不需要再修改Mother类了
	}
}