package com.tec.pattern.absfactory;

public class RedShapeFactory implements ShapeFactory {

	public Shape getCircle() {
		return new RedCircle();
	}

	public Shape getRectange() {
		return new RedRectange();
	}

}
