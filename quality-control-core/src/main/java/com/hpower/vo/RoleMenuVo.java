package com.hpower.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hpower.support.ITree;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author yangyang.jiang
 * @Description:
 * @create 2020/5/8
 * @time 7:27 下午
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenuVo extends BaseVo implements ITree<RoleMenuVo> {

    private Long id;

    private Long parentId;

    @JsonProperty("label")
    private String name;

    private List<RoleMenuVo> children;

    private Boolean flag = false;
}
