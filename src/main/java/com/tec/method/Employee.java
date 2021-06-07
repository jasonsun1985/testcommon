package com.tec.method;

import lombok.Data;

import java.util.List;

/**
 * @author SUNLEI4
 * @Description
 * @date 2021/5/28
 */
@Data
public class Employee {
    private String name;
    private int age;
    private List<Address> listAddress;
}
