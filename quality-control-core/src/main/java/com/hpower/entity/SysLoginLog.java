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
 * 登陆日志记录信息
 * </p>
 *
 * @author yangyang.jiang
 * @since 2020-03-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_sys_login_log")
public class SysLoginLog extends Model<SysLoginLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 会话id
     */
    @TableField("session_id")
    private String sessionId;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 质控中心id
     */
    @TableField("quality_id")
    private Long qualityId;

    /**
     * 医院id
     */
    @TableField("hospital_id")
    private Long hospitalId;

    /**
     * 用户类型
     */
    @TableField("type")
    private String type;

    /**
     * 登陆时间
     */
    @TableField("login_time")
    private LocalDateTime loginTime;

    /**
     * 退出时间
     */
    @TableField("loginout_time")
    private LocalDateTime loginoutTime;

    /**
     * 设备类型
     */
    @TableField("device_class")
    private String deviceClass;

    /**
     * 设备名称
     */
    @TableField("device_name")
    private String deviceName;

    /**
     * 设备品牌
     */
    @TableField("device_brand")
    private String deviceBrand;

    /**
     * ip地址
     */
    @TableField("ip_address")
    private String ipAddress;

    /**
     * 省份
     */
    @TableField("province")
    private String province;

    /**
     * 城市
     */
    @TableField("city")
    private String city;

    /**
     * 区域
     */
    @TableField("area")
    private String area;

    /**
     * 浏览器
     */
    @TableField("browser")
    private String browser;

    /**
     * 操作系统
     */
    @TableField("os")
    private String os;

    /**
     * 用户代理
     */
    @TableField("user_agent")
    private String userAgent;

    /**
     * 备注
     */
    @TableField("reamrk")
    private String reamrk;

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

    public static final String SESSION_ID = "session_id";

    public static final String USER_ID = "user_id";

    public static final String QUALITY_ID = "quality_id";

    public static final String HOSPITAL_ID = "hospital_id";

    public static final String TYPE = "type";

    public static final String LOGIN_TIME = "login_time";

    public static final String LOGINOUT_TIME = "loginout_time";

    public static final String DEVICE_CLASS = "device_class";

    public static final String DEVICE_NAME = "device_name";

    public static final String DEVICE_BRAND = "device_brand";

    public static final String IP_ADDRESS = "ip_address";

    public static final String PROVINCE = "province";

    public static final String CITY = "city";

    public static final String AREA = "area";

    public static final String BROWSER = "browser";

    public static final String OS = "os";

    public static final String USER_AGENT = "user_agent";

    public static final String REAMRK = "reamrk";

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
