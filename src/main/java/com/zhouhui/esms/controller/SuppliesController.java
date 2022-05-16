package com.zhouhui.esms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhouhui.esms.entity.Supplies;
import com.zhouhui.esms.service.SuppliesService;
import com.zhouhui.esms.utils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 物资库存 前端控制器
 * </p>
 *
 * @author zhouhui
 * @since 2022-04-27
 */
@RestController
@RequestMapping("/esms/supplies")
public class SuppliesController {
    @Autowired
    SuppliesService suppliesService;



    @GetMapping
    public R findAll(){
        List<Supplies> list = suppliesService.list();
        return R.ok().data("suppliesList",list);
    }

    @GetMapping("/page")
    @ApiOperation(value = "分页查询部门信息", notes = "分页查询部门信息,并按条件查询")
    public R findAllByPages(@RequestParam Integer pageCurrent,
                            @RequestParam Integer pageSize
                            ){

        IPage<Supplies> page = new Page<>(pageCurrent,pageSize);
        QueryWrapper<Supplies> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag",0);
        IPage<Supplies> iPage = suppliesService.page(page,queryWrapper);
        return R.ok().data("page",iPage);
    }

    @PostMapping
    @ApiOperation(value = "修改保存部门信息", notes = "修改保存部门信息,根据是否有id进行判断")
    public R save(@RequestBody Supplies supplies){
        boolean b = suppliesService.saveOrUpdate(supplies);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }
    }
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除部门信息",notes = "删除部门信息")
    public R delMenu(@PathVariable Integer id){
        boolean b = suppliesService.removeById(id);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/batch/ids")
    @ApiOperation(value = "批量删除部门",notes = "批量删除部门信息")
    public R deleteBatch(@RequestBody List<Integer> ids){
        boolean b = suppliesService.removeBatchByIds(ids);
        if(b == true){
            return R.ok();
        }else {
            return R.error();
        }
    }
}
