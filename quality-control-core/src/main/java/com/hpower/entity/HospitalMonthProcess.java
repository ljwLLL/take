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
 * 月度指标流程信息表
 * </p>
 *
 * @author yangyang.jiang
 * @since 2020-03-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_hospital_month_process")
public class HospitalMonthProcess extends Model<HospitalMonthProcess> {

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
     * 操作人id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 操作人
     */
    @TableField("name")
    private String name;

    /**
     * 操作时间
     */
    @TableField("time")
    private LocalDateTime time;

    /**
     * 操作状态 0定时生成 1手动新增 2未上报 3重新编辑 4待审核 5已驳回 6已上传
     */
    @TableField("state")
    private Integer state;

    /**
     * 操作说明
     */
    @TableField("suggest")
    private String suggest;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 是否启用 0 未启用 1已启用
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

    public static final String USER_ID = "user_id";

    public static final String NAME = "name";

    public static final String TIME = "time";

    public static final String STATE = "state";

    public static final String SUGGEST = "suggest";

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
