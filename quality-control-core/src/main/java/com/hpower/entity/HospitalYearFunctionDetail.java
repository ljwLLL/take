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
 * 年度指标营养功能明细
 * </p>
 *
 * @author yangyang.jiang
 * @since 2020-03-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_hospital_year_function_detail")
public class HospitalYearFunctionDetail extends Model<HospitalYearFunctionDetail> {

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
     * 营养功能区
     */
    @TableField("nutrition_function")
    private String nutritionFunction;

    /**
     * 营养科门诊是否独立诊疗号
     */
    @TableField("whether_diagnostic_number")
    private Integer whetherDiagnosticNumber;

    /**
     * 营养门诊投诉例数
     */
    @TableField("complaint_count")
    private String complaintCount;

    /**
     * 营养代谢室归属
     */
    @TableField("nutrition_dept")
    private Integer nutritionDept;

    /**
     * 营养代谢室归属单位
     */
    @TableField("nutrition_dept_else")
    private String nutritionDeptElse;

    /**
     * 营养代谢室面积
     */
    @TableField("nutrition_area")
    private String nutritionArea;

    /**
     * 代谢检测工作人数
     */
    @TableField("metabolize_work_number")
    private String metabolizeWorkNumber;

    /**
     * 营养科对住院患者能量代谢测定例数
     */
    @TableField("hos_energy_number")
    private String hosEnergyNumber;

    /**
     * 营养科对住院患者体成分测定例数
     */
    @TableField("hos_component_number")
    private String hosComponentNumber;

    /**
     * 营养科门诊患者能量代谢测定例数
     */
    @TableField("dep_energy_number")
    private String depEnergyNumber;

    /**
     * 营养科门诊患者体成分测定例数
     */
    @TableField("dep_component_number")
    private String depComponentNumber;

    /**
     * 肠外营养配置室归属
     */
    @TableField("parenteral_dept")
    private Integer parenteralDept;

    /**
     * 肠外营养配置室归属单位
     */
    @TableField("parenteral_dept_else")
    private String parenteralDeptElse;

    /**
     * 肠外营养配置室面积
     */
    @TableField("parenteral_area")
    private String parenteralArea;

    /**
     * 肠外营养配置工作人数
     */
    @TableField("parenteral_work_number")
    private String parenteralWorkNumber;

    /**
     * 肠外营养治疗患者人数
     */
    @TableField("parenteral_patient_number")
    private String parenteralPatientNumber;

    /**
     * 肠外营养治疗不良事件发生例数
     */
    @TableField("parenteral_bad_number")
    private String parenteralBadNumber;

    /**
     * 肠内营养配置室归属
     */
    @TableField("enterice_dept")
    private Integer entericeDept;

    /**
     * 肠内营养配置室归属单位
     */
    @TableField("enterice_dept_else")
    private String entericeDeptElse;

    /**
     * 肠内营养配置室面积
     */
    @TableField("enterice_area")
    private String entericeArea;

    /**
     * 肠内营养配置工作人数
     */
    @TableField("enterice_work_number")
    private String entericeWorkNumber;

    /**
     * 肠内营养治疗患者人数
     */
    @TableField("enterice_patient_number")
    private String entericePatientNumber;

    /**
     * 肠内营养治疗不良事件发生数
     */
    @TableField("enterice_bad_number")
    private String entericeBadNumber;

    /**
     * 医疗膳食配置室归属
     */
    @TableField("diet_dept")
    private Integer dietDept;

    /**
     * 医疗膳食配置室面积
     */
    @TableField("diet_area")
    private String dietArea;

    /**
     * 营养烹饪技师人数
     */
    @TableField("nutrition_cooker_number")
    private String nutritionCookerNumber;

    /**
     * 营养膳食护士总数
     */
    @TableField("nutrition_nurse_number")
    private String nutritionNurseNumber;

    @TableField("diet_will_number")
    private String dietWillNumber;

    /**
     * 全院营养素调整膳食总例数
     */
    @TableField("diet_number")
    private String dietNumber;

    /**
     * 全院糖尿病膳食总例数
     */
    @TableField("diabetes_number")
    private String diabetesNumber;

    /**
     * 全院限制蛋白膳食总例数
     */
    @TableField("protein_number")
    private String proteinNumber;

    /**
     * 全院低嘌呤膳食总例数
     */
    @TableField("low_purine_number")
    private String lowPurineNumber;

    /**
     * 医疗膳食治疗不良时间发生数
     */
    @TableField("treat_bed_number")
    private String treatBedNumber;

    /**
     * 营养科专科病房床位数
     */
    @TableField("specialty_bed_number")
    private String specialtyBedNumber;

    /**
     * 住院病例数
     */
    @TableField("patient_number")
    private String patientNumber;

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

    public static final String NUTRITION_FUNCTION = "nutrition_function";

    public static final String WHETHER_DIAGNOSTIC_NUMBER = "whether_diagnostic_number";

    public static final String COMPLAINT_COUNT = "complaint_count";

    public static final String NUTRITION_DEPT = "nutrition_dept";

    public static final String NUTRITION_DEPT_ELSE = "nutrition_dept_else";

    public static final String NUTRITION_AREA = "nutrition_area";

    public static final String METABOLIZE_WORK_NUMBER = "metabolize_work_number";

    public static final String HOS_ENERGY_NUMBER = "hos_energy_number";

    public static final String HOS_COMPONENT_NUMBER = "hos_component_number";

    public static final String DEP_ENERGY_NUMBER = "dep_energy_number";

    public static final String DEP_COMPONENT_NUMBER = "dep_component_number";

    public static final String PARENTERAL_DEPT = "parenteral_dept";

    public static final String PARENTERAL_DEPT_ELSE = "parenteral_dept_else";

    public static final String PARENTERAL_AREA = "parenteral_area";

    public static final String PARENTERAL_WORK_NUMBER = "parenteral_work_number";

    public static final String PARENTERAL_PATIENT_NUMBER = "parenteral_patient_number";

    public static final String PARENTERAL_BAD_NUMBER = "parenteral_bad_number";

    public static final String ENTERICE_DEPT = "enterice_dept";

    public static final String ENTERICE_DEPT_ELSE = "enterice_dept_else";

    public static final String ENTERICE_AREA = "enterice_area";

    public static final String ENTERICE_WORK_NUMBER = "enterice_work_number";

    public static final String ENTERICE_PATIENT_NUMBER = "enterice_patient_number";

    public static final String ENTERICE_BAD_NUMBER = "enterice_bad_number";

    public static final String DIET_DEPT = "diet_dept";

    public static final String DIET_AREA = "diet_area";

    public static final String NUTRITION_COOKER_NUMBER = "nutrition_cooker_number";

    public static final String NUTRITION_NURSE_NUMBER = "nutrition_nurse_number";

    public static final String DIET_WILL_NUMBER = "diet_will_number";

    public static final String DIET_NUMBER = "diet_number";

    public static final String DIABETES_NUMBER = "diabetes_number";

    public static final String PROTEIN_NUMBER = "protein_number";

    public static final String LOW_PURINE_NUMBER = "low_purine_number";

    public static final String TREAT_BED_NUMBER = "treat_bed_number";

    public static final String SPECIALTY_BED_NUMBER = "specialty_bed_number";

    public static final String PATIENT_NUMBER = "patient_number";

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
