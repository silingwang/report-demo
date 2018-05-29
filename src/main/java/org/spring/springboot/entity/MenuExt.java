package org.spring.springboot.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.*;

/**
 * <Description> 菜单扩展<br>
 */
@Getter
@Setter
@ToString
public class MenuExt implements Serializable {

    /**
     * 菜单ID
     */
    private Long id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单地址
     */
    private String url;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 父菜单ID
     */
    private Long parentId;
    /**
     * 菜单顺序
     */
    private Integer ordby;

    /**
     * 菜单状态
     */
    private String state;
    /**
     * 子菜单
     */
    private List<MenuExt> children = new ArrayList<MenuExt>();
    /**
     *
     * <Description> 孩子节点排序<br>
     *
     */
    public void sortChildren() {
        Collections.sort(children, new Comparator<MenuExt>() {
            @Override
            public int compare(MenuExt menu1, MenuExt menu2) {
                int result = 0;

                Integer ordby1 = menu1.getOrdby();
                Integer ordby2 = menu2.getOrdby();

                Long id1 = menu1.getId();
                Long id2 = menu2.getId();
                if (null != ordby1 && null != ordby2) {
                    result = (ordby1 < ordby2 ? -1 : (ordby1 == ordby2 ? 0 : 1));
                } else {
                    result = (id1 < id2 ? -1 : (id1 == id2 ? 0 : 1));
                }
                return result;
            }

        });
        // 对每个节点的下一层节点进行排序
        for (Iterator<MenuExt> it = children.iterator(); it.hasNext();) {
            it.next().sortChildren();
        }
    }
}
 