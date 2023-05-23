package com.group4.server.controller_g4;

import com.group4.server.pojo.RespBean;
import com.group4.server.pojo.Salary;
import com.group4.server.service.ISalaryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 工资账套
 */
@RestController
@RequestMapping("g4_salary/sob")
public class G4_salaryController {

    @Autowired
    private ISalaryService salaryService;

    @ApiOperation(value = "账套列表展示-所有")
    @GetMapping("/list")
    public List<Salary> getAllSalaries(){
        return salaryService.list();
    }


    @ApiOperation(value = "添加工资账套")
    @PostMapping("/add")
    public RespBean addSalaries(@RequestBody Salary salary){
//        添加创建时间
        salary.setCreateDate(LocalDateTime.now());
        if (salaryService.save(salary)){
            return RespBean.success("添加工资账套成功");
        }
        return RespBean.error("添加失败，请重试");
    }

    @ApiOperation(value = "删除工资账套")
    @PostMapping("/delete")
    public RespBean deleteSalaries(@RequestBody Integer id){
        if(salaryService.removeById(id)){
            return RespBean.success("删除套账成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "更新工资套账")
    @PostMapping("/update")
    public RespBean updateSalaties (@RequestBody Salary salary){
        if (salaryService.updateById(salary) ) {
            return RespBean.success("更新账套成功");
        }
        return RespBean.success("更新账套失败");
    }

}
