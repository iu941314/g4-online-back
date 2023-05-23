package com.group4.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author AWEI
 * @version 1.0
 * @description: 分页公共返回对象
 * @date 2022/3/20 9:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespPageBean {

    /**
     * 总条数
     */
    private Long total;
    /**
     * 数据List
     */
    private List<?> data;

}
