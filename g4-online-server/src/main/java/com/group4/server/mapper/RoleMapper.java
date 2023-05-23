package com.group4.server.mapper;

import com.group4.server.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author awei
 * @since 2022-03-13
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * @return
     * @description: 根据用户id查询角色列表
     * @param: adminId
     */
    List<Role> getRoles(Integer adminId);
}
