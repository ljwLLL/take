package com.hpower.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 字典库
 *
 * @author yangyang.jiang
 * @create 2020/03/27
 **/
@Data
@ApiModel(value = "字典类型对象", description = "字典类型对象")
public class DictTypeVo {

    @JsonProperty(value = "t")
    @ApiModelProperty(value = "字典类型")
    private String dictType;

    @JsonProperty(value = "list")
    @ApiModelProperty(value = "字典数据集合")
    private List<DictDataVo> dictDataList;
}
