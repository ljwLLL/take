package com.hpower.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 下载文件对象入参
 *
 * @author yangyang.jiang
 * @create 2020/03/27
 **/
@Data
@ApiModel(value = "下载文件信息对象", description = "下载文件信息对象")
public class DownLoadFileParam {

    @ApiModelProperty(value = "附件id",example = "1")
    @NotNull(message = "附件id不能为空")
    private Long attachId;

    @ApiModelProperty(value = "下载方式")
    @NotNull(message = "下载方式不能为空")
    private String type;

    @ApiModelProperty(value = "下载文件自定义文件名")
    private String fileName;
}
