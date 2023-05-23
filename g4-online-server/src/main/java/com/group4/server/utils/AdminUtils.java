package com.group4.server.utils;

import com.group4.server.pojo.Admin;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author AWEI
 * @version 1.0
 * @description: 操作员工具类
 * @date 2022/3/19 18:34
 */
public class AdminUtils {

    /**
     * @description: 获取当前登录操作员
     * @param:
     * @return
     */
    public static Admin getCurrentAdmin(){
        return (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
