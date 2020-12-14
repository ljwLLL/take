package com.hpower.param;

import com.hpower.validator.constraints.Password;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 重置密码请求参数对象,用于个人设置
 *
 * @author yangyang.jiang
 * @date 2020/03/28
 * @since 1.0
 */
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Data
@Builder
@ApiModel(value = "重置密码请求参数对象", description = "用于个人设置自己的账户密码")
public class ResetPasswordParam extends BaseParam {

    @NotNull(message = "用户ID不能为空")
    @ApiModelProperty(value = "用户ID", required = true, example = "1")
    private Long id;

    @ApiModelProperty(value = "旧密码", required = true, example = "123456", position = 2)
    @Length(min = 6, max = 20, message = "密码长度为6-20位")
    @Password(format = Password.PasswordFormat.NUMBER_LETTER, message = "密码格式错误")
    private String oldPassword;

    /**
     * 密码
     */
    @ApiModelProperty(value = "新密码", required = true, example = "123456", position = 2)
    @Length(min = 6, max = 20, message = "密码长度为6-20位")
    @Password(format = Password.PasswordFormat.NUMBER_LETTER, message = "密码格式错误")
    private String newPassword;


}
