package com.lsl.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsl.exam.entity.TabRole;
import com.lsl.exam.mapper.TabRoleMapper;
import com.lsl.exam.service.ITabRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TabRoleServiceImpl extends ServiceImpl<TabRoleMapper, TabRole> implements ITabRoleService {

    @Autowired
    TabRoleMapper roleMapper;

    /**
     * 查询用户拥有的角色
     * @param map
     * @return
     */
    @Override
    public List<Map> qryRoleInfoByUserId(Map map) {
        return roleMapper.qryRoleInfoByUserId(map);
    }
}
