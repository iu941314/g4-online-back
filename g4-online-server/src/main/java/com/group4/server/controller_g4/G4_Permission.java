package com.group4.server.controller_g4;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.group4.server.pojo.Menu;
import com.group4.server.pojo.MenuRole;
import com.group4.server.pojo.RespBean;
import com.group4.server.pojo.Role;
import com.group4.server.service.IMenuRoleService;
import com.group4.server.service.IMenuService;
import com.group4.server.service.IRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/g4_sys/basic/permission")
public class G4_Permission {
    @Autowired
    private IRoleService roleService;

    @Autowired
    private IMenuService menuService;
    @Autowired
    private IMenuRoleService menuRoleService;

    @ApiOperation(value = "查询所有角色")
    @GetMapping("/")
    public List<Role> getAllRoles()
    {
        return roleService.list();
    }

    @ApiOperation(value = "新增角色")
    @PutMapping("/role")
    public RespBean addRole(@RequestBody Role role){
        if(roleService.save(role)){
            return  RespBean.success("新增成功");
        }
        return RespBean.error("新增失败");
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/role/{id}")
    public RespBean deleteRole(@PathVariable Integer id){
        if(roleService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "查询所有菜单")
    @GetMapping("/menus")
    public List<Menu> getAllMenus(){
        return menuService.getAllMenus();
    }

    @ApiOperation(value = "根据角色id查询菜单id")
    @GetMapping("/mid/{rid}")
    public List<Integer> getMidByRid(@PathVariable Integer rid){
        //通过id查询角色下所有的id
        List<MenuRole> roleList = menuRoleService.list(new QueryWrapper<MenuRole>().eq("rid", rid));
        //将数据通过io流转换成map再转换成list
        List<Integer> list = roleList.stream().map(MenuRole::getMid).collect(Collectors.toList());
        return list;
    }

    @ApiOperation(value = "更新角色菜单")
    @PutMapping("/")
    public RespBean updateMenuRole(Integer rid,Integer[] mids){
        return menuRoleService.updateMenuRole(rid,mids);
    }
//

}
