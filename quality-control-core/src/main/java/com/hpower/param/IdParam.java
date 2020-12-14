package com.hpower.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;


/**
 * 主键请求参数对象
 *
 * @author yangyang.jiang
 * @date 2020/03/28
 * @since 1.0
 */
@Data
@ApiModel("主键参数对象")
public class IdParam extends BaseParam {
    @NotNull(message = "主键不能为空")
    @ApiModelProperty(value = "主键", required = true, example = "1", notes = "根据实际接口传递不同对象的主键")
    private Long id;

}
