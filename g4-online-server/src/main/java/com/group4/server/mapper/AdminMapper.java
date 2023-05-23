package com.group4.server.mapper;

import com.group4.server.pojo.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.group4.server.pojo.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author awei
 * @since 2022-03-13
 */
public interface AdminMapper extends BaseMapper<Admin> {

    /**
     * @return
     * @description: 获取所有操作员
     * @param: keywords
     */
    List<Admin> getAllAdmins(@Param("id") Integer id, @Param("keywords") String keywords);

}

