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
 * 月度指标过程指标明细
 * </p>
 *
 * @author yangyang.jiang
 * @since 2020-03-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_hospital_month_process_detail")
public class HospitalMonthProcessDetail extends Model<HospitalMonthProcessDetail> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 月度结构id
     */
    @TableField("month_id")
    private Long monthId;

    /**
     * 营养风险筛查患者数
     */
    @TableField("nutp_one_number")
    private String nutpOneNumber;

    /**
     * 同期住院患者总数(筛查)
     */
    @TableField("nutp_one_count")
    private String nutpOneCount;

    /**
     * 筛查百分比
     */
    @TableField("nutp_perentage")
    private String nutpPerentage;

    /**
     * 存在营养风险的患者
     */
    @TableField("nutp_tow_number")
    private String nutpTowNumber;

    /**
     * 同期筛查患者总数(风险)
     */
    @TableField("nutp_tow_count")
    private String nutpTowCount;

    /**
     * 风险百分比
     */
    @TableField("nutp_tow_perentage")
    private String nutpTowPerentage;

    /**
     * 全院肠外营养治疗例数
     */
    @TableField("nutp_three_number")
    private String nutpThreeNumber;

    /**
     * 同期住院患者总数(全员肠外)
     */
    @TableField("nutp_three_count")
    private String nutpThreeCount;

    /**
     * 全院肠外百分比
     */
    @TableField("nutp_three_perentage")
    private String nutpThreePerentage;

    /**
     * 营养科肠外营养治疗例数
     */
    @TableField("nutp_four_number")
    private String nutpFourNumber;

    /**
     * 同期全院肠外营养治疗总例数(营养科肠外)
     */
    @TableField("nutp_four_count")
    private String nutpFourCount;

    /**
     * 营养科肠外百分比
     */
    @TableField("nutp_four_perentage")
    private String nutpFourPerentage;

    /**
     * 全院肠内营养治疗例数
     */
    @TableField("nutp_five_number")
    private String nutpFiveNumber;

    /**
     * 同期住院患者总数(全院肠内)
     */
    @TableField("nutp_five_count")
    private String nutpFiveCount;

    /**
     * 全院肠内百分比
     */
    @TableField("nutp_five_perentage")
    private String nutpFivePerentage;

    /**
     * 营养科肠内营养治疗例数
     */
    @TableField("nutp_six_number")
    private String nutpSixNumber;

    /**
     * 同期全院肠内营养治疗总例数(营养科肠内)
     */
    @TableField("nutp_six_count")
    private String nutpSixCount;

    /**
     * 营养科肠内百分比
     */
    @TableField("nutp_six_perentage")
    private String nutpSixPerentage;

    /**
     * 普通膳食完成例数
     */
    @TableField("nutp_seven_a_number")
    private String nutpSevenANumber;

    /**
     * 同期普通膳食医嘱住院患者数(营养膳食)
     */
    @TableField("nutp_seven_a_count")
    private String nutpSevenACount;

    /**
     * 营养膳食百分比
     */
    @TableField("nutp_seven_a_perentage")
    private String nutpSevenAPerentage;

    /**
     * 软食完成例数
     */
    @TableField("nutp_seven_b_number")
    private String nutpSevenBNumber;

    /**
     * 同期软食医嘱住院患者数(软食)
     */
    @TableField("nutp_seven_b_count")
    private String nutpSevenBCount;

    /**
     * 软食百分比
     */
    @TableField("nutp_seven_b_perentage")
    private String nutpSevenBPerentage;

    /**
     * 半流质饮食完成例数
     */
    @TableField("nutp_seven_c_number")
    private String nutpSevenCNumber;

    /**
     * 同期半流质饮食医嘱患者数(半流质)
     */
    @TableField("nutp_seven_c_count")
    private String nutpSevenCCount;

    /**
     * 半流质百分比
     */
    @TableField("nutp_seven_c_perentage")
    private String nutpSevenCPerentage;

    @TableField("nutp_seven_d_number")
    private String nutpSevenDNumber;

    /**
     * 同期流质饮食医嘱患者数
     */
    @TableField("nutp_seven_d_count")
    private String nutpSevenDCount;

    @TableField("nutp_seven_d_perentage")
    private String nutpSevenDPerentage;

    /**
     * 糖尿病膳食完成例数
     */
    @TableField("nutp_seven_e_number")
    private String nutpSevenENumber;

    /**
     * 同期糖尿病膳食医嘱患者数
     */
    @TableField("nutp_seven_e_count")
    private String nutpSevenECount;

    /**
     * 糖尿病膳食百分比
     */
    @TableField("nutp_seven_e_perentage")
    private String nutpSevenEPerentage;

    /**
     * 限制蛋白质膳食完成例数
     */
    @TableField("nutp_seven_f_number")
    private String nutpSevenFNumber;

    /**
     * 同期限制蛋白质膳食医嘱患者数
     */
    @TableField("nutp_seven_f_count")
    private String nutpSevenFCount;

    /**
     * 限制蛋白质百分比
     */
    @TableField("nutp_seven_f_perentage")
    private String nutpSevenFPerentage;

    /**
     * 低嘌呤膳食完成例数
     */
    @TableField("nutp_seven_g_number")
    private String nutpSevenGNumber;

    /**
     * 同期低嘌呤膳食医嘱患者数
     */
    @TableField("nutp_seven_g_count")
    private String nutpSevenGCount;

    /**
     * 低嘌呤百分比
     */
    @TableField("nutp_seven_g_perentage")
    private String nutpSevenGPerentage;

    /**
     * 住院患者静息能量消耗测定例数
     */
    @TableField("nutp_eight_a_number")
    private String nutpEightANumber;

    /**
     * 同期住院患者总数(静息)
     */
    @TableField("nutp_eight_a_count")
    private String nutpEightACount;

    /**
     * 住院患者静息百分比
     */
    @TableField("nutp_eight_a_perentage")
    private String nutpEightAPerentage;

    /**
     * 营养门诊静息能量消耗测定例数
     */
    @TableField("nutp_eight_b_number")
    private String nutpEightBNumber;

    /**
     * 同期营养门诊患者总数
     */
    @TableField("nutp_eight_b_count")
    private String nutpEightBCount;

    /**
     * 门诊静息百分比
     */
    @TableField("nutp_eight_b_perentage")
    private String nutpEightBPerentage;

    /**
     * 住院患者体成分测定例数
     */
    @TableField("nutp_nine_a_number")
    private String nutpNineANumber;

    /**
     * 同期住院患者总数(患者)
     */
    @TableField("nutp_nine_a_count")
    private String nutpNineACount;

    /**
     * 患者体成分百分比
     */
    @TableField("nutp_nine_a_perentage")
    private String nutpNineAPerentage;

    /**
     * 营养门诊体成分测定例
     */
    @TableField("nutp_nine_b_number")
    private String nutpNineBNumber;

    /**
     * 同期营养门诊患者总数(营养门诊)
     */
    @TableField("nutp_nine_b_count")
    private String nutpNineBCount;

    /**
     * 营养门诊体百分比
     */
    @TableField("nutp_nine_b_perentage")
    private String nutpNineBPerentage;

    /**
     * 营养门诊就诊人数
     */
    @TableField("nutp_ten_number")
    private String nutpTenNumber;

    /**
     * 同期医院门诊就诊总数
     */
    @TableField("nutp_ten_count")
    private String nutpTenCount;

    /**
     * 营养门诊人数百分比
     */
    @TableField("nutp_ten_perentage")
    private String nutpTenPerentage;

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

    public static final String NUTP_ONE_NUMBER = "nutp_one_number";

    public static final String NUTP_ONE_COUNT = "nutp_one_count";

    public static final String NUTP_PERENTAGE = "nutp_perentage";

    public static final String NUTP_TOW_NUMBER = "nutp_tow_number";

    public static final String NUTP_TOW_COUNT = "nutp_tow_count";

    public static final String NUTP_TOW_PERENTAGE = "nutp_tow_perentage";

    public static final String NUTP_THREE_NUMBER = "nutp_three_number";

    public static final String NUTP_THREE_COUNT = "nutp_three_count";

    public static final String NUTP_THREE_PERENTAGE = "nutp_three_perentage";

    public static final String NUTP_FOUR_NUMBER = "nutp_four_number";

    public static final String NUTP_FOUR_COUNT = "nutp_four_count";

    public static final String NUTP_FOUR_PERENTAGE = "nutp_four_perentage";

    public static final String NUTP_FIVE_NUMBER = "nutp_five_number";

    public static final String NUTP_FIVE_COUNT = "nutp_five_count";

    public static final String NUTP_FIVE_PERENTAGE = "nutp_five_perentage";

    public static final String NUTP_SIX_NUMBER = "nutp_six_number";

    public static final String NUTP_SIX_COUNT = "nutp_six_count";

    public static final String NUTP_SIX_PERENTAGE = "nutp_six_perentage";

    public static final String NUTP_SEVEN_A_NUMBER = "nutp_seven_a_number";

    public static final String NUTP_SEVEN_A_COUNT = "nutp_seven_a_count";

    public static final String NUTP_SEVEN_A_PERENTAGE = "nutp_seven_a_perentage";

    public static final String NUTP_SEVEN_B_NUMBER = "nutp_seven_b_number";

    public static final String NUTP_SEVEN_B_COUNT = "nutp_seven_b_count";

    public static final String NUTP_SEVEN_B_PERENTAGE = "nutp_seven_b_perentage";

    public static final String NUTP_SEVEN_C_NUMBER = "nutp_seven_c_number";

    public static final String NUTP_SEVEN_C_COUNT = "nutp_seven_c_count";

    public static final String NUTP_SEVEN_C_PERENTAGE = "nutp_seven_c_perentage";

    public static final String NUTP_SEVEN_D_NUMBER = "nutp_seven_d_number";

    public static final String NUTP_SEVEN_D_COUNT = "nutp_seven_d_count";

    public static final String NUTP_SEVEN_D_PERENTAGE = "nutp_seven_d_perentage";

    public static final String NUTP_SEVEN_E_NUMBER = "nutp_seven_e_number";

    public static final String NUTP_SEVEN_E_COUNT = "nutp_seven_e_count";

    public static final String NUTP_SEVEN_E_PERENTAGE = "nutp_seven_e_perentage";

    public static final String NUTP_SEVEN_F_NUMBER = "nutp_seven_f_number";

    public static final String NUTP_SEVEN_F_COUNT = "nutp_seven_f_count";

    public static final String NUTP_SEVEN_F_PERENTAGE = "nutp_seven_f_perentage";

    public static final String NUTP_SEVEN_G_NUMBER = "nutp_seven_g_number";

    public static final String NUTP_SEVEN_G_COUNT = "nutp_seven_g_count";

    public static final String NUTP_SEVEN_G_PERENTAGE = "nutp_seven_g_perentage";

    public static final String NUTP_EIGHT_A_NUMBER = "nutp_eight_a_number";

    public static final String NUTP_EIGHT_A_COUNT = "nutp_eight_a_count";

    public static final String NUTP_EIGHT_A_PERENTAGE = "nutp_eight_a_perentage";

    public static final String NUTP_EIGHT_B_NUMBER = "nutp_eight_b_number";

    public static final String NUTP_EIGHT_B_COUNT = "nutp_eight_b_count";

    public static final String NUTP_EIGHT_B_PERENTAGE = "nutp_eight_b_perentage";

    public static final String NUTP_NINE_A_NUMBER = "nutp_nine_a_number";

    public static final String NUTP_NINE_A_COUNT = "nutp_nine_a_count";

    public static final String NUTP_NINE_A_PERENTAGE = "nutp_nine_a_perentage";

    public static final String NUTP_NINE_B_NUMBER = "nutp_nine_b_number";

    public static final String NUTP_NINE_B_COUNT = "nutp_nine_b_count";

    public static final String NUTP_NINE_B_PERENTAGE = "nutp_nine_b_perentage";

    public static final String NUTP_TEN_NUMBER = "nutp_ten_number";

    public static final String NUTP_TEN_COUNT = "nutp_ten_count";

    public static final String NUTP_TEN_PERENTAGE = "nutp_ten_perentage";

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
