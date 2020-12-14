package com.hpower.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Classname 根据编码获取下一级信息
 * @Description TODO
 * @Date 2020/3/27 2:04 下午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "根据编码获取下一级信息", description = "根据编码获取下一级信息")
public class RegionParam {

    @ApiModelProperty("父级id,0表示获取所有省份的信息,省份id获取城市信息，城市id获取区域信息")
    @NotNull(message = "父级id不能为空")
    private Long id;
}
