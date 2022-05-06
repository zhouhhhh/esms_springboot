package com.zhouhui.esms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhouhui.esms.entity.Menu;
import com.zhouhui.esms.service.MenuService;
import com.zhouhui.esms.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhouhui
 * @since 2022-05-06
 */
@RestController
@RequestMapping("/esms/menu")
@Api(value = "菜单模块", tags = "系统菜单接口")
public class MenuController {
    @Autowired
    MenuService menuService;

    @GetMapping
    public R findAll(@RequestParam(defaultValue = "") String menuName) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("menu_name", menuName);
        queryWrapper.eq("del_flag", 0);
        List<Menu> list = menuService.list(queryWrapper);
        //找出一级菜单
        List<Menu> level1Node = list.stream().filter(menu -> {
            if (menu.getPid() == null) {
                menu.setLevel(1);
                return true;
            } else {
                return false;
            }
        }).collect(Collectors.toList());
        //最多三级菜单
        for (Menu menu1 : level1Node) {
            List<Menu> level2Node = list.stream().filter(m -> {
                if (menu1.getMenuId().equals(m.getPid())) {
                    m.setLevel(2);
                    return true;
                } else {
                    return false;
                }
            }).collect(Collectors.toList());
            menu1.setChildren(level2Node);
            for (Menu menu2 : level2Node) {
                List<Menu> level3Node = list.stream().filter(m -> {
                    if (menu2.getMenuId().equals(m.getPid())) {
                        m.setLevel(3);
                        return true;
                    } else {
                        return false;
                    }
                }).collect(Collectors.toList());
                menu2.setChildren(level3Node);
            }
        }
        return R.ok().data("menuList", level1Node);
    }

    @GetMapping("/page")
    @ApiOperation(value = "分页查询菜单信息", notes = "分页查询菜单信息,并按条件查询")
    public R findAllByPages(@RequestParam Integer pageCurrent,
                            @RequestParam Integer pageSize,
                            @RequestParam(defaultValue = "") String menuName) {
        IPage<Menu> menuPage = new Page<>(pageCurrent, pageSize);
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("menu_name", menuName);
        queryWrapper.eq("del_flag", 0);
        IPage<Menu> page = menuService.page(menuPage, queryWrapper);
        return R.ok().data("page", page);
    }

    @PostMapping
    @ApiOperation(value = "修改保存菜单信息", notes = "修改保存菜单信息,根据是否有id进行判断")
    public R save(@RequestBody Menu menu) {
        boolean b = menuService.saveOrUpdate(menu);
        if (b) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除菜单信息", notes = "删除菜单信息")
    public R delMenu(@PathVariable Integer id) {
        boolean b = menuService.removeById(id);
        if (b) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @DeleteMapping("/batch/ids")
    @ApiOperation(value = "批量删除菜单", notes = "批量删除角菜单信息")
    public R deleteBatch(@RequestBody List<Integer> ids) {
        boolean b = menuService.removeBatchByIds(ids);
        if (b == true) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}
