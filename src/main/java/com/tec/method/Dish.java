package com.tec.method;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author SUNLEI
 * @version V1.0
 * @Description: <p>
 * 创建日期：2017年9月26日
 * </p>
 * @see
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dish {
    private String name;
    private Boolean vegetarian;
    private Integer calories;
}
