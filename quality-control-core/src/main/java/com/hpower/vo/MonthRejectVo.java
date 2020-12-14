package com.hpower.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname 查看驳回对象信息
 * @Description TODO
 * @Date 2020/3/31 1:05 下午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "查看驳回对象信息", description = "查看驳回对象信息")
public class MonthRejectVo extends BaseVo {

    @ApiModelProperty(value = "驳回备注")
    private String suggest;
}
