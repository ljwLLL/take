package com.hpower.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 数据字典值
 *
 * @author yangyang.jiang
 * @create 2020/03/27
 **/
@Data
@ApiModel(value = "字典数据对象", description = "字典数据对象")
public class DictDataVo {

    @ApiModelProperty(value = "字典数据编码")
    private String code;

    @ApiModelProperty(value = "字典数据名称")
    private String name;
}
