package com.hpower.util;

import com.hpower.support.ITree;
import com.hpower.vo.TreeVo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 集合转树形对象
 *
 * @author yangyang.jiang
 * @create 2018-11-22 16:12
 **/
public final class TreeUtil {
    /**
     * 使用递归方法建树
     *
     * @param list    列表数据
     * @param rootIds 指定根ID，默认为0
     * @param <T>     数据类型，必须继承{@link ITree}接口
     * @return 树形列表
     */
    public static <T extends ITree> List<T> toTree(List<T> list, Long... rootIds) {
        return toTree(list, true, rootIds);
    }

    /**
     * 使用递归方法建树
     *
     * @param list               列表数据
     * @param ignoreTrunkChecked 是否忽略树干节点选中状态，true：忽略，否则返回实际选中状态，用于前端展示半选状态不需要返回树干节点状态
     * @param rootIds            指定根ID，默认为0
     * @param <T>                数据类型，必须继承{@link ITree}接口
     * @return 树形列表
     */
    public static <T extends ITree> List<T> toTree(List<T> list, boolean ignoreTrunkChecked, Long... rootIds) {
        Long rootId = 0L;
        if (rootIds != null && rootIds.length > 0) {
            rootId = rootIds[0];
        }
        List<T> trees = new ArrayList<>();
        for (T t : list) {
            if (rootId.equals(t.getParentId())) {
                trees.add(findChildren(t, list, ignoreTrunkChecked));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     *
     * @param parent 父节点
     * @param list   列表数据
     * @param <T>
     * @return 父节点
     */
    private static <T extends ITree> T findChildren(T parent, List<T> list, boolean ignoreTrunkChecked) {
        for (T t : list) {
            if (parent.getId().equals(t.getParentId())) {
                if (parent.getChildren() == null) {
                    parent.setChildren(new ArrayList<T>());
                }
                parent.getChildren().add(findChildren(t, list, ignoreTrunkChecked));
            }
        }
        //当前节点包含子节点则把选中状态设置为false，树干节点状态根据叶子节点来确认
        if (ignoreTrunkChecked && parent.getChildren() != null && !parent.getChildren().isEmpty()) {
            parent.setChecked(false);
        }
        return parent;
    }
}
