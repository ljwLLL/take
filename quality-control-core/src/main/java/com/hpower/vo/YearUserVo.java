package com.hpower.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname 年份报表用户信息
 * @Description TODO
 * @Date 2020/4/1 3:56 下午
 * @Created yangyang.jiang
 */
@Data
@ApiModel(value = "年份报表用户信息",description = "年份报表用户信息")
public class YearUserVo extends BaseVo{

    @ApiModelProperty(value = "营养医师总数")
    private String doctorCount;

    @ApiModelProperty(value = "(医师)临床执业医师总数")
    private String doctorClinicCount;

    @ApiModelProperty(value = "(医师)公卫执业医师总数")
    private String doctorPublicCount;

    @ApiModelProperty(value = "(医师)中医类执业医师总数")
    private String doctorMedicineCount;

    @ApiModelProperty(value = "(医师)博士学历总数")
    private String doctorBxCount;

    @ApiModelProperty(value = "(医师)硕士学历总数")
    private String doctorSxCount;

    @ApiModelProperty(value = "(医师)本科学历总数")
    private String doctorBkCount;

    @ApiModelProperty(value = "(医师)高级职称总数")
    private String doctorSeniorCount;

    @ApiModelProperty(value = "(医师)中级职称总数")
    private String doctorMiddleCount;

    @ApiModelProperty(value = "(医师)初级职称总数")
    private String doctorPrimaryCount;

    @ApiModelProperty(value = "营养技师总数")
    private String techCount;

    @ApiModelProperty(value = "(技师)博士学历总数")
    private String techBxCount;

    @ApiModelProperty(value = "(技师)硕士学历总数")
    private String techSxCount;

    @ApiModelProperty(value = "(技师)本科学历总数")
    private String techBkCount;

    @ApiModelProperty(value = "(技师)高级职称总数")
    private String techSeniorCount;

    @ApiModelProperty(value = "(技师)中级职称总数")
    private String techMiddleCount;

    @ApiModelProperty(value = "(技师)初级职称总数")
    private String techPrimaryCount;

    @ApiModelProperty(value = "营养护士总数")
    private String nurseCount;

    @ApiModelProperty(value = "(护士)博士学历总数")
    private String nurseBxCount;

    @ApiModelProperty(value = "(护士)硕士学历总数")
    private String nurseSxCount;

    @ApiModelProperty(value = "(护士)本科学历总数")
    private String nurseBkCount;

    @ApiModelProperty(value = "(护士)高级职称总数")
    private String nurseSeniorCount;

    @ApiModelProperty(value = "(护士)中级职称总数")
    private String nurseMiddleCount;

    @ApiModelProperty(value = "(护士)初级职称总数")
    private String nursePrimaryCount;
}
