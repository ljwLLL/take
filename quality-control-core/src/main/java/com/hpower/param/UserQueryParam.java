package com.hpower.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Classname 用户查询入参对象
 * @Description TODO
 * @Date 2020/3/27 4:07 下午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "用户查询入参对象", description = "用户查询入参对象")
public class UserQueryParam extends BasePageParam {

    @ApiModelProperty(value = "全部账户数据权限" , hidden = true)
    public static final String TYPE_ALL = "0";

    @ApiModelProperty(value = "部分账户数据权限" ,hidden = true)
    public static final String TYPE_ONLY = "1";

    @ApiModelProperty(value = "医院id")
    private Long hospitalId;

    @ApiModelProperty(value = "用户名称")
    private String name;

    @ApiModelProperty(value = "质控中心id", hidden = true)
    private Long qualityId;

    @ApiModelProperty(value = "数据类型" , hidden = true)
    private  String type;

    @ApiModelProperty(value = "医院字符串" ,hidden = true)
    private List<Long> list;
}
