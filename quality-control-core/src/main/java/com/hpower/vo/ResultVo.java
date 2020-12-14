package com.hpower.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname 结果指标对象
 * @Description TODO
 * @Date 2020/3/31 9:47 上午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "结果指标对象",description = "结果指标对象")
public class ResultVo extends BaseVo{

    @ApiModelProperty(value = "NUT-O-01A不良事件发生数")
    private String nutoOneANumber;

    @ApiModelProperty(value = "NUT-O-01A治疗总例数")
    private String nutoOneACount;

    @ApiModelProperty(value = "NUT-O-01A比率")
    private String nutoOneAPerentage;

    @ApiModelProperty(value = "NUT-O-01B不良事件发生数")
    private String nutoOneBNumber;

    @ApiModelProperty(value = "NUT-O-01B治疗总例数")
    private String nutoOneBCount;

    @ApiModelProperty(value = "NUT-O-01B比率")
    private String nutoOneBPerentage;

    @ApiModelProperty(value = "NUT-O-01C不良事件发生数")
    private String nutoOneCNumber;

    @ApiModelProperty(value = "NUT-O-01C治疗总例数")
    private String nutoOneCCount;

    @ApiModelProperty(value = "NUT-O-01C比率")
    private String nutoOneCPerentage;

    @ApiModelProperty(value = "NUT-O-02投诉发生数")
    private String nutoTowNumber;

    @ApiModelProperty(value = "NUT-O-02治疗总例数")
    private String nutoTowCount;

    @ApiModelProperty(value = "NUT-O-02比率")
    private String nutoTowPerentage;
}
