package com.hpower.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Classname 文件类型入参类
 * @Description TODO
 * @Date 2020/3/26 5:24 下午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "文件类型入参类", description = "文件类型入参类")
public class FileParam {

    @ApiModelProperty(value = "文件类型")
    @NotEmpty(message = "文件类型不能为空")
    private String fileType;
}
