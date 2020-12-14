package com.hpower.enums;

/**
 * @Classname 报表流程状态
 * @Description TODO
 * @Date 2020/3/31 1:16 下午
 * @Created yangyang.jiang
 */
public enum  ReportProcessEnum implements IEnum<Integer>{

    //定时生成
    TIME(0, "定时生成"),

    //手动新增
    MANUAL(1, "手动新增"),

    //未上报
    NO(2, "未上报"),

    //重新编辑
    UPDATE(3, "重新编辑"),

    //待审核
    WAIT(4, "待审核"),

    //已驳回
    REJECT(5, "已驳回"),

    //已上传
    FINISH(6, "已上传");

    private final Integer value;
    private final String label;
    private final String remark;

    ReportProcessEnum(final int value, final String label) {
        this(value, label, null);
    }

    ReportProcessEnum(final int value, final String label, final String remark) {
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
