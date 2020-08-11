package com.tec.method;

/**
 * @author SUNLEI
 * @version V1.0
 * @Description: <p>
 * 创建日期：2017年9月26日
 * </p>
 * @see
 */
public class Dish {
    private final String name;
    private final boolean vegetarian;
    private final int calories;

    public Dish(String name, boolean vegetarian, int calories) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    @Override
    public String toString() {
        return name;
    }
}
