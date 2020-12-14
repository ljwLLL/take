/*
 * Copyright (c) 2018.
 * http://www.ulabcare.com
 */

package com.hpower.enums;

import java.io.Serializable;

/**
 * 枚举接口
 *
 * @param <T>
 * @author yangyang.jiang
 * @date 2020/03/26
 * @since 1.0
 */
public interface IEnum<T extends Serializable> {

    /**
     * 数据值
     *
     * @return 数据值
     */
    T getValue();

    /**
     * 标签名
     *
     * @return 标签名
     */
    String getLabel();

    /**
     * 备注，对数据值进行详细说明
     *
     * @return 备注
     */
    String getRemark();

}
