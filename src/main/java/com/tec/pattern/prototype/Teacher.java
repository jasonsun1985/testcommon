package com.tec.pattern.prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Teacher implements Cloneable, Serializable {
	private static final long serialVersionUID = 800723712896917058L;
	private String name;
	private int age;
	private Address address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Object clone() throws CloneNotSupportedException {
		Teacher t = (Teacher) super.clone();
		return t;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Object deepclone() throws Exception {
		Teacher t = (Teacher) super.clone();
		t.address = (Address) this.address.clone();
		return t;
	}

	@Override
	public String toString() {
		return "Teacher [name=" + name + ", age=" + age + ", address=" + address.toString() + "]";
	}
	
}
