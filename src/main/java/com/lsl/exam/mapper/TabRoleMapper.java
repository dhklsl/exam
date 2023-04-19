package com.lsl.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsl.exam.entity.TabRole;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TabRoleMapper extends BaseMapper<TabRole> {

    /**
     * 查询用户拥有的角色信息
     * @param map
     * @return
     */
    List<Map> qryRoleInfoByUserId(Map map);
}
