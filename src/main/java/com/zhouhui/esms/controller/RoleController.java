package com.zhouhui.esms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhouhui.esms.entity.Role;
import com.zhouhui.esms.service.RoleService;
import com.zhouhui.esms.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 角色;不同的角色有不同的权限 前端控制器
 * </p>
 *
 * @author zhouhui
 * @since 2022-04-27
 */
@RestController
@RequestMapping("/esms/roles")
@Api(value = "角色模块",tags = "系统角色接口")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping
    public R findAll(){
        List<Role> list = roleService.list();
        return R.ok().data("roleList",list);
    }

    @GetMapping("/rolesnames")
    public R findRolesNames(){
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("role_id","role_name");
        List<Role> list = roleService.list(queryWrapper);
        return R.ok().data("roleList",list);
    }
    @GetMapping("/page")
    @ApiOperation(value = "分页查询角色信息", notes = "分页查询角色信息,并按条件查询")
    public R findAllByPages(@RequestParam Integer pageCurrent,
                            @RequestParam Integer pageSize,
                            @RequestParam(defaultValue = "") String roleName){
        IPage<Role> rolePage = new Page<>(pageCurrent, pageSize);
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("role_name",roleName);
        queryWrapper.eq("del_flag",0);
        IPage<Role> page = roleService.page(rolePage,queryWrapper);
        return R.ok().data("page",page);
    }

    @PostMapping
    @ApiOperation(value = "修改保存角色信息", notes = "修改保存角色信息,根据是否有id进行判断")
    public R save(@RequestBody Role role){
        boolean b = roleService.saveOrUpdate(role);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }
    }
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除角色信息",notes = "删除角色信息")
    public R delRole(@PathVariable Integer id){
        boolean b = roleService.removeById(id);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/batch/ids")
    @ApiOperation(value = "批量删除角色",notes = "批量删除角色信息")
    public R deleteBatch(@RequestBody List<Integer> ids){
        boolean b = roleService.removeBatchByIds(ids);
        if(b == true){
            return R.ok();
        }else {
            return R.error();
        }
    }

    /**
     * 角色和菜单之间的关系
     * @param roleId 角色id
     * @param menuIds 菜单id
     * @return
     */
    @PostMapping("/rolemenu/{roleId}")
    public R roleMenu(@PathVariable Integer roleId,@RequestBody List<Integer> menuIds){
        roleService.setRoleMenu(roleId,menuIds);
        return R.ok();
    }

    @GetMapping("/rolemenu/{roleId}")
    public R getRoleMenu(@PathVariable Integer roleId){
        List<Integer> menuIds = roleService.getRoleMenu(roleId);
        return R.ok().data("menuIds",menuIds);
    }
}
