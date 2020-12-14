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
 * 质控中心信息表
 * </p>
 *
 * @author yangyang.jiang
 * @since 2020-03-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_quality_control")
public class QualityControl extends Model<QualityControl> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 质控中心名称
     */
    @TableField("name")
    private String name;

    /**
     * 省份id
     */
    @TableField("province_id")
    private Long provinceId;

    /**
     * 省份名称
     */
    @TableField("province_name")
    private String provinceName;

    /**
     * 市级id
     */
    @TableField("city_id")
    private Long cityId;

    /**
     * 市级名称
     */
    @TableField("city_name")
    private String cityName;

    /**
     * 区域id
     */
    @TableField("area_id")
    private Long areaId;

    /**
     * 区域名称
     */
    @TableField("area_name")
    private String areaName;

    /**
     * 质控中心网站
     */
    @TableField("website")
    private String website;

    /**
     * 质控中心联系人
     */
    @TableField("contact")
    private String contact;

    /**
     * 质控中心电话
     */
    @TableField("contact_phone")
    private String contactPhone;

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
     * 删除标志 0已删除 1 未删除
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

    public static final String NAME = "name";

    public static final String PROVINCE_ID = "province_id";

    public static final String PROVINCE_NAME = "province_name";

    public static final String CITY_ID = "city_id";

    public static final String CITY_NAME = "city_name";

    public static final String AREA_ID = "area_id";

    public static final String AREA_NAME = "area_name";

    public static final String WEBSITE = "website";

    public static final String CONTACT = "contact";

    public static final String CONTACT_PHONE = "contact_phone";

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
