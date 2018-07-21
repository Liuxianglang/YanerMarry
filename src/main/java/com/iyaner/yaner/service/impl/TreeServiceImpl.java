package com.iyaner.yaner.service.impl;

import com.iyaner.yaner.entity.utils.TreeVO;
import com.iyaner.yaner.repository.TreeMapper;
import com.iyaner.yaner.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreeServiceImpl implements TreeService {

    @Autowired
    private TreeMapper treeMapper;
    @Override
    public List<TreeVO> getRootTree(int id) {
        return treeMapper.getRootTree(id);
    }
}
