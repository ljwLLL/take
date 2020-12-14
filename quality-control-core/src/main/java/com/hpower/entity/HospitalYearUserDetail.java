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
 * 年度指标营养科人员组成明细
 * </p>
 *
 * @author yangyang.jiang
 * @since 2020-03-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_hospital_year_user_detail")
public class HospitalYearUserDetail extends Model<HospitalYearUserDetail> {

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
     * 营养医师人数
     */
    @TableField("doctor_count")
    private String doctorCount;

    /**
     * (医师)临床执业医师人数
     */
    @TableField("doctor_clinic_count")
    private String doctorClinicCount;

    /**
     * (医师)公卫执业医师人数
     */
    @TableField("doctor_public_count")
    private String doctorPublicCount;

    /**
     * (医师)中医类执业医师人数
     */
    @TableField("doctor_medicine_count")
    private String doctorMedicineCount;

    /**
     * (医师)博士学历总人数
     */
    @TableField("doctor_bx_count")
    private String doctorBxCount;

    /**
     * (医师)硕士学历总人数
     */
    @TableField("doctor_sx_count")
    private String doctorSxCount;

    /**
     * (医师)本科学历总人数
     */
    @TableField("doctor_bk_count")
    private String doctorBkCount;

    /**
     * (医师)高级职称营养医师人数
     */
    @TableField("doctor_senior_count")
    private String doctorSeniorCount;

    /**
     * (医师)中级职称营养医师人数
     */
    @TableField("doctor_middle_count")
    private String doctorMiddleCount;

    /**
     * (医师)初级职称营养医师人数
     */
    @TableField("doctor_primary_count")
    private String doctorPrimaryCount;

    /**
     * 营养技师总人数
     */
    @TableField("tech_count")
    private String techCount;

    /**
     * (技师)博士学历总人数
     */
    @TableField("tech_bx_count")
    private String techBxCount;

    /**
     * (技师)硕士学历总人数
     */
    @TableField("tech_sx_count")
    private String techSxCount;

    /**
     * (技师)本科学历总人数
     */
    @TableField("tech_bk_count")
    private String techBkCount;

    /**
     * (技师)高级职称营养医师人数
     */
    @TableField("tech_senior_count")
    private String techSeniorCount;

    /**
     * (技师)中级职称营养医师人数
     */
    @TableField("tech_middle_count")
    private String techMiddleCount;

    /**
     * (技师)初级职称营养医师人数
     */
    @TableField("tech_primary_count")
    private String techPrimaryCount;

    /**
     * 营养护士总人数
     */
    @TableField("nurse_count")
    private String nurseCount;

    /**
     * (护士)博士学历总人数
     */
    @TableField("nurse_bx_count")
    private String nurseBxCount;

    /**
     * (护士)硕士学历总人数
     */
    @TableField("nurse_sx_count")
    private String nurseSxCount;

    /**
     * (护士)本科学历总人数
     */
    @TableField("nurse_bk_count")
    private String nurseBkCount;

    /**
     * (护士)高级职称营养医师人数
     */
    @TableField("nurse_senior_count")
    private String nurseSeniorCount;

    /**
     * (护士)中级职称营养医师人数
     */
    @TableField("nurse_middle_count")
    private String nurseMiddleCount;

    /**
     * (护士)初级职称营养医师人数
     */
    @TableField("nurse_primary_count")
    private String nursePrimaryCount;

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

    public static final String DOCTOR_COUNT = "doctor_count";

    public static final String DOCTOR_CLINIC_COUNT = "doctor_clinic_count";

    public static final String DOCTOR_PUBLIC_COUNT = "doctor_public_count";

    public static final String DOCTOR_MEDICINE_COUNT = "doctor_medicine_count";

    public static final String DOCTOR_BX_COUNT = "doctor_bx_count";

    public static final String DOCTOR_SX_COUNT = "doctor_sx_count";

    public static final String DOCTOR_BK_COUNT = "doctor_bk_count";

    public static final String DOCTOR_SENIOR_COUNT = "doctor_senior_count";

    public static final String DOCTOR_MIDDLE_COUNT = "doctor_middle_count";

    public static final String DOCTOR_PRIMARY_COUNT = "doctor_primary_count";

    public static final String TECH_COUNT = "tech_count";

    public static final String TECH_BX_COUNT = "tech_bx_count";

    public static final String TECH_SX_COUNT = "tech_sx_count";

    public static final String TECH_BK_COUNT = "tech_bk_count";

    public static final String TECH_SENIOR_COUNT = "tech_senior_count";

    public static final String TECH_MIDDLE_COUNT = "tech_middle_count";

    public static final String TECH_PRIMARY_COUNT = "tech_primary_count";

    public static final String NURSE_COUNT = "nurse_count";

    public static final String NURSE_BX_COUNT = "nurse_bx_count";

    public static final String NURSE_SX_COUNT = "nurse_sx_count";

    public static final String NURSE_BK_COUNT = "nurse_bk_count";

    public static final String NURSE_SENIOR_COUNT = "nurse_senior_count";

    public static final String NURSE_MIDDLE_COUNT = "nurse_middle_count";

    public static final String NURSE_PRIMARY_COUNT = "nurse_primary_count";

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
