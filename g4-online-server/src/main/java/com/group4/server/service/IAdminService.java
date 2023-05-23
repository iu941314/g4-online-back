package com.group4.server.service;

import com.group4.server.pojo.Admin;
import com.group4.server.pojo.Menu;
import com.group4.server.pojo.RespBean;
import com.group4.server.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author awei
 * @since 2022-03-13
 */
public interface IAdminService extends IService<Admin> {

    /**
     * 登录之后返回token
     *
     * @param username
     * @param password
     * @param code
     * @param request
     * @return
     */
    RespBean login(String username, String password, String code, HttpServletRequest request);

    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    Admin getAdminByUserName(String username);

    /**
     * @return
     * @description: 根据用户id查询菜单列表
     * @param:
     */
    List<Menu> getMenusByAdminId();

    /**
     * 根据用户id查询角色列表
     *
     * @param adminId
     * @return
     */
    List<Role> getRoles(Integer adminId);

    /**
     * @return
     * @description: 获取所有操作员
     * @param: keywords
     */
    List<Admin> getAllAdmins(String keywords);

    /**
     * @return
     * @description: 更新操作员角色
     * @param: adminId
     * @param: rids
     */
    RespBean updateAdminRole(Integer adminId, Integer[] rids);

    /**
     * @description: 更新用户密码
     * @param: oldPass
     * @param: pass
     * @param: adminId
     * @return
     */
    RespBean updateAdminPassword(String oldPass, String pass, Integer adminId);

    /**
     * @description: 更新用户头像
     * @param: url
     * @param: id
     * @param: authentication
     * @return
     */
    RespBean updateAdminUserFace(String url, Integer id, Authentication authentication);


}
