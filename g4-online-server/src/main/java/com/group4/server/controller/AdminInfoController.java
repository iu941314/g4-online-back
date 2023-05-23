package com.group4.server.controller;

import com.group4.server.pojo.Admin;
import com.group4.server.pojo.RespBean;
import com.group4.server.service.IAdminService;
//import com.awei.server.utils.FastDFSUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author AWEI
 * @version 1.0
 * @description: 个人中心
 * @date 2022/3/27 20:36
 */
@RestController
public class AdminInfoController {

    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "更新当前用户信息")
    @PutMapping("/admin/info")
    public RespBean updateAdmin(@RequestBody Admin admin, Authentication authentication){
        if(adminService.updateById(admin)){
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(admin,null,authentication.getAuthorities()));
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "更新用户密码")
    @PutMapping("/admin/pass")
    public RespBean updateAdminPasswors(@RequestBody Map<String,Object> info){
        String oldPass = (String)info.get("oldPass");
        String pass = (String)info.get("pass");
        Integer adminId = (Integer)info.get("adminId");
        return adminService.updateAdminPassword(oldPass,pass,adminId);
    }

//    @ApiOperation(value = "更新用户头像")
//    @PostMapping("/admin/userface")
//    public RespBean updateAdminUserFace(MultipartFile file,Integer id,Authentication authentication){
//        String[] filePath = FastDFSUtils.upload(file);
//        String url = FastDFSUtils.getTrackerUrl() + filePath[0] + "/" + filePath[1];
//        return adminService.updateAdminUserFace(url,id,authentication);
//    }

}
