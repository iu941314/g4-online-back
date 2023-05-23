package com.group4.server.service;

import com.group4.server.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface IMenuService extends IService<Menu> {

    /**
     * 根据角色获取菜单列表
     *
     * @return
     */
    List<Menu> getMenusWithRole();

    /**
     * @return
     * @description: 查询所有菜单
     * @param:
     */
    List<Menu> getAllMenus();
}
