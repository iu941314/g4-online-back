package com.group4.server.controller_g4;

import com.group4.server.pojo.RespPageBean;
import com.group4.server.pojo.Salary;
import com.group4.server.service.ISalaryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 员工账套
 */
@RestController
@RequestMapping("/g4_salary/sobcfg")
public class G4_SalarySobcfgContronnler {
    @Autowired
    private ISalaryService salaryService;

    @ApiOperation(value = "获取所有工资账套")
    @GetMapping("/salaries")
    public List<Salary> getAllSalaries(){
        return  salaryService.list();
    }


}
