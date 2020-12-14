package com.hpower.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author LENOVO
 * @Classname 根据月份去查找浙江省的数据
 * @Description TODO
 * @Date 2020/6/23
 * @Created jianwen.liu
 */
@Data
@ApiModel(value = "查询浙江省月度报表的参数" ,description = "查询浙江省月度报表的参数")
public class AnalyseMonthAllParam  extends BasePageParam{
//    @ApiModelProperty(value = "月份")
//    private String month;
}
