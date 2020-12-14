package com.hpower.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname 过程指标对象
 * @Description TODO
 * @Date 2020/3/31 9:47 上午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "过程指标对象",description = "过程指标对象")
public class ProcessVo extends BaseVo{

    @ApiModelProperty(value = "NUTP-P-01患者数")
    private String nutpOneNumber;

    @ApiModelProperty(value = "NUTP-P-01住院患者数")
    private String nutpOneCount;

    @ApiModelProperty(value = "NUTP-P-01比率")
    private String nutpPerentage;

    @ApiModelProperty(value = "NUTP-P-02患者数")
    private String nutpTowNumber;

    @ApiModelProperty(value = "NUTP-P-02住院患者总数")
    private String nutpTowCount;

    @ApiModelProperty(value = "NUTP-P-02比率")
    private String nutpTowPerentage;

    @ApiModelProperty(value = "NUTP-P-03肠外治疗数")
    private String nutpThreeNumber;

    @ApiModelProperty(value = "NUTP-P-03住院患者总数")
    private String nutpThreeCount;

    @ApiModelProperty(value = "NUTP-P-03比率")
    private String nutpThreePerentage;

    @ApiModelProperty(value = "NUTP-P-04治疗数")
    private String nutpFourNumber;

    @ApiModelProperty(value = "NUTP-P-04治疗总数")
    private String nutpFourCount;

    @ApiModelProperty(value = "NUTP-P-04比率")
    private String nutpFourPerentage;

    @ApiModelProperty(value = "NUTP-P-05治疗数")
    private String nutpFiveNumber;

    @ApiModelProperty(value = "NUTP-P-05治疗总数")
    private String nutpFiveCount;

    @ApiModelProperty(value = "NUTP-P-05比率")
    private String nutpFivePerentage;

    @ApiModelProperty(value = "NUTP-P-06治疗数")
    private String nutpSixNumber;

    @ApiModelProperty(value = "NUTP-P-06治疗总数")
    private String nutpSixCount;

    @ApiModelProperty(value = "NUTP-P-06比率")
    private String nutpSixPerentage;

    @ApiModelProperty(value = "NUTP-P-07A治疗数")
    private String nutpSevenANumber;

    @ApiModelProperty(value = "NUTP-P-07A治疗总数")
    private String nutpSevenACount;

    @ApiModelProperty(value = "NUTP-P-07A比率")
    private String nutpSevenAPerentage;

    @ApiModelProperty(value = "NUTP-P-07B治疗数")
    private String nutpSevenBNumber;

    @ApiModelProperty(value = "NUTP-P-07B治疗总数")
    private String nutpSevenBCount;

    @ApiModelProperty(value = "NUTP-P-07B比率")
    private String nutpSevenBPerentage;

    @ApiModelProperty(value = "NUTP-P-07C治疗数")
    private String nutpSevenCNumber;

    @ApiModelProperty(value = "NUTP-P-07C治疗总数")
    private String nutpSevenCCount;

    @ApiModelProperty(value = "NUTP-P-07C比率")
    private String nutpSevenCPerentage;

    @ApiModelProperty(value = "NUTP-P-07D治疗数")
    private String nutpSevenDNumber;

    @ApiModelProperty(value = "NUTP-P-07D治疗总数")
    private String nutpSevenDCount;

    @ApiModelProperty(value = "NUTP-P-07D比率")
    private String nutpSevenDPerentage;

    @ApiModelProperty(value = "NUTP-P-07E治疗数")
    private String nutpSevenENumber;

    @ApiModelProperty(value = "NUTP-P-07E治疗总数")
    private String nutpSevenECount;

    @ApiModelProperty(value = "NUTP-P-07E比率")
    private String nutpSevenEPerentage;

    @ApiModelProperty(value = "NUTP-P-07F治疗数")
    private String nutpSevenFNumber;

    @ApiModelProperty(value = "NUTP-P-07F治疗总数")
    private String nutpSevenFCount;

    @ApiModelProperty(value = "NUTP-P-07F比率")
    private String nutpSevenFPerentage;

    @ApiModelProperty(value = "NUTP-P-07G治疗数")
    private String nutpSevenGNumber;

    @ApiModelProperty(value = "NUTP-P-07G治疗总数")
    private String nutpSevenGCount;

    @ApiModelProperty(value = "NUTP-P-07G比率")
    private String nutpSevenGPerentage;

    @ApiModelProperty(value = "NUTP-P-08A治疗数")
    private String nutpEightANumber;

    @ApiModelProperty(value = "NUTP-P-08A治疗总数")
    private String nutpEightACount;

    @ApiModelProperty(value = "NUTP-P-08A比率")
    private String nutpEightAPerentage;

    @ApiModelProperty(value = "NUTP-P-08B治疗数")
    private String nutpEightBNumber;

    @ApiModelProperty(value = "NUTP-P-08B治疗总数")
    private String nutpEightBCount;

    @ApiModelProperty(value = "NUTP-P-08B比率")
    private String nutpEightBPerentage;

    @ApiModelProperty(value = "NUTP-P-09A治疗数")
    private String nutpNineANumber;

    @ApiModelProperty(value = "NUTP-P-09A治疗总数")
    private String nutpNineACount;

    @ApiModelProperty(value = "NUTP-P-09A比率")
    private String nutpNineAPerentage;

    @ApiModelProperty(value = "NUTP-P-09B治疗数")
    private String nutpNineBNumber;

    @ApiModelProperty(value = "NUTP-P-09B治疗总数")
    private String nutpNineBCount;

    @ApiModelProperty(value = "NUTP-P-09B比率")
    private String nutpNineBPerentage;

    @ApiModelProperty(value = "NUTP-P-10治疗数")
    private String nutpTenNumber;

    @ApiModelProperty(value = "NUTP-P-10治疗总数")
    private String nutpTenCount;

    @ApiModelProperty(value = "NUTP-P-10比率")
    private String nutpTenPerentage;
}
