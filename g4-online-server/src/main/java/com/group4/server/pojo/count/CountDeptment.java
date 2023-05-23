package com.group4.server.pojo.count;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(value = "部门员工统计结果",description = "")
public class CountDeptment {
    private String departmentName;
    private Integer countDep;
}
