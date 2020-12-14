package com.hpower.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @Classname 查询用户信息返回对象
 * @Description TODO
 * @Date 2020/3/27 4:11 下午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "查询用户信息返回对象", description = "查询用户信息返回对象")
public class UserVo extends BaseVo {

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "医院名称")
    private String hospitalName;

    @ApiModelProperty(value = "用户名称")
    private String name;

    @ApiModelProperty(value = "科室名称")
    private String deptName;

    @ApiModelProperty(value = "职务")
    private String post;

    @ApiModelProperty(value = "电话号码")
    private String mobile;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "是否启用 0未启用 1已启用")
    private Integer enabled;
}
