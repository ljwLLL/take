package com.hpower.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname 封装page对象
 * @Description TODO
 * @Date 2020/3/27 4:55 下午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "封装page对象")
public class PageVo<T> extends Page<T> {

    @ApiModelProperty(value = "已启用数量")
    private Integer enabledCount;

    @ApiModelProperty(value = "未启用数量")
    private Integer disabledCount;

    @ApiModelProperty(value = "总数量")
    private Integer count;
}
