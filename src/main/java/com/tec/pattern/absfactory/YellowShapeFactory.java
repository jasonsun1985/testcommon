package com.tec.pattern.absfactory;

public class YellowShapeFactory implements ShapeFactory {

	public Shape getCircle() {
		return new YellowCircle();
	}

	public Shape getRectange() {
		return new YellowRectange();
	}

}
