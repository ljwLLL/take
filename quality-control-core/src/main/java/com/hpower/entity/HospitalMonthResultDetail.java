package com.hpower.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 月度指标结果指标明细
 * </p>
 *
 * @author yangyang.jiang
 * @since 2020-03-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_hospital_month_result_detail")
public class HospitalMonthResultDetail extends Model<HospitalMonthResultDetail> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 月度指标id
     */
    @TableField("month_id")
    private Long monthId;

    /**
     * 营养科肠外营养治疗不良事件发生例数
     */
    @TableField("nuto_one_a_number")
    private String nutoOneANumber;

    /**
     * 营养科同期肠外营养治疗总例数
     */
    @TableField("nuto_one_a_count")
    private String nutoOneACount;

    /**
     * 肠外营养百分比
     */
    @TableField("nuto_one_a_perentage")
    private String nutoOneAPerentage;

    /**
     * 营养科肠内营养治疗不良事件发生例数
     */
    @TableField("nuto_one_b_number")
    private String nutoOneBNumber;

    /**
     * 营养科同期肠内营养治疗总例数
     */
    @TableField("nuto_one_b_count")
    private String nutoOneBCount;

    /**
     * 肠内营养百分比
     */
    @TableField("nuto_one_b_perentage")
    private String nutoOneBPerentage;

    /**
     * 医疗膳食治疗不良事件发生例数
     */
    @TableField("nuto_one_c_number")
    private String nutoOneCNumber;

    /**
     * 同期医疗膳食治疗总例数
     */
    @TableField("nuto_one_c_count")
    private String nutoOneCCount;

    /**
     * 医疗膳食不良百分比
     */
    @TableField("nuto_one_c_perentage")
    private String nutoOneCPerentage;

    /**
     * 营养门诊投诉发生例数
     */
    @TableField("nuto_tow_number")
    private String nutoTowNumber;

    /**
     * 同期营养门诊治疗总例数
     */
    @TableField("nuto_tow_count")
    private String nutoTowCount;

    /**
     * 门诊投诉百分比
     */
    @TableField("nuto_tow_perentage")
    private String nutoTowPerentage;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 是否启用 0未启用 1已启用
     */
    @TableField("enabled")
    private Integer enabled;

    /**
     * 删除标志 0已删除 1未删除
     */
    @TableField("valid")
    @TableLogic
    private Integer valid;

    /**
     * 创建人
     */
    @TableField("creator")
    private Long creator;

    /**
     * 创建人姓名
     */
    @TableField("creator_name")
    private String creatorName;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 修改人
     */
    @TableField("updater")
    private Long updater;

    /**
     * 修改人姓名
     */
    @TableField("updater_name")
    private String updaterName;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 版本号
     */
    @TableField("version")
    @Version
    private Integer version;


    public static final String ID = "id";

    public static final String MONTH_ID = "month_id";

    public static final String NUTO_ONE_A_NUMBER = "nuto_one_a_number";

    public static final String NUTO_ONE_A_COUNT = "nuto_one_a_count";

    public static final String NUTO_ONE_A_PERENTAGE = "nuto_one_a_perentage";

    public static final String NUTO_ONE_B_NUMBER = "nuto_one_b_number";

    public static final String NUTO_ONE_B_COUNT = "nuto_one_b_count";

    public static final String NUTO_ONE_B_PERENTAGE = "nuto_one_b_perentage";

    public static final String NUTO_ONE_C_NUMBER = "nuto_one_c_number";

    public static final String NUTO_ONE_C_COUNT = "nuto_one_c_count";

    public static final String NUTO_ONE_C_PERENTAGE = "nuto_one_c_perentage";

    public static final String NUTO_TOW_NUMBER = "nuto_tow_number";

    public static final String NUTO_TOW_COUNT = "nuto_tow_count";

    public static final String NUTO_TOW_PERENTAGE = "nuto_tow_perentage";

    public static final String REMARK = "remark";

    public static final String ENABLED = "enabled";

    public static final String VALID = "valid";

    public static final String CREATOR = "creator";

    public static final String CREATOR_NAME = "creator_name";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATER = "updater";

    public static final String UPDATER_NAME = "updater_name";

    public static final String UPDATE_TIME = "update_time";

    public static final String VERSION = "version";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
