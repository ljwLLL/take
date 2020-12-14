package com.hpower.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(value = "年度历史记录",description = "年度历史记录")
public class AnalyseYearHistoryVO extends BaseVo {

    /**
     * 信息的id  月度/年度 报表的id
     */
    private Long id;

    /**
     * 医院id
     */
    @TableField("hospital_id")
    private Long hospitalId;
    /**
     * 医院名称
     */
    @ApiModelProperty(value = "医院名称")
    private String hospitalName;

    /**
     * 系统指定的时间 年份
     */
    @ApiModelProperty(value = "上报年份时间")
    private String year;

    /**
     * 上报时间
     */
    @ApiModelProperty(value = "上报时间")
    private LocalDateTime time;

    /**
     * 上报人姓名
     */
    @ApiModelProperty(value = "上报人姓名")
    private String name;

    /**
     * 部门
     */
    @ApiModelProperty(value = "部门")
    private String dept;

    /**
     * 职务
     */
    @ApiModelProperty(value = "职务")
    private String post;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @ApiModelProperty(value = "上报状态,已上报和未上报")
    private Integer state;


}
