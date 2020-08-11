package com.tec.pattern.absfactory.demo2;

public class DEMO {
	public static void main(String[] args) {
		VehicleFactory bmwFactory = new BMWFactory();
		Vehicle bmwCar = bmwFactory.getCar();
		bmwCar.go();
		Vehicle bmwBike = bmwFactory.getBike();
		bmwBike.go();
	}
}
