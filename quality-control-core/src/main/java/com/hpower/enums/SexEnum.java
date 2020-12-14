package com.hpower.enums;

/**
 * 性别:1、女，2、男
 *
 * @author yangyang.jiang
 * @date 2020/03/26
 * @since 1.0
 */
public enum SexEnum implements IEnum<Integer> {
    /**
     * 女
     */
    FEMALE(1, "女"),
    /**
     * 男
     */
    MALE(2, "男");

    private final Integer value;
    private final String label;
    private final String remark;

    SexEnum(final int value, final String label) {
        this(value, label, null);
    }

    SexEnum(final int value, final String label, final String remark) {
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
