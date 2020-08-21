package com.tec.cas;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.atomic.AtomicReference;


public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User z3 = new User("zhangsan", 23);
        User li4 = new User("lisi", 23);

        AtomicReference<Object> atomicReference = new AtomicReference<>();
        atomicReference.set(z3);

        System.out.println(atomicReference.compareAndSet(z3, li4) + "\t"
                + atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(z3, li4) + "\t"
                + atomicReference.get().toString());
    }
}

@Data
@AllArgsConstructor
class User {
    String username;
    int age;
}
