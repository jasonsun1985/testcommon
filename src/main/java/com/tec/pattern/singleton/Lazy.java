package com.tec.pattern.singleton;

public class Lazy {
    private static Lazy lazy = null;

    private Lazy() {
    }

    public static synchronized Lazy getInstance() {
        if (lazy == null) {
            lazy = new Lazy();
        }
        return lazy;
    }
}