package com.group4.server.mapper;

import com.group4.server.pojo.AdminRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author awei
 * @since 2022-03-13
 */
public interface AdminRoleMapper extends BaseMapper<AdminRole> {

    /**
     * @return
     * @description: 跟新操作员角色
     * @param: adminId
     * @param: rids
     */
    Integer addAdminRole(@Param("adminId") Integer adminId, @Param("rids") Integer[] rids);
}
