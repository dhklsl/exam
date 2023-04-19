package com.lsl.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lsl.exam.entity.TabRole;

import java.util.List;
import java.util.Map;

public interface ITabRoleService extends IService<TabRole> {

    /**
     * 查询用户拥有的角色
     * @param map
     * @return
     */
    List<Map> qryRoleInfoByUserId(Map map);
}
