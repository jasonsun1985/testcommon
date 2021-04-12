package com.tec.java8.growing;

import java.util.function.Supplier;

public interface DefaulableFactory {
    // Interfaces now allow static methods
    static Integer create(Supplier<Integer> supplier) {
        return supplier.get();
    }
}