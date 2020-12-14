package com.hpower.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Classname 月度报表结果指标
 * @Description TODO
 * @Date 2020/3/30 5:28 下午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "月度报表结果指标", description = "月度报表结果指标")
public class ResultParam extends BaseParam {

    @ApiModelProperty(value = "NUT-O-01A不良事件发生数")
    @NotEmpty(message = "NUT-O-01A不良事件发生数不能为空")
    private String nutoOneANumber;

    @ApiModelProperty(value = "NUT-O-01A治疗总例数")
    @NotEmpty(message = "NUT-O-01A治疗总例数不能为空")
    private String nutoOneACount;

    @ApiModelProperty(value = "NUT-O-01A比率")
    @NotEmpty(message = "NUT-O-01A比率不能为空")
    private String nutoOneAPerentage;

    @ApiModelProperty(value = "NUT-O-01B不良事件发生数")
    @NotEmpty(message = "NUT-O-01B不良事件发生数不能为空")
    private String nutoOneBNumber;

    @ApiModelProperty(value = "NUT-O-01B治疗总例数")
    @NotEmpty(message = "NUT-O-01B治疗总例数不能为空")
    private String nutoOneBCount;

    @ApiModelProperty(value = "NUT-O-01B比率")
    @NotEmpty(message = "NUT-O-01B比率不能为空")
    private String nutoOneBPerentage;

    @ApiModelProperty(value = "NUT-O-01C不良事件发生数")
    @NotEmpty(message = "NUT-O-01C不良事件发生数不能为空")
    private String nutoOneCNumber;

    @ApiModelProperty(value = "NUT-O-01C治疗总例数")
    @NotEmpty(message = "NUT-O-01C治疗总例数不能为空")
    private String nutoOneCCount;

    @ApiModelProperty(value = "NUT-O-01C比率")
    @NotEmpty(message = "NUT-O-01C比率不能为空")
    private String nutoOneCPerentage;

    @ApiModelProperty(value = "NUT-O-02投诉发生数")
    @NotEmpty(message = "NUT-O-02投诉发生数不能为空")
    private String nutoTowNumber;

    @ApiModelProperty(value = "NUT-O-02治疗总例数")
    @NotEmpty(message = "NUT-O-02治疗总例数不能为空")
    private String nutoTowCount;

    @ApiModelProperty(value = "NUT-O-02比率")
    @NotEmpty(message = "NUT-O-02比率不能为空")
    private String nutoTowPerentage;
}
