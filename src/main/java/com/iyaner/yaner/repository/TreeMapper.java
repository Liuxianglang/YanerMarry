package com.iyaner.yaner.repository;

import com.iyaner.yaner.entity.utils.TreeVO;

import java.util.List;

public interface TreeMapper {
    /**
     * 根据ID查询其节点下的子菜单，若ID=0 则查找根菜单
     * @param id
     * @return
     */
    List<TreeVO> getRootTree(int id);

}
