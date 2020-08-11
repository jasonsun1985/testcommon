/**
 * 软件版权：SUNLEI
 * 系统名称：java8
 * 文件名称：People.java
 * 版本变更记录（可选）：修改日期2017年8月30日  下午4:02:04，修改人SUNLEI，工单号（手填），修改描述（手填）
 */
package com.tec.method;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class People {
    String name;
    int age;
}
