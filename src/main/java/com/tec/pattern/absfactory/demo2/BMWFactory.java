package com.tec.pattern.absfactory.demo2;

public class BMWFactory implements VehicleFactory  {

	public Vehicle getCar() {
		return new BMWCar();
	}

	public Vehicle getBike() {
		return new BMWBike();
	}

	public Vehicle getMoto() {
		return new BMWMoto();
	}
}
