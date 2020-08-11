package com.tec.pattern.prototype;

/**
 * @author sunlei
 * <p>
 * 优点：
 * 原型模式是在内存二进制流的拷贝,要比直接new一个对象性能好很多,
 * 特别是要在一个循环体内产生大量的对象时,原型模式可以更好地体现其优点
 * <p>
 * 需要最大的注意事项就是深浅拷贝的使用
 * @Description: 原型模式
 */
public class PROTOTYPE {
    public static void main(String[] args) throws Exception {
        Teacher t1 = new Teacher();
        t1.setName("王老师");
        t1.setAge(45);
        Address a = new Address();
        a.setStreet("长安街");
        a.setZipcode(100000);
        t1.setAddress(a);
        System.out.println("原始t1 : " + t1.toString());
        /////////第二个老师t2 克隆后改了住址，竟然给第一个老师的地址一起改了……///////////
        Teacher t2 = (Teacher) t1.clone();
        System.out.println("原始t2 : " + t2.toString());
        t2.getAddress().setStreet("嘉陵道");
        System.out.println("原始t1 : " + t1.toString());
        System.out.println("修改地址后的t2 : " + t2.toString());
        ///////////// t3使用深拷贝互不影响 /////////////////////
        Teacher t3 = (Teacher) t1.deepclone();
        System.out.println("原始t3 : " + t3.toString());
        t3.getAddress().setStreet("小白楼");
        System.out.println("原始t1 : " + t1.toString());
        System.out.println("修改地址后的t3 : " + t3.toString());
    }
}
