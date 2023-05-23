package com.group4.server.mapper;

import com.group4.server.pojo.MenuRole;
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
public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    /**
     * @return
     * @description: 更新角色菜单
     * @param: rid
     * @param: mids
     */
    Integer insertRecord(@Param("rid") Integer rid, @Param("mids") Integer[] mids);
}
