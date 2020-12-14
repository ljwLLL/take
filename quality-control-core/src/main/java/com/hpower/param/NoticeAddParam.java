package com.hpower.param;

import com.hpower.validator.groups.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Classname 新闻公告新增对象
 * @Description TODO
 * @Date 2020/4/3 5:02 下午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "新闻公告新增对象", description = "新闻公告新增对象")
public class NoticeAddParam extends BaseParam {

    @ApiModelProperty(value = "新闻id")
    @NotNull(message = "新闻id不能为空", groups = {UpdateGroup.class})
    private Long id;

    @ApiModelProperty(value = "标题")
    @NotEmpty(message = "标题不能为空")
    private String title;

    @ApiModelProperty(value = "文件说明")
    private String note;

    @ApiModelProperty(value = "附件id")
    private Long fileId;
}
