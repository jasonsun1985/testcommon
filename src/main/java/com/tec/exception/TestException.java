package com.tec.exception;


public class TestException {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        try {
            System.out.println("test1() in");
            try {
                System.out.println(1 / 0);
            } catch (Exception e) {
                throw new RuntimeException("test1-try2");
            }
            System.out.println("over");
        } catch (Exception e) {
            throw new RuntimeException("test1-try1");

        }
    }
}
