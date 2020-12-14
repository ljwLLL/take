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
 * 医院信息表
 * </p>
 *
 * @author yangyang.jiang
 * @since 2020-03-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_hospital")
public class Hospital extends Model<Hospital> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 质控中心id
     */
    @TableField("quality_id")
    private Long qualityId;

    /**
     * 医院编码
     */
    @TableField("code")
    private String code;

    /**
     * 医院名称
     */
    @TableField("name")
    private String name;

    /**
     * 医院级别 字典值 hospital_level
     */
    @TableField("level")
    private Integer level;

    /**
     * 省份id
     */
    @TableField("province_code")
    private Long provinceCode;

    /**
     * 省份名称
     */
    @TableField("province_name")
    private String provinceName;

    /**
     * 市级id
     */
    @TableField("city_code")
    private Long cityCode;

    /**
     * 市级名称
     */
    @TableField("city_name")
    private String cityName;

    /**
     * 区域id
     */
    @TableField("area_code")
    private Long areaCode;

    /**
     * 区域名称
     */
    @TableField("area_name")
    private String areaName;

    /**
     * 是否质控标志
     */
    @TableField("quality_control_sign")
    private Integer qualityControlSign;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 启用状态 0 未启用 1 已启用
     */
    @TableField("enabled")
    private Integer enabled;

    /**
     * 删除标志 0 已删除 1 未删除
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

    public static final String QUALITY_ID = "quality_id";

    public static final String CODE = "code";

    public static final String NAME = "name";

    public static final String LEVEL = "level";

    public static final String PROVINCE_CODE = "province_code";

    public static final String PROVINCE_NAME = "province_name";

    public static final String CITY_CODE = "city_code";

    public static final String CITY_NAME = "city_name";

    public static final String AREA_CODE = "area_code";

    public static final String AREA_NAME = "area_name";

    public static final String QUALITY_CONTROL_SIGN = "quality_control_sign";

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
