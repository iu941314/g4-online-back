package com.group4.server.controller_g4;


import com.group4.server.pojo.Position;
import com.group4.server.pojo.RespBean;
import com.group4.server.service.IPositionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("g4_sys/config/pos")
public class G4_PositonController {

    @Autowired
    private IPositionService positionService;

    @ApiOperation(value = "新增职位")
    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position) {
        //参数校验
        position.setCreateDate(LocalDateTime.now());
        if (positionService.save(position)) {
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @ApiOperation(value = "删除职位")
    @DeleteMapping("/")
    public RespBean deletePostion( Integer id){
        if(positionService.removeById(id)){
            return  RespBean.success("删除职位成功");
        }
        return RespBean.error("删除职位失败");
    }

    @ApiOperation(value = "查询所有职位信息")
    @GetMapping("/")
    public List<Position> getAllPosttions(){
        return positionService.list();
    }
}
