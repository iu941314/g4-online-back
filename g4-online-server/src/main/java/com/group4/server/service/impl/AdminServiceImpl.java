package com.group4.server.service.impl;

import com.group4.server.mapper.MenuMapper;
import com.group4.server.utils.AdminUtils;
import com.group4.server.config.security.component.JwtTokenUtil;
import com.group4.server.mapper.AdminMapper;
import com.group4.server.mapper.AdminRoleMapper;
import com.group4.server.mapper.RoleMapper;
import com.group4.server.service.IAdminService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group4.server.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author awei
 * @since 2022-03-13
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;//这个报红不用管
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private AdminRoleMapper adminRoleMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private MenuMapper menuMapper;

    /**
     * 登录之后返回token
     *
     * @param username
     * @param password
     * @param code
     * @param request
     * @return
     */
    @Override
    public RespBean login(String username, String password, String code, HttpServletRequest request) {
        String captcha = (String) request.getSession().getAttribute("captcha");
        if (StringUtils.isEmpty(code) || !captcha.equalsIgnoreCase(code)) {
            return RespBean.error("验证码输入错误，请重新输入！");
        }
        //登录
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (null == userDetails || !passwordEncoder.matches(password, userDetails.getPassword())) {
            return RespBean.error("用户名或密码不正确");
        }
        if (!userDetails.isEnabled()) {
            return RespBean.error("账号被禁用，请联系管理员！");
        }
        //更新security登录用户对象

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails
                , null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //生成token
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return RespBean.success("登录成功", tokenMap);
    }

    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    @Override
    public Admin getAdminByUserName(String username) {
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username", username).eq("enabled", true));
    }

    /**
     * @return
     * @description: 根据用户id查询菜单列表
     * @param:
     */
    @Override
    public List<Menu> getMenusByAdminId() {
        Integer adminId = ((Admin)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        //从redis获取菜单数据
        List<Menu> menus = (List<Menu>) valueOperations.get("menu_" + adminId);
        //如果为空，去数据库获取
        if (CollectionUtils.isEmpty(menus)) {
            menus = menuMapper.getMenusByAdminId(adminId);
            //将数据设置到Redis中
            valueOperations.set("menu_" + adminId, menus);
        }
        return menus;
    }

    /**
     * @return
     * @description: 根据用户id查询角色列表
     * @param: adminId
     */
    @Override
    public List<Role> getRoles(Integer adminId) {
        return roleMapper.getRoles(adminId);
    }

    /**
     * @return
     * @description: 获取所有操作员
     * @param: keywords
     */
    @Override
    public List<Admin> getAllAdmins(String keywords) {
        return adminMapper.getAllAdmins(AdminUtils.getCurrentAdmin().getId(), keywords);
    }

    /**
     * @return
     * @description: 跟新操作员角色
     * @param: adminId
     * @param: rids
     */
    @Override
    @Transactional
    public RespBean updateAdminRole(Integer adminId, Integer[] rids) {
        adminRoleMapper.delete(new QueryWrapper<AdminRole>().eq("adminId", adminId));
        Integer result = adminRoleMapper.addAdminRole(adminId, rids);
        if (rids.length == result) {
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    /**
     * @description: 更新用户密码
     * @param: oldPass
     * @param: pass
     * @param: adminId
     * @return
     */
    @Override
    public RespBean updateAdminPassword(String oldPass, String pass, Integer adminId) {
        Admin admin = adminMapper.selectById(adminId);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //判断旧密码是否正确
        if(encoder.matches(oldPass,admin.getPassword())){
            admin.setPassword(encoder.encode(pass));
            int result = adminMapper.updateById(admin);
            if(1==result){
                return RespBean.success("更新成功！");
            }
        }
        return RespBean.error("更新失败！");
    }

    /**
     * @description: 更新用户头像
     * @param: url
     * @param: id
     * @param: authentication
     * @return
     */
    @Override
    public RespBean updateAdminUserFace(String url, Integer id, Authentication authentication) {
        Admin admin = adminMapper.selectById(id);
        admin.setUserFace(url);
        int result = adminMapper.updateById(admin);
        if(1==result){
            Admin principal = (Admin) authentication.getPrincipal();
            principal.setUserFace(url);
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(admin,null,authentication.getAuthorities()));
            return RespBean.success("更新成功！",url);
        }
        return RespBean.error("更新失败！");
    }


}
