package com.group4.server.controller;

import com.group4.server.pojo.Admin;
import com.group4.server.service.IAdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author AWEI
 * @version 1.0
 * @description: 在线聊天
 * @date 2022/3/22 10:31
 */
@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "获取所有操作员")
    @GetMapping("/admin")
    public List<Admin> getAllAdmins(String keywords){
        return adminService.getAllAdmins(keywords);
    }

}
