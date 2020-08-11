package com.tec.pattern.principle.srp;

class Animal {
    public void breathe(String animal) {
        System.out.println(animal + "呼吸空气");
    }

    public static void main(String[] args) {
        Animal animal = new Animal();
        animal.breathe("牛");
        animal.breathe("羊");
        animal.breathe("猪");
    }
    //如果引入鱼需要怎么办？修改陆地呼吸，水里呼吸类型，修改Animal类，然后后面水分淡水和海水等等，破坏了单一职责
}
