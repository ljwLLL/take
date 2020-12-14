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
 * 年度指标基础信息明细
 * </p>
 *
 * @author yangyang.jiang
 * @since 2020-03-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_hospital_year_base_detail")
public class HospitalYearBaseDetail extends Model<HospitalYearBaseDetail> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 年度指标id
     */
    @TableField("year_id")
    private Long yearId;

    /**
     * 医院等级
     */
    @TableField("level")
    private String level;

    /**
     * 年度住院患者总数
     */
    @TableField("year_patient_count")
    private String yearPatientCount;

    /**
     * 医院编制床位数
     */
    @TableField("bed_count")
    private String bedCount;

    /**
     * 是否开设营养科
     */
    @TableField("whether_nutrition")
    private Integer whetherNutrition;

    /**
     * 营养科归属部门
     */
    @TableField("nutrition_dept")
    private Integer nutritionDept;

    /**
     * 营养科所属管理部门
     */
    @TableField("nutrition_manager_dept")
    private String nutritionManagerDept;

    /**
     * 是否每日营养科查房
     */
    @TableField("whether_day_round")
    private Integer whetherDayRound;

    /**
     * 是否有营养科三级查房
     */
    @TableField("whether_three_round")
    private Integer whetherThreeRound;

    /**
     * 营养科类别及人数
     */
    @TableField("type_number")
    private String typeNumber;

    /**
     * 住院患者营养筛查总例数
     */
    @TableField("patient_screen_count")
    private String patientScreenCount;

    /**
     * 住院营养筛查阳性总例数
     */
    @TableField("patient_screen_positive_count")
    private String patientScreenPositiveCount;

    /**
     * 营养科是否有独立收费项目
     */
    @TableField("whether_independent")
    private Integer whetherIndependent;

    /**
     * 独立收费项目个数
     */
    @TableField("whether_independent_count")
    private String whetherIndependentCount;

    /**
     * 营养教学科研
     */
    @TableField("nutrition_research")
    private String nutritionResearch;

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

    public static final String YEAR_ID = "year_id";

    public static final String LEVEL = "level";

    public static final String YEAR_PATIENT_COUNT = "year_patient_count";

    public static final String BED_COUNT = "bed_count";

    public static final String WHETHER_NUTRITION = "whether_nutrition";

    public static final String NUTRITION_DEPT = "nutrition_dept";

    public static final String NUTRITION_MANAGER_DEPT = "nutrition_manager_dept";

    public static final String WHETHER_DAY_ROUND = "whether_day_round";

    public static final String WHETHER_THREE_ROUND = "whether_three_round";

    public static final String TYPE_NUMBER = "type_number";

    public static final String PATIENT_SCREEN_COUNT = "patient_screen_count";

    public static final String PATIENT_SCREEN_POSITIVE_COUNT = "patient_screen_positive_count";

    public static final String WHETHER_INDEPENDENT = "whether_independent";

    public static final String WHETHER_INDEPENDENT_COUNT = "whether_independent_count";

    public static final String NUTRITION_RESEARCH = "nutrition_research";

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
