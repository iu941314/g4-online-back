package com.group4.server.controller_g4;

import com.group4.server.pojo.RespBean;
import com.group4.server.service.IEmployeeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class G4_CountController {
    @Autowired
    private IEmployeeService employeeService;


    @ApiOperation(value = "员工婚姻统计")
    @GetMapping("g4_count1")
    public RespBean queryMarrige() {
        RespBean respBean = new RespBean();
        System.out.println("开始执行");
        respBean.setObj(employeeService.queryMarrige_count());
        System.out.println(respBean.getObj());
        return respBean;
    }


    @ApiOperation(value = "部门人员统计")
    @GetMapping("g4_countDep")
    public Map<String, Object> queryDeptmentCount() {
        return employeeService.queryDeptmentCount();
    }

    @ApiOperation(value = "男女比例统计")
    @GetMapping("g4_countSex")
    public RespBean querySextCount() {
        RespBean respBean = new RespBean();
        respBean.setObj(employeeService.countSex());
        return respBean;
    }

    @ApiOperation(value = "学历统计")
    @GetMapping("g4_countEdu")
    public RespBean queryEduCount() {
        RespBean respBean = new RespBean();
        respBean.setObj(employeeService.countEdu());
        respBean.setCode(200);
        respBean.setMessage("查询成功");
        return respBean;
    }


}
