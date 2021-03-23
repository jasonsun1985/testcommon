/**
 * Project Name:hospital;<br/>
 * File Name:ReceptionMethodEnums;<br/>
 * Package Name:com.sq580.ms.hospital.biz.domain.enums;<br/>
 * Date: 2018-10-16 20:23;<br/>
 * Copyright (c) 2018, www.sq580.com All Rights Reserved.;<br/>
 */
package com.tec.method;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum ConsultationTypeEnums {

    /**
     * 问诊类型
     */
    MEDICAL_INSURANCE_MEN_TE("医保门特", "1"),
    MEDICAL_INSURANCE_MEN_ZHEN("医保门诊", "2"),
    OWN_EXPENSE("自费", "3");

    private String key;
    private String eval;

    public Byte getToByte() {
        return Byte.valueOf(eval);
    }

    public Integer getToInteger() {
        return Integer.valueOf(eval);
    }

}