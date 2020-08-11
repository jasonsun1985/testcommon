package com.tec.pattern.builder;

public class Person {
	private final String name;
	private final int age;
	private final String colour;
	private final String hair;
	private final int height;
	private final String gender;

	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public String getColour() {
		return colour;
	}
	public String getHair() {
		return hair;
	}
	public int getHeight() {
		return height;
	}
	public String getGender() {
		return gender;
	}

	public static class Builder {
		private String name;
		private int age;
		private String gender;
		private String colour;
		private String hair;
		private int height;
		public Builder(){
		}
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		public Builder age(int age) {
			this.age = age;
			return this;
		}
		public Builder gender(String gender) {
			this.gender = gender;
			return this;
		}
		public Builder colour(String colour) {
			this.colour = colour;
			return this;
		}
		public Builder hair(String hair) {
			this.hair = hair;
			return this;
		}
		public Builder height(int height){
			this.height = height;
			return this;
		}
		public Person build(){
			return new Person(this);
		}
	}
	private Person(Builder builder){
		this.name = builder.name;
		this.gender = builder.gender;
		this.age=builder.age;
		this.height=builder.height;
		this.hair=builder.hair;
		this.colour=builder.colour;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", colour=" + colour + ", hair=" + hair + ", height=" + height
				+ ", gender=" + gender + "]";
	}
}
