package com.group4.server.controller_g4;


import com.group4.server.pojo.Joblevel;
import com.group4.server.pojo.RespBean;
import com.group4.server.service.IJoblevelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("g4_sys/basic/joblevel")
public class G4_JoblevelController {
    @Autowired
    private IJoblevelService joblevelService;

    @ApiOperation(value = "新增职称管理")
    @RequestMapping("/")
    public RespBean addJoblevel(@RequestBody Joblevel joblevel){
        joblevel.setCreateDate(LocalDateTime.now());
        if(joblevelService.save(joblevel)){
            return RespBean.success("新增职称成功");
        }
        return RespBean.error("新增职称失败");
    }
    @ApiOperation(value = "删除职称管理")
    @DeleteMapping("/{id}")
    public RespBean deleteJoblevel(@PathVariable Integer id){
        if(joblevelService.removeById(id)){
            return RespBean.success("删除职称成功");
        }
        return RespBean.error("删除职称失败");
    }
    @ApiOperation(value = "批量删除职称管理")
    @DeleteMapping("/")
    public RespBean batchDeleteJoblevel(Integer[] ids){
        if (joblevelService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除职称成功");
        }
        return RespBean.success("删除职称失败");
    }

    @ApiOperation(value = "查询所有职称")
    @GetMapping("/")
    public List<Joblevel> getAllJobleverl(){
        return joblevelService.list();
    }
    @ApiOperation(value = "修改职称")
    @PutMapping("/")
    public RespBean updateJobleverl(@RequestBody Joblevel joblevel){
        if (null == joblevel){
            return RespBean.error("参数不能为空,请检查");
        }
        if (joblevelService.updateById(joblevel)){
            return RespBean.success("添加成功");
        }
       return RespBean.error("添加失败");
    }
}
