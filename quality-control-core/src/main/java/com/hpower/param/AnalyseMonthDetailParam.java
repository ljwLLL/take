package com.hpower.param;

import com.hpower.enums.DataTypeEnum;
import com.hpower.enums.SexEnum;
import com.hpower.validator.constraints.Enum;
import com.hpower.vo.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Classname 根据医院id查询月度报表信息
 * @Description TODO
 * @Date 2020/4/6 8:24 下午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "根据医院id查询月度报表信息param", description = "根据医院id查询月度报表信息")
public class AnalyseMonthDetailParam extends BasePageParam {

    @ApiModelProperty(value = "医院id")
    private Long hospitalId;

    @ApiModelProperty(value = "质控中心id", hidden = true)
    private Long qualityId;
}
