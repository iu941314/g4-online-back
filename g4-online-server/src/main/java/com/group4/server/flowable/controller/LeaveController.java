/*
package com.group4.server.flowable.controller;



import com.group4.server.flowable.pojo.ResponseBean;
import com.group4.server.flowable.pojo.VacationApproveVo;
import com.group4.server.flowable.pojo.VacationRequestVo;
import com.group4.server.flowable.service.VacationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;



//@RequestMapping("Leave")
//@RestController
public class LeaveController {

    @Autowired
    VacationService vacationService;

    */
/**
     * 请假条新增页面
     * @return
     *//*

    @GetMapping("/add")
    public ModelAndView add(){
        return new ModelAndView("vacation");
    }

    */
/**
     * 请假条审批列表
     * @return
     *//*

    @GetMapping("/aList")
    public ModelAndView aList(){
        return new ModelAndView("list");
    }

    */
/**
     * 请假条查询列表
     * @return
     *//*

    @GetMapping("/sList")
    public ModelAndView sList(){
        return new ModelAndView("search");
    }

    */
/**
     * 请假请求方法
     * @param vacationRequestVO
     * @return
     *//*

    @PostMapping
    public ResponseBean askForLeave(@RequestBody VacationRequestVo vacationRequestVO) {
        return vacationService.askForLeave(vacationRequestVO);
    }

    */
/**
     * 获取待审批列表
     * @param identity
     * @return
     *//*

    @GetMapping("/list")
    public ResponseBean leaveList(String identity) {
        return vacationService.leaveList(identity);
    }

    */
/**
     * 拒绝或同意请假
     * @param vacationVO
     * @return
     *//*

    @PostMapping("/handler")
    public ResponseBean askForLeaveHandler(@RequestBody VacationApproveVo vacationVO) {
        return vacationService.askForLeaveHandler(vacationVO);
    }

    */
/**
     * 请假查询
     * @param name
     * @return
     *//*

    @GetMapping("/search")
    public ResponseBean searchResult(String name) {
        return vacationService.searchResult(name);
    }
}
*/
