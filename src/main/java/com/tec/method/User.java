package com.tec.method;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ClassName User
 * @Description TODO
 * @Author sunlei4
 * @Date 2021/4/22 17:26
 **/
@Data
@AllArgsConstructor
public class User {
    private String name;
    private Integer age;
    private String cityName;
}
