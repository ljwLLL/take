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
 * 重置密码,用于管理员账户
 *
 * @author jianwen.liu
 * @date 2020/05/08
 * @since 1.0
 */
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Data
@Builder
@ApiModel(value = "重置密码请求参数对象,用于管理员", description = "用于管理员重置其他用户密码")
public class PasswordRestParam {

    @NotNull(message = "用户ID不能为空")
    @ApiModelProperty(value = "用户ID", required = true, example = "1")
    private Long id;

    /**
     * 密码
     */
    @ApiModelProperty(value = "新密码", required = true, example = "123456", position = 2)
    @Length(min = 6, max = 20, message = "密码长度为6-20位")
    @Password(format = Password.PasswordFormat.NUMBER_LETTER, message = "密码格式错误")
    private String newPassword;
}
