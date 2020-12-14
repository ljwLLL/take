package com.hpower.dto;

import com.hpower.util.EncryptUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author yangyang, jiang
 * @date 2020/03/24
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "用户对象")
public class LoginUser extends BaseDto {

    public static final String HTTP_HEADER_NAME = "loginUser";

    @ApiModelProperty(value = "用户id")
    private Long id;

    @ApiModelProperty(value = "用户名称")
    private String name;

    @ApiModelProperty(value = "质控中心id")
    private Long qualityId;

    @ApiModelProperty(value = "医院id")
    private Long hospitalId;

    @ApiModelProperty(value = "电话号码")
    private String mobile;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "医院id串")
    private String hospitalIds;

    @ApiModelProperty(value = "用户区分是所有数据还是部分数据 0表示所有数据 1部分数据")
    private Integer permissionType;

    @ApiModelProperty(value = "省份id")
    private Long provinceId;

    @ApiModelProperty(value = "省份信息")
    private String provinceName;
}
