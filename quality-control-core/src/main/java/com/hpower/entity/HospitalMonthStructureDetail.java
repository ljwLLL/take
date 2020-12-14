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
 * 月度指标结构指标明细
 * </p>
 *
 * @author yangyang.jiang
 * @since 2020-03-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_hospital_month_structure_detail")
public class HospitalMonthStructureDetail extends Model<HospitalMonthStructureDetail> {

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
     * 营养科医师总数
     */
    @TableField("nuts_one_number")
    private String nutsOneNumber;

    /**
     * 同期医院编制床位数(医师)
     */
    @TableField("nuts_one_count")
    private String nutsOneCount;

    /**
     * 医师百分比
     */
    @TableField("nuts_one_perentage")
    private String nutsOnePerentage;

    /**
     * 营养科护士总数
     */
    @TableField("nuts_tow_number")
    private String nutsTowNumber;

    /**
     * 同期医院编制床位数(护士)
     */
    @TableField("nuts_tow_count")
    private String nutsTowCount;

    /**
     * 护士百分比
     */
    @TableField("nuts_tow_perentage")
    private String nutsTowPerentage;

    /**
     * 营养科技师总数
     */
    @TableField("nuts_three_number")
    private String nutsThreeNumber;

    /**
     * 同期医院编制床位数(技师)
     */
    @TableField("nuts_three_count")
    private String nutsThreeCount;

    /**
     * 技师百分比
     */
    @TableField("nuts_three_perentage")
    private String nutsThreePerentage;

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

    public static final String NUTS_ONE_NUMBER = "nuts_one_number";

    public static final String NUTS_ONE_COUNT = "nuts_one_count";

    public static final String NUTS_ONE_PERENTAGE = "nuts_one_perentage";

    public static final String NUTS_TOW_NUMBER = "nuts_tow_number";

    public static final String NUTS_TOW_COUNT = "nuts_tow_count";

    public static final String NUTS_TOW_PERENTAGE = "nuts_tow_perentage";

    public static final String NUTS_THREE_NUMBER = "nuts_three_number";

    public static final String NUTS_THREE_COUNT = "nuts_three_count";

    public static final String NUTS_THREE_PERENTAGE = "nuts_three_perentage";

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
