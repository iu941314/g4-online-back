package com.group4.server.mapper;

import com.group4.server.pojo.Department;
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
public interface DepartmentMapper extends BaseMapper<Department> {

    /**
     * @return
     * @description: 获取所有部门
     * @param:
     */
    List<Department> getAllDepartments(Integer parentId);

    /**
     * @return
     * @description: 添加部门
     * @param: dep
     */
    void addDep(Department dep);

    /**
     * @return
     * @description: 删除部门
     * @param: dep
     */
    void deleteDep(Department dep);

}
