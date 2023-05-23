package com.group4.server.mapper;

import com.group4.server.pojo.Menu;
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
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * @return
     * @description: 根据用户id查询菜单列表
     * @param: id
     */
    List<Menu> getMenusByAdminId(Integer id);

    /**
     * @return
     * @description: 根据角色获取菜单列表
     * @param:
     */
    List<Menu> getMenusWithRole();

    /**
     * @return
     * @description: 查询所有菜单
     * @param:
     */
    List<Menu> getAllMenus();
}
