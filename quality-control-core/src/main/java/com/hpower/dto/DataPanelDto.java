package com.hpower.dto;

import com.hpower.entity.Hospital;
import lombok.Data;

import java.util.List;

/**
 * @Classname 总览dto层
 * @Description TODO
 * @Date 2020/4/2 3:13 下午
 * @Created yangyang.jiang
 */
@Data
public class DataPanelDto {

    private String startMonth;

    private String endMonth;

    private String tag;

    private Long qualityId;

    private Long cityId;

    private String startYear;

    private String endYear;

    private Integer level;

    private List<Hospital> list;
}
