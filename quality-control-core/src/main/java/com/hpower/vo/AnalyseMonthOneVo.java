package com.hpower.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname 月度报表明细对象
 * @Description TODO
 * @Date 2020/4/6 8:48 下午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "月度报表明细对象", description = "月度报表明细对象")
public class AnalyseMonthOneVo extends BaseVo {

    @ApiModelProperty(value = "月度报表id")
    private Long monthId;

    //结构指标
    @ApiModelProperty(value = "NUT-S-01医室数")
    private Integer nutsOneNumber;

    @ApiModelProperty(value = "NUT-S-01床位数")
    private Integer nutsOneCount;

    @ApiModelProperty(value = "NUT-S-01医床比")
    private String nutsOnePerentage;


    @ApiModelProperty(value = "NUT-S-02医室数")
    private Integer nutsTowNumber;

    @ApiModelProperty(value = "NUT-S-02床位数")
    private Integer nutsTowCount;

    @ApiModelProperty(value = "NUT-S-02医床比")
    private String nutsTowPerentage;



    @ApiModelProperty(value = "NUT-S-03医室数")
    private Integer nutsThreeNumber;

    @ApiModelProperty(value = "NUT-S-03床位数")
    private Integer nutsThreeCount;

    @ApiModelProperty(value = "NUT-S-03医床比")
    private String nutsThreePerentage;



    //过程指标
    @ApiModelProperty(value = "NUTP-P-01患者数")
    private Integer nutpOneNumber;

    @ApiModelProperty(value = "NUTP-P-01住院患者数")
    private Integer nutpOneCount;

    @ApiModelProperty(value = "NUTP-P-01比率")
    private String nutpPerentage;



    @ApiModelProperty(value = "NUTP-P-02患者数")
    private Integer nutpTowNumber;

    @ApiModelProperty(value = "NUTP-P-02住院患者总数")
    private Integer nutpTowCount;

    @ApiModelProperty(value = "NUTP-P-02比率")
    private String nutpTowPerentage;


    @ApiModelProperty(value = "NUTP-P-03肠外治疗数")
    private Integer nutpThreeNumber;

    @ApiModelProperty(value = "NUTP-P-03住院患者总数")
    private Integer nutpThreeCount;

    @ApiModelProperty(value = "NUTP-P-03比率")
    private String nutpThreePerentage;



    @ApiModelProperty(value = "NUTP-P-04治疗数")
    private Integer nutpFourNumber;

    @ApiModelProperty(value = "NUTP-P-04治疗总数")
    private Integer nutpFourCount;

    @ApiModelProperty(value = "NUTP-P-04比率")
    private String nutpFourPerentage;


    @ApiModelProperty(value = "NUTP-P-05治疗数")
    private Integer nutpFiveNumber;

    @ApiModelProperty(value = "NUTP-P-05治疗总数")
    private Integer nutpFiveCount;

    @ApiModelProperty(value = "NUTP-P-05比率")
    private String nutpFivePerentage;


    @ApiModelProperty(value = "NUTP-P-06治疗数")
    private Integer nutpSixNumber;

    @ApiModelProperty(value = "NUTP-P-06治疗总数")
    private Integer nutpSixCount;

    @ApiModelProperty(value = "NUTP-P-06比率")
    private String nutpSixPerentage;


    @ApiModelProperty(value = "NUTP-P-07A治疗数")
    private Integer nutpSevenANumber;

    @ApiModelProperty(value = "NUTP-P-07A治疗总数")
    private Integer nutpSevenACount;

    @ApiModelProperty(value = "NUTP-P-07A比率")
    private String nutpSevenAPerentage;


    @ApiModelProperty(value = "NUTP-P-07B治疗数")
    private Integer nutpSevenBNumber;

    @ApiModelProperty(value = "NUTP-P-07B治疗总数")
    private Integer nutpSevenBCount;

    @ApiModelProperty(value = "NUTP-P-07B比率")
    private String nutpSevenBPerentage;


    @ApiModelProperty(value = "NUTP-P-07C治疗数")
    private Integer nutpSevenCNumber;

    @ApiModelProperty(value = "NUTP-P-07C治疗总数")
    private Integer nutpSevenCCount;

    @ApiModelProperty(value = "NUTP-P-07C比率")
    private String nutpSevenCPerentage;


    @ApiModelProperty(value = "NUTP-P-07D治疗数")
    private Integer nutpSevenDNumber;

    @ApiModelProperty(value = "NUTP-P-07D治疗总数")
    private Integer nutpSevenDCount;

    @ApiModelProperty(value = "NUTP-P-07D比率")
    private String nutpSevenDPerentage;


    @ApiModelProperty(value = "NUTP-P-07E治疗数")
    private Integer nutpSevenENumber;

    @ApiModelProperty(value = "NUTP-P-07E治疗总数")
    private Integer nutpSevenECount;

    @ApiModelProperty(value = "NUTP-P-07E比率")
    private String nutpSevenEPerentage;


    @ApiModelProperty(value = "NUTP-P-07F治疗数")
    private Integer nutpSevenFNumber;

    @ApiModelProperty(value = "NUTP-P-07F治疗总数")
    private Integer nutpSevenFCount;

    @ApiModelProperty(value = "NUTP-P-07F比率")
    private String nutpSevenFPerentage;



    @ApiModelProperty(value = "NUTP-P-07G治疗数")
    private Integer nutpSevenGNumber;

    @ApiModelProperty(value = "NUTP-P-07G治疗总数")
    private Integer nutpSevenGCount;

    @ApiModelProperty(value = "NUTP-P-07G比率")
    private String nutpSevenGPerentage;


    @ApiModelProperty(value = "NUTP-P-08A治疗数")
    private Integer nutpEightANumber;

    @ApiModelProperty(value = "NUTP-P-08A治疗总数")
    private Integer nutpEightACount;

    @ApiModelProperty(value = "NUTP-P-08A比率")
    private String nutpEightAPerentage;


    @ApiModelProperty(value = "NUTP-P-08B治疗数")
    private Integer nutpEightBNumber;

    @ApiModelProperty(value = "NUTP-P-08B治疗总数")
    private Integer nutpEightBCount;

    @ApiModelProperty(value = "NUTP-P-08B比率")
    private String nutpEightBPerentage;


    @ApiModelProperty(value = "NUTP-P-09A治疗数")
    private Integer nutpNineANumber;

    @ApiModelProperty(value = "NUTP-P-09A治疗总数")
    private Integer nutpNineACount;

    @ApiModelProperty(value = "NUTP-P-09A比率")
    private String nutpNineAPerentage;


    @ApiModelProperty(value = "NUTP-P-09B治疗数")
    private Integer nutpNineBNumber;

    @ApiModelProperty(value = "NUTP-P-09B治疗总数")
    private Integer nutpNineBCount;

    @ApiModelProperty(value = "NUTP-P-09B比率")
    private String nutpNineBPerentage;


    @ApiModelProperty(value = "NUTP-P-10治疗数")
    private Integer nutpTenNumber;

    @ApiModelProperty(value = "NUTP-P-10治疗总数")
    private Integer nutpTenCount;

    @ApiModelProperty(value = "NUTP-P-10比率")
    private String nutpTenPerentage;


    //结果指标
    @ApiModelProperty(value = "NUT-O-01A不良事件发生数")
    private Integer nutoOneANumber;

    @ApiModelProperty(value = "NUT-O-01A治疗总例数")
    private Integer nutoOneACount;

    @ApiModelProperty(value = "NUT-O-01A比率")
    private String nutoOneAPerentage;


    @ApiModelProperty(value = "NUT-O-01B不良事件发生数")
    private Integer nutoOneBNumber;

    @ApiModelProperty(value = "NUT-O-01B治疗总例数")
    private Integer nutoOneBCount;

    @ApiModelProperty(value = "NUT-O-01B比率")
    private String nutoOneBPerentage;


    @ApiModelProperty(value = "NUT-O-01C不良事件发生数")
    private Integer nutoOneCNumber;

    @ApiModelProperty(value = "NUT-O-01C治疗总例数")
    private Integer nutoOneCCount;

    @ApiModelProperty(value = "NUT-O-01C比率")
    private String nutoOneCPerentage;

    @ApiModelProperty(value = "NUT-O-02投诉发生数")
    private Integer nutoTowNumber;

    @ApiModelProperty(value = "NUT-O-02治疗总例数")
    private Integer nutoTowCount;

    @ApiModelProperty(value = "NUT-O-02比率")
    private String nutoTowPerentage;

    @ApiModelProperty(value = "填写人")
    private String name;

    @ApiModelProperty(value = "部门")
    private String dept;

    @ApiModelProperty(value = "职称")
    private String post;

    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @ApiModelProperty(value = "备注")
    private String remark;
}
