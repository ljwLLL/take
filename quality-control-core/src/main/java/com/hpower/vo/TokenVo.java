package com.hpower.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Classname token会话令牌
 * @Description TODO
 * @Date 2020/3/24 9:01 上午
 * @Created yangyang.jiang
 */
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Data
@Builder
@ApiModel(value = "会话令牌对象", description = "")
public class TokenVo extends BaseVo {
    @ApiModelProperty(value = "会话令牌", example = "1", notes = "后续接口需要通过http请求头携带该令牌")
    private String token;
}
