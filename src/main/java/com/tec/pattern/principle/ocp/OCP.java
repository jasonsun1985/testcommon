package com.tec.pattern.principle.ocp;

/**
 * @author sunlei
 * @Description:
 * @date 2019年9月16日
 * <p>
 * 里氏替换原则 - 在1988年，由麻省理工学院的一位姓里的女士（Barbara Liskov）提出来的。
 * 定义1：如果对每一个类型为 T1的对象 o1，都有类型为 T2 的对象o2，使得以 T1定义的所有程序 P
 * 在所有的对象 o1 都代换成 o2 时，程序 P 的行为没有发生变化，那么类型 T2 是类型 T1 的子类型。
 * <p>
 * 定义2：所有引用基类的地方必须能透明地使用其子类的对象。
 * <p>
 * 在软件系统中，一个可以接受基类对象的地方必然可以接受一个子类对象
 * <p>
 * 通俗来讲：里氏替换原则通俗的来讲就是：子类可以扩展父类的功能，但不能改变父类原有的功能。
 */
public class OCP {
    public static void main(String[] args) {
        A a = new A();
    }
}
