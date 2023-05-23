package com.group4.server.service;

import com.group4.server.pojo.Employee;
import com.group4.server.pojo.RespBean;
import com.group4.server.pojo.RespPageBean;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public interface IEmployeeService extends IService<Employee> {
    /**
     * @return
     * @description: 获取所有员工（分页）
     * @param: currentPage
     * @param: size
     * @param: employee
     * @param: beginDateScope
     */
    RespPageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope);

    /**
     * @return
     * @description: 获取工号
     * @param:
     */
    RespBean maxWorkID();

    /**
     * @return
     * @description: 添加员工
     * @param: employee
     */
    RespBean addEmp(Employee employee);

    /**
     * @return
     * @description: 查询员工
     * @param: id
     */
    List<Employee> getEmployee(Integer id);

    /**
     * @return
     * @description: 获取所有员工账套
     * @param: currentPage
     * @param: size
     */
    RespPageBean getEmployeeWithSalary(Integer currentPage, Integer size);

    /**
     * 获取所有工资账套ByG4
     * @param currentPage
     * @param size
     * @return
     */
    RespPageBean getEmployeeAndSalary(Integer currentPage, Integer size);

//

    Map<String,Object> queryMarrige_count();

    Map<String, Object> queryDeptmentCount();
    Map<String, Object> countSex();
    Map<String, Object> countEdu();
}
