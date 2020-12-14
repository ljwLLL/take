package com.hpower.enums;

/**
 * @Classname 报表上报状态
 * @Description TODO
 * @Date 2020/3/30 3:56 下午
 * @Created yangyang.jiang
 */
public enum ReportStateEnum implements IEnum<Integer> {

    //未上报
    NO(0, "未上报"),

    //待审核
    WAIT(1, "待审核"),

    //已上报
    FINISH(2, "已上报"),

    //已驳回
    REJECT(3, "已驳回");

    private final Integer value;
    private final String label;
    private final String remark;

    ReportStateEnum(final int value, final String label) {
        this(value, label, null);
    }

    ReportStateEnum(final int value, final String label, final String remark) {
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
