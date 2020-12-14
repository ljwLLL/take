package com.hpower.enums;

/**
 * <p>
 * 是、否有效枚举
 * </P>
 *
 * @author yangyang.jiang
 * @date 2020/03/28
 * @since 1.0
 */
public enum WhetherEnum implements IEnum<Integer> {

    NO(0, "否"),

    YES(1, "是");

    private final Integer value;
    private final String label;
    private final String remark;

    WhetherEnum(final int value, final String label) {
        this(value, label, null);
    }

    WhetherEnum(final int value, final String label, final String remark) {
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
