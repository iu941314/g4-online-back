package com.group4.server.service;

import com.group4.server.pojo.MenuRole;
import com.group4.server.pojo.RespBean;
import com.baomidou.mybatisplus.extension.service.IService;


public interface IMenuRoleService extends IService<MenuRole> {


    RespBean updateMenuRole(Integer rid, Integer[] mids);
}
