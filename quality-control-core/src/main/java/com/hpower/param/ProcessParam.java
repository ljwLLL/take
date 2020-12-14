package com.hpower.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Classname 月度报表过程指标
 * @Description TODO
 * @Date 2020/3/30 5:42 下午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "月度报表过程指标", description = "月度报表过程指标")
public class ProcessParam extends BaseParam {

    @ApiModelProperty(value = "NUTP-P-01患者数")
    @NotEmpty(message = "NUTP-P-01患者数不能为空")
    private String nutpOneNumber;

    @ApiModelProperty(value = "NUTP-P-01住院患者数")
    @NotEmpty(message = "NUTP-P-01住院患者数不能为空")
    private String nutpOneCount;

    @ApiModelProperty(value = "NUTP-P-01比率")
    @NotEmpty(message = "NUTP-P-01比率")
    private String nutpPerentage;

    @ApiModelProperty(value = "NUTP-P-02患者数")
    @NotEmpty(message = "NUTP-P-02患者数不能为空")
    private String nutpTowNumber;

    @ApiModelProperty(value = "NUTP-P-02住院患者总数")
    @NotEmpty(message = "NUTP-P-02住院患者总数不能为空")
    private String nutpTowCount;

    @ApiModelProperty(value = "NUTP-P-02比率")
    @NotEmpty(message = "NUTP-P-02比率不能为空")
    private String nutpTowPerentage;

    @ApiModelProperty(value = "NUTP-P-03肠外治疗数")
    @NotEmpty(message = "NUTP-P-03肠外治疗数不能为空")
    private String nutpThreeNumber;

    @ApiModelProperty(value = "NUTP-P-03住院患者总数")
    @NotEmpty(message = "NUTP-P-03住院患者总数不能为空")
    private String nutpThreeCount;

    @ApiModelProperty(value = "NUTP-P-03比率")
    @NotEmpty(message = "NUTP-P-03比率不能为空")
    private String nutpThreePerentage;

    @ApiModelProperty(value = "NUTP-P-04治疗数")
    @NotEmpty(message = "NUTP-P-04治疗数不能为空")
    private String nutpFourNumber;

    @ApiModelProperty(value = "NUTP-P-04治疗总数")
    @NotEmpty(message = "NUTP-P-04治疗总数不能为空")
    private String nutpFourCount;

    @ApiModelProperty(value = "NUTP-P-04比率")
    @NotEmpty(message = "NUTP-P-04比率不能为空")
    private String nutpFourPerentage;

    @ApiModelProperty(value = "NUTP-P-05治疗数")
    @NotEmpty(message = "NUTP-P-05治疗数不能为空")
    private String nutpFiveNumber;

    @ApiModelProperty(value = "NUTP-P-05治疗总数")
    @NotEmpty(message = "NUTP-P-05治疗总数不能为空")
    private String nutpFiveCount;

    @ApiModelProperty(value = "NUTP-P-05比率")
    @NotEmpty(message = "NUTP-P-05比率不能为空")
    private String nutpFivePerentage;

    @ApiModelProperty(value = "NUTP-P-06治疗数")
    @NotEmpty(message = "NUTP-P-06治疗数不能为空")
    private String nutpSixNumber;

    @ApiModelProperty(value = "NUTP-P-06治疗总数")
    @NotEmpty(message = "NUTP-P-06治疗总数不能为空")
    private String nutpSixCount;

    @ApiModelProperty(value = "NUTP-P-06比率")
    @NotEmpty(message = "NUTP-P-06比率不能为空")
    private String nutpSixPerentage;

    @ApiModelProperty(value = "NUTP-P-07A治疗数")
    @NotEmpty(message = "NUTP-P-07A治疗数不能为空")
    private String nutpSevenANumber;

    @ApiModelProperty(value = "NUTP-P-07A治疗总数")
    @NotEmpty(message = "NUTP-P-07A治疗总数不能为空")
    private String nutpSevenACount;

    @ApiModelProperty(value = "NUTP-P-07A比率")
    @NotEmpty(message = "NUTP-P-07A比率不能为空")
    private String nutpSevenAPerentage;

    @ApiModelProperty(value = "NUTP-P-07B治疗数")
    @NotEmpty(message = "NUTP-P-07B治疗数不能为空")
    private String nutpSevenBNumber;

    @ApiModelProperty(value = "NUTP-P-07B治疗总数")
    @NotEmpty(message = "NUTP-P-07B治疗总数不能为空")
    private String nutpSevenBCount;

    @ApiModelProperty(value = "NUTP-P-07B比率")
    @NotEmpty(message = "NUTP-P-07B比率不能为空")
    private String nutpSevenBPerentage;

    @ApiModelProperty(value = "NUTP-P-07C治疗数")
    @NotEmpty(message = "NUTP-P-07C治疗数不能为空")
    private String nutpSevenCNumber;

    @ApiModelProperty(value = "NUTP-P-07C治疗总数")
    @NotEmpty(message = "NUTP-P-07C治疗总数不能为空")
    private String nutpSevenCCount;

    @ApiModelProperty(value = "NUTP-P-07C比率")
    @NotEmpty(message = "NUTP-P-07C比率不能为空")
    private String nutpSevenCPerentage;

    @ApiModelProperty(value = "NUTP-P-07D治疗数")
    @NotEmpty(message = "NUTP-P-07D治疗数不能为空")
    private String nutpSevenDNumber;

    @ApiModelProperty(value = "NUTP-P-07D治疗总数")
    @NotEmpty(message = "NUTP-P-07D治疗总数不能为空")
    private String nutpSevenDCount;

    @ApiModelProperty(value = "NUTP-P-07D比率")
    @NotEmpty(message = "NUTP-P-07D比率不能为空")
    private String nutpSevenDPerentage;

    @ApiModelProperty(value = "NUTP-P-07E治疗数")
    @NotEmpty(message = "NUTP-P-07E治疗数不能为空")
    private String nutpSevenENumber;

    @ApiModelProperty(value = "NUTP-P-07E治疗总数")
    @NotEmpty(message = "NUTP-P-07E治疗总数不能为空")
    private String nutpSevenECount;

    @ApiModelProperty(value = "NUTP-P-07E比率")
    @NotEmpty(message = "NUTP-P-07E比率不能为空")
    private String nutpSevenEPerentage;

    @ApiModelProperty(value = "NUTP-P-07F治疗数")
    @NotEmpty(message = "NUTP-P-07F治疗数不能为空")
    private String nutpSevenFNumber;

    @ApiModelProperty(value = "NUTP-P-07F治疗总数")
    @NotEmpty(message = "NUTP-P-07F治疗总数不能为空")
    private String nutpSevenFCount;

    @ApiModelProperty(value = "NUTP-P-07F比率")
    @NotEmpty(message = "NUTP-P-07F比率不能为空")
    private String nutpSevenFPerentage;

    @ApiModelProperty(value = "NUTP-P-07G治疗数")
    @NotEmpty(message = "NUTP-P-07G治疗数不能为空")
    private String nutpSevenGNumber;

    @ApiModelProperty(value = "NUTP-P-07G治疗总数")
    @NotEmpty(message = "NUTP-P-07G治疗总数不能为空")
    private String nutpSevenGCount;

    @ApiModelProperty(value = "NUTP-P-07G比率")
    @NotEmpty(message = "NUTP-P-07G比率不能为空")
    private String nutpSevenGPerentage;

    @ApiModelProperty(value = "NUTP-P-08A治疗数")
    @NotEmpty(message = "NUTP-P-08A治疗数不能为空")
    private String nutpEightANumber;

    @ApiModelProperty(value = "NUTP-P-08A治疗总数")
    @NotEmpty(message = "NUTP-P-08A治疗总数不能为空")
    private String nutpEightACount;

    @ApiModelProperty(value = "NUTP-P-08A比率")
    @NotEmpty(message = "NUTP-P-08A比率不能为空")
    private String nutpEightAPerentage;

    @ApiModelProperty(value = "NUTP-P-08B治疗数")
    @NotEmpty(message = "NUTP-P-08B治疗数不能为空")
    private String nutpEightBNumber;

    @ApiModelProperty(value = "NUTP-P-08B治疗总数")
    @NotEmpty(message = "NUTP-P-08B治疗总数不能为空")
    private String nutpEightBCount;

    @ApiModelProperty(value = "NUTP-P-08B比率")
    @NotEmpty(message = "NUTP-P-08B比率不能为空")
    private String nutpEightBPerentage;

    @ApiModelProperty(value = "NUTP-P-09A治疗数")
    @NotEmpty(message = "NUTP-P-09A治疗数不能为空")
    private String nutpNineANumber;

    @ApiModelProperty(value = "NUTP-P-09A治疗总数")
    @NotEmpty(message = "NUTP-P-09A治疗总数不能为空")
    private String nutpNineACount;

    @ApiModelProperty(value = "NUTP-P-09A比率")
    @NotEmpty(message = "NUTP-P-09A比率不能为空")
    private String nutpNineAPerentage;

    @ApiModelProperty(value = "NUTP-P-09B治疗数")
    @NotEmpty(message = "NUTP-P-09B治疗数不能为空")
    private String nutpNineBNumber;

    @ApiModelProperty(value = "NUTP-P-09B治疗总数")
    @NotEmpty(message = "NUTP-P-09B治疗总数不能为空")
    private String nutpNineBCount;

    @ApiModelProperty(value = "NUTP-P-09B比率")
    @NotEmpty(message = "NUTP-P-09B比率不能为空")
    private String nutpNineBPerentage;

    @ApiModelProperty(value = "NUTP-P-10治疗数")
    @NotEmpty(message = "NUTP-P-10治疗数不能为空")
    private String nutpTenNumber;

    @ApiModelProperty(value = "NUTP-P-10治疗总数")
    @NotEmpty(message = "NUTP-P-10治疗总数不能为空")
    private String nutpTenCount;

    @ApiModelProperty(value = "NUTP-P-10比率")
    @NotEmpty(message = "NUTP-P-10比率不能为空")
    private String nutpTenPerentage;
}
