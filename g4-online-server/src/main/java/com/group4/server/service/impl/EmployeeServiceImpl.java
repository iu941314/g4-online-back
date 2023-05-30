package com.group4.server.service.impl;

import com.group4.server.mapper.EmployeeMapper;
import com.group4.server.mapper.MailLogMapper;
import com.group4.server.pojo.*;
import com.group4.server.pojo.count.CountDeptment;
import com.group4.server.pojo.count.CountEdu;
import com.group4.server.pojo.count.CountSex;
import com.group4.server.service.IEmployeeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;


@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private MailLogMapper mailLogMapper;

    /**
     * @return
     * @description: 获取所有员工（分页）
     * @param: currentPage
     * @param: size
     * @param: employee
     * @param: beginDateScope
     */
    @Override
    public RespPageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope) {
        //开启分页
        Page<Employee> page = new Page<>(currentPage, size);
        IPage<Employee> employeeByPage = employeeMapper.getEmployeeByPage(page, employee, beginDateScope);
        RespPageBean respPageBean = new RespPageBean(employeeByPage.getTotal(), employeeByPage.getRecords());
        return respPageBean;
    }

    /**
     * @return
     * @description: 获取工号
     * @param:
     */
    @Override
    public RespBean maxWorkID() {
        List<Map<String, Object>> maps = employeeMapper.selectMaps(new QueryWrapper<Employee>().select("max(workID)"));
        return RespBean.success(null, String.format("%08d", Integer.parseInt(maps.get(0).get("max(workID)").toString()) + 1));
    }

    /**
     * @return
     * @description: 添加员工
     * @param: employee
     */
    @Override
    public RespBean addEmp(Employee employee) {
        //处理合同期限，保留两位小数
        LocalDate beginContract = employee.getBeginContract();
        LocalDate endContract = employee.getEndContract();
        long days = beginContract.until(endContract, ChronoUnit.DAYS);
        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(days / 365.00)));
        if (1 == employeeMapper.insert(employee)) {
            Employee emp = employeeMapper.getEmployee(employee.getId()).get(0);
            //数据库记录已发送的消息
            String msgId = UUID.randomUUID().toString();
            MailLog mailLog = new MailLog();
            mailLog.setMsgId(msgId);
            mailLog.setEid(employee.getId());
            mailLog.setStatus(0);
            mailLog.setRouteKey(MailConstants.MAIL_ROUTING_KEY_NAME);
            mailLog.setExchange(MailConstants.MAIL_EXCHANGE_NAME);
            mailLog.setCount(0);
            mailLog.setTryTime(LocalDateTime.now().plusMinutes(MailConstants.MSG_TIMEOUT));
            mailLog.setCreateTime(LocalDateTime.now());
            mailLog.setUpdateTime(LocalDateTime.now());
            mailLogMapper.insert(mailLog);
            //发送信息
            rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME, MailConstants.MAIL_ROUTING_KEY_NAME, emp, new CorrelationData(msgId));
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    /**
     * @return
     * @description: 查询员工
     * @param: id
     */
    @Override
    public List<Employee> getEmployee(Integer id) {
        return employeeMapper.getEmployee(id);
    }

    /**
     * @return
     * @description: 获取所有员工账套
     * @param: currentPage
     * @param: size
     */
    @Override
    public RespPageBean getEmployeeWithSalary(Integer currentPage, Integer size) {
        //开启分页
        Page<Employee> page = new Page<>(currentPage, size);
        IPage<Employee> employeeIPage = employeeMapper.getEmployeeWithSalary(page);
        RespPageBean respPageBean = new RespPageBean(employeeIPage.getTotal(), employeeIPage.getRecords());
        return respPageBean;
    }

    @Override
    public RespPageBean getEmployeeAndSalary(Integer currentPage, Integer size) {
        //开启分页
        Page<Employee> page = new Page<>(currentPage, size);
        IPage<Employee> employeeIPage = employeeMapper.getEmployeeAndSalary(page);
        RespPageBean respPageBean = new RespPageBean(employeeIPage.getTotal(), employeeIPage.getRecords());
        return respPageBean;
    }


    //    基本信息统计
    @Override
    public Map<String, Object> queryMarrige_count() {
        Map<String, Object> dataMap = new HashMap<>();
        //获取数据
        Integer ly = employeeMapper.queryMarrige_ly();
        Integer wh = employeeMapper.queryMarrige_wh();
        Integer yh = employeeMapper.queryMarrige_yh();
        System.out.println(ly);
        System.out.println(wh);
        System.out.println(yh);

        List<Map<String, Object>> data1 = new ArrayList<>();
        //数据格式化
        Map<String, Object> map1 = new HashMap<>();
        map1.put("value",ly);
        map1.put("name", "离异");
        data1.add(map1);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("value",wh);
        map2.put("name", "未婚");
        data1.add(map2);

        Map<String, Object> map3 = new HashMap<>();
        map3.put("value",yh);
        map3.put("name", "已婚");
        data1.add(map3);

        dataMap.put("data",data1);
        return dataMap;
    }


    @Override
    public Map<String, Object> queryDeptmentCount() {
        List<CountDeptment> countDeptments = employeeMapper.queryDeptmentCount();
        HashMap<String, Object> map = new HashMap<>();
            //查询数据
        List<Map<String,Object>> data1 = new ArrayList<>();

        Integer value =0;
        countDeptments.forEach(System.out::println);
        for (CountDeptment data: countDeptments) {
            HashMap<String, Object> map1 = new HashMap<>();
            map1.put("name",data.getDepartmentName());
            map1.put("value",data.getCountDep());
            data1.add(map1);

            value += data.getCountDep();
        }

        HashMap<String, Object> map2 = new HashMap<>();
            map2.put("value",value);


        HashMap<String, Object> map3 = new HashMap<>(); //itemStyle
        HashMap<String, Object> map4 = new HashMap<>();//color
        HashMap<String, Object> map5 = new HashMap<>();//show
        map3.put("color","none");
//        =================
        map4.put("smybol","none");
        map3.put("decal",map4);
//        ====================
        map5.put("show",false);
        map3.put("lable",map5);
        map2.put("itemStyle",map3);

//        格式化数据放入map中
        data1.add(map2);
        map.put("data",data1);
        map.put("code",200);
        return map;
    }


    @Override
    public Map<String, Object> countSex() {
        Map<String, Object> map = new HashMap<>();

        List<Map<String, Object>> data1 = new ArrayList<>();
        //格式化数据
        List<CountSex> countSexes = employeeMapper.countSex();
        countSexes.forEach(System.out::println);
        for (CountSex data:countSexes) {
            Map<String, Object> map1 = new HashMap<>();
            map1.put("value",data.getCountSex());
            map1.put("name",data.getSex());
            data1.add(map1);
        }
        map.put("data",data1);
        return map;
    }


    @Override
    public Map<String, Object> countEdu() {
       Map<String, Object> map = new HashMap<>();
        List<CountEdu> countEdus = employeeMapper.countEdu();
//        x坐标
        List<String> data1 = new ArrayList<>();
//        y坐标
        List<Object> data2= new ArrayList<>();
        for (CountEdu edu: countEdus) {
            data1.add(edu.getEdu());
            data2.add(edu.getCountEdu());
        }

        map.put("xAxis",data1);
        map.put("yAxis",data2);
        return map;
    }

    /**
     * 查询员工来自省份
     * @return
     */
    @Override
    public Map<String, Object> countProvice() {
        Map<String, Object> map = new HashMap<>();
        List<Map<String,Object>> data = new ArrayList<>();
        //查询省份名
        List<String> list = employeeMapper.queryProviceName();
        for (String name:list) {
            System.out.println(name);
            //临时容器1
            Map<String,Object> map1 = new HashMap<>();
            //临时容器2
//            List<Map<String,Object>> data1 = new ArrayList<>();
            map1.put("name",name);
            //通过省份名查询数据
            Integer countProvice = employeeMapper.countProvice(name);
            map1.put("value",countProvice);
//            data1.add(map1);
            data.add(map1);

        }

        map.put("data",data);
        return map;
    }
}
