package com.group4.server.pojo.count;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(value = " 学历统计",description = "")
public class CountEdu {
    private String edu;
    private Integer countEdu;
}
