package com.hpower.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Classname 新闻公告对象
 * @Description TODO
 * @Date 2020/4/3 4:44 下午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "新闻公告对象", description = "新闻公告对象")
public class NoticeVo extends BaseVo {

    @ApiModelProperty(value = "新闻公告id")
    private Long id;

    @ApiModelProperty(value = "新闻公告标题")
    private String title;

    @ApiModelProperty(value = "新闻公告说明")
    private String note;

    @ApiModelProperty(value = "附件id")
    private Long fileId;

    @ApiModelProperty(value = "发布时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "发布人")
    private String creatorName;
}
