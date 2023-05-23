package com.group4.server.controller_g4;


import com.group4.server.pojo.RespPageBean;
import com.group4.server.service.IEmployeeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/g4_zhangtao")
public class G4_EmployeeTaoZhang {

    @Autowired
    private IEmployeeService employeeService;

    @ApiOperation(value = "查询员工账套")
    @PostMapping("/list")
    public RespPageBean queryAllEmpZhangTao(@RequestParam(defaultValue = "1") Integer currentPage ,
                                                 @RequestParam(defaultValue = "10") Integer size) {
        return employeeService.getEmployeeAndSalary(currentPage,size);
    }

}
