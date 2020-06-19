package com.tec.file;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Person {
    private String name;
    private int age;
}
