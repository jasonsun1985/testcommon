package com.tec.pattern.prototype;

import java.io.Serializable;

public class Address implements Cloneable, Serializable {
	private String street;
	private int zipcode;
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Address a = (Address) super.clone();
		return a;
	}
	@Override
	public String toString() {
		return "Address [street=" + street + ", zipcode=" + zipcode + "]";
	}
}
