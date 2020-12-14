package com.hpower.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname 新闻公告查询对象
 * @Description TODO
 * @Date 2020/4/3 4:48 下午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "新闻公告查询对象",description = "新闻公告查询对象")
public class NoticeParam extends BasePageParam {

    @ApiModelProperty(value = "新闻公告标题")
    private String title;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;
}
