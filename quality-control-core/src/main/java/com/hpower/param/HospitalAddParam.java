package com.hpower.param;

import com.hpower.validator.groups.AddGroup;
import com.hpower.validator.groups.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Classname 医院新增对象
 * @Description TODO
 * @Date 2020/3/30 10:59 上午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "医院新增对象", description = "医院新增对象")
public class HospitalAddParam extends BaseParam {

    @ApiModelProperty(value = "医院id")
    @NotNull(message = "医院id不能为空", groups = {UpdateGroup.class})
    private Long id;

    @ApiModelProperty(value = "医院编码")
    @NotEmpty(message = "医院编码不能为空")
    private String code;

    @ApiModelProperty(value = "医院名称")
    @NotEmpty(message = "医院名称不能为空")
    private String name;

    @ApiModelProperty(value = "医院等级")
    @NotNull(message = "医院等级不能为空")
    private Integer level;

    @ApiModelProperty(value = "省份id")
    @NotNull(message = "省份id不能为空")
    private Long provinceCode;

    @ApiModelProperty(value = "城市id")
    @NotNull(message = "城市id不能为空")
    private Long cityCode;

    @ApiModelProperty(value = "区域id")
    @NotNull(message = "区域id不能为空")
    private Long areaCode;

    @ApiModelProperty(value = "启用状态")
    @NotNull(message = "启用状态不能为空", groups = {AddGroup.class})
    private Integer enabled;
}
