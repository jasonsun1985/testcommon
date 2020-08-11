package com.tec.pattern.simplefactory;

/**
* @Description: 4S店-重量级角色，工厂
* @author sunlei
*
*/
public abstract class CarFactory {
	public static Car driverCar(String type) throws Exception {
		if (type.equalsIgnoreCase("Audi"))
			return new AudiCar();
		else if (type.equalsIgnoreCase("BMW")) {
			return new BMWCar();
		} else {
			throw new Exception("没有");
		}
		
		
		//1.如果一个土豪来了需要一辆劳斯莱斯……
		//2.如果用户需要买BMW5系、有人想买7系怎么办
	}

}
