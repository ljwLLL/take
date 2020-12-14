package com.hpower.enums;

import io.swagger.models.auth.In;

/**
 * @Classname 启用枚举类
 * @Description TODO
 * @Date 2020/3/26 11:15 上午
 * @Created yangyang.jiang
 */
public enum EnabledEnum implements IEnum<Integer> {

    //启用
    ENABLED(1, "启用"),

    //禁用
    FORBIDDEN(0, "禁用");

    private final Integer value;
    private final String label;
    private final String remark;

    EnabledEnum(final int value, final String label) {
        this(value, label, null);
    }

    EnabledEnum(final int value, final String label, final String remark) {
        this.value = value;
        this.label = label;
        this.remark = remark;
    }

    /**
     * @return 数据值
     */
    @Override
    public Integer getValue() {
        return value;
    }

    /**
     * @return 标签名
     */
    @Override
    public String getLabel() {
        return label;
    }

    /**
     * @return 备注
     */
    @Override
    public String getRemark() {
        return remark;
    }
}
