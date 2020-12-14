package com.hpower.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hpower.support.ITree;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 用户菜单视图对象
 *
 * @author yangyang.jiang
 * @date 2020/03/26
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@ApiModel(value = "用户菜单视图对象", description = "用户返回登录用户的菜单树")
@NoArgsConstructor
@AllArgsConstructor
public class UserMenuVo extends BaseVo implements ITree<UserMenuVo> {
    /**
     * 主键，自增
     */
    @ApiModelProperty(value = "主键", required = true, example = "1")
    private Long id;

    /**
     * 父菜单ID
     */
    @ApiModelProperty(value = "父菜单ID", required = true, example = "0", position = 1)
    @JsonProperty("pid")
    private Long parentId;

    /**
     * 菜单编码
     */
    @ApiModelProperty(value = "菜单编码", example = "menu", position = 2)
    @JsonProperty("c")
    private String code;

    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称", required = true, example = "菜单管理", position = 3)
    @JsonProperty("n")
    private String name;

    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "菜单图标", example = "menu.png", position = 4)
    @JsonProperty("i")
    private String icon;

    /**
     * 链接地址，可以是页面地址，也可以是函数事件
     */
    @ApiModelProperty(value = "链接地址，可以是页面地址，也可以是函数事件", example = "menu.html", position = 5)
    @JsonProperty("h")
    private String href;

    /**
     * 类型:1、module，2、menu，3、button
     */
    @ApiModelProperty(value = "类型:1、module，2、menu，3、button", required = true, example = "1", position = 6)
    @JsonProperty("t")
    private Integer type;

    /**
     * 显示顺序
     */
    @ApiModelProperty(value = "显示顺序", example = "1", position = 7)
    @JsonProperty("o")
    private Integer orderNum;


    /**
     * 子列表
     */
    @ApiModelProperty(value = "子列表，用于获取菜单树时才有数据", required = false, position = 9)
    @JsonProperty("l")
    private List<UserMenuVo> children;
}
