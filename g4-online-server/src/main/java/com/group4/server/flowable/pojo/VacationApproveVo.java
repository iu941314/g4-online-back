package com.group4.server.flowable.pojo;

import lombok.Data;

import java.util.Date;

/**
 * 请假条审批
 * @Date
 */
@Data
public class VacationApproveVo {

    private String taskId;

    private Boolean approve;

    private String name;
}


