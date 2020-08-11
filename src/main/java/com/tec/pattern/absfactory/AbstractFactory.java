package com.patterns.absfactory;

import com.tec.pattern.absfactory.RedShapeFactory;
import com.tec.pattern.absfactory.Shape;
import com.tec.pattern.absfactory.ShapeFactory;
import com.tec.pattern.absfactory.YellowShapeFactory;

/**
 * 1) 抽象工厂角色： 这是工厂方法模式的核心，它与应用程序无关。是具体工厂角色必须实现的接口或者必须继承的父类。在java中它由抽象类或者接口来实现。
 * 2) 具体工厂角色：它含有和具体业务逻辑有关的代码。由应用程序调用以创建对应的具体产品的对象。
 * 3) 抽象产品角色：它是具体产品继承的父类或者是实现的接口。在java中一般有抽象类或者接口来实现。
 * 4) 具体产品角色：具体工厂角色所创建的对象就是此角色的实例。在java中由具体的类来实现。
 * <p>
 * <p>
 * <p>
 * ○  △   ◇  一个产品族
 * 纵向代表等级，例如 实心原型 实心三角 实心菱形
 */
public class AbstractFactory {
    public static void main(String[] args) {
        ShapeFactory redShapeFactory = new RedShapeFactory();
        Shape circle = redShapeFactory.getCircle();
        circle.draw();
        Shape rectangle = redShapeFactory.getRectange();
        rectangle.draw();

        ShapeFactory yellowFactory = new YellowShapeFactory();
        Shape circle2 = yellowFactory.getCircle();
        circle2.draw();
        Shape rectangle2 = yellowFactory.getRectange();
        rectangle2.draw();
    }
}
