package com.hpower.vo;

import lombok.Data;

import java.util.List;

/**
 * 树形结构
 *
 * @author yangyang.jiang
 * @create 2018-11-22 16:22
 **/
@Data
public class TreeVo<T> {

    private Long id;

    private Long parentId;

    private List<T> children;
}
