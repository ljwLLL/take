package com.hpower.enums;

/**
 * @Classname 数据权限数据类型
 * @Description TODO
 * @Date 2020/3/27 3:53 下午
 * @Created yangyang.jiang
 */
public enum PermissionEnum implements IEnum<Integer> {

    //所有数据类型
    ALL(1, "所有数据类型"),

    //部分数据类型
    PART(2, "部分数据类型");

    private final Integer value;
    private final String label;
    private final String remark;

    PermissionEnum(final int value, final String label) {
        this(value, label, null);
    }

    PermissionEnum(final int value, final String label, final String remark) {
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
