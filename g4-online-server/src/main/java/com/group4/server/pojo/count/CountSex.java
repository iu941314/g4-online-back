package com.group4.server.pojo.count;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(value = "部门员工统计结果",description = "")
public class CountSex {
    private String sex;
    private Integer countSex;
}
