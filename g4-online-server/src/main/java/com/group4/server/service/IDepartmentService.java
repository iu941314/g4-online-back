package com.group4.server.service;

import com.group4.server.pojo.Department;
import com.group4.server.pojo.RespBean;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author awei
 * @since 2022-03-13
 */
public interface IDepartmentService extends IService<Department> {

    /**
     * @return
     * @description: 获取所有部门
     * @param:
     */
    List<Department> getAllDepartments();

    /**
     * @return
     * @description: 添加部门
     * @param: dep
     */
    RespBean addDep(Department dep);

    /**
     * @return
     * @description: 删除部门
     * @param: id
     */
    RespBean deleteDep(Integer id);
}
