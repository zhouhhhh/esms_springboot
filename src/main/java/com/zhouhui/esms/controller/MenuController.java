package com.zhouhui.esms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhouhui.esms.entity.Dict;
import com.zhouhui.esms.entity.Menu;
import com.zhouhui.esms.mapper.DictMapper;
import com.zhouhui.esms.service.MenuService;
import com.zhouhui.esms.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    DictMapper dictMapper;

    @GetMapping
    public R findAll(@RequestParam(defaultValue = "") String menuName) {
        List<Menu> level1Node = menuService.findAll(menuName);

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
    @GetMapping("/icons")
    public R getIcons(){
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type","icon");
        List<Dict> dicts = dictMapper.selectList(queryWrapper);
        return R.ok().data("dictList",dicts);
    }
}
