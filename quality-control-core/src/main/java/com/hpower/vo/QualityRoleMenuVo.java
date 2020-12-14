package com.hpower.vo;

import lombok.Data;

import java.util.List;

/**
 * @author yangyang.jiang
 * @Description:
 * @create 2020/5/9
 * @time 1:52 下午
 */
@Data
public class QualityRoleMenuVo {

    private List<RoleMenuVo>  roleMenuVos;

    private String ids;
}
