package com.hpower.param;

import com.hpower.enums.SexEnum;
import com.hpower.validator.constraints.Enum;
import com.hpower.validator.constraints.Password;
import com.hpower.validator.groups.AddGroup;
import com.hpower.validator.groups.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Classname 用户新增对象
 * @Description TODO
 * @Date 2020/3/28 9:22 下午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "用户新增对象", description = "用户新增对象")
public class UserAddParam extends BaseParam {

    @ApiModelProperty(value = "用户id")
    @NotNull(message = "用户id不能为空", groups = {UpdateGroup.class})
    private Long id;

    @ApiModelProperty(value = "医院id")
    private Long hospitalId;

    @ApiModelProperty(value = "姓名")
    @NotEmpty(message = "姓名不能为空")
    private String name;

    @ApiModelProperty(value = "密码")
    @Length(min = 6, max = 20, message = "密码长度为6-20位", groups = {AddGroup.class})
    @Password(format = Password.PasswordFormat.NUMBER_LETTER, message = "密码必须同时包含数字、字母", groups = {AddGroup.class})
    private String password;

    @ApiModelProperty(value = "性别")
    @Enum(message = "{lis.validator.constraints.SexEnum.message}", target = {SexEnum.class})
    private Integer sex;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "科室")
    private String department;

    @ApiModelProperty(value = "职位")
    private String post;

    @ApiModelProperty(value = "手机号码")
    @NotEmpty(message = "手机号码不能为空")
    @Pattern(regexp = "^1[3456789]\\d{9}$", message = "手机号码格式不正确")
    private String mobile;

    @ApiModelProperty(value = "是否启用")
    @NotNull(message = "是否启用不能为空", groups = {AddGroup.class})
    private Integer enabled;
}
