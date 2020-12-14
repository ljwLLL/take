package com.hpower.param;

import com.hpower.enums.DataTypeEnum;
import com.hpower.validator.constraints.Enum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname 根据月度id查询月度信息
 * @Description TODO
 * @Date 2020/4/6 8:52 下午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "根据月度id查询月度信息", description = "根据月度id查询月度信息")
public class AnalyseOneParam extends BaseParam {

    @ApiModelProperty(value = "月度id")
    private Long monthId;

    @ApiModelProperty(value = "质控中心id", hidden = true)
    private Long qualityId;

    @ApiModelProperty(value = "月度,针对浙江省")
    private String month;


}
