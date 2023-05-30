package com.group4.server.flowable.pojo;

import lombok.Data;

import java.util.Date;

/**
 * 请假条DO
 *
 * @author
 * @Date
 */
@Data
public class VacationInfo {

    private String name;

    private Date startTime;

    private Date endTime;

    private String reason;

    private Integer days;

    private Boolean status;
}
