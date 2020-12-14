package com.hpower.enums;

/**
 * @Classname 数据权限枚举类型
 * @Description TODO
 * @Date 2020/3/26 3:32 下午
 * @Created yangyang.jiang
 */
public enum DataTypeEnum implements IEnum<Integer> {

    //所有医院数据
    ALL(1, "所有医院数据"),

    //个人医院数据
    ONLY(2, "个人医院数据"),

    //指定医院数据
    APPOINT(3, "指定医院数据");

    private final Integer value;
    private final String label;
    private final String remark;

    DataTypeEnum(final int value, final String label) {
        this(value, label, null);
    }

    DataTypeEnum(final int value, final String label, final String remark) {
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
