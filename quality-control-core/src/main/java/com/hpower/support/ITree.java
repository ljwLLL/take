package com.hpower.support;

import java.util.List;

/**
 * 树接口
 *
 * @param <T> 泛型参数
 * @author yangyang.jiang
 * @date 2020/03/26
 * @since 1.0
 */
public interface ITree<T extends ITree> {

    /**
     * 主键
     *
     * @return ID
     */
    Long getId();

    /**
     * 父ID
     *
     * @return 父ID
     */
    Long getParentId();

    /**
     * 获取子列表
     *
     * @return 子列表
     */
    List<T> getChildren();

    /**
     * 设置子列表
     *
     * @param children 子列表
     * @return 当前对象
     */
    T setChildren(List<T> children);

    /**
     * 设置选中状态
     *
     * @param checked 选中状态，true：选中，否则未选中
     * @return 当前对象
     */
    default T setChecked(boolean checked) {
        return (T) this;
    }
}
