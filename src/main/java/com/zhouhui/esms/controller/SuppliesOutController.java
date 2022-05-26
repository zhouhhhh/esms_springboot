package com.zhouhui.esms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhouhui.esms.entity.*;
import com.zhouhui.esms.service.SuppliesOutMidSuppliesService;
import com.zhouhui.esms.service.SuppliesOutService;
import com.zhouhui.esms.service.SuppliesService;
import com.zhouhui.esms.utils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 物资发放 前端控制器
 * </p>
 *
 * @author zhouhui
 * @since 2022-04-27
 */
@RestController
@RequestMapping("/esms/supplies-out")
public class SuppliesOutController {

    @Autowired
    SuppliesOutService suppliesOutService;

    @Autowired
    SuppliesOutMidSuppliesService suppliesOutMidSuppliesService;

    @Autowired
    SuppliesService suppliesService;
    @GetMapping
    public R findAll(){
        List<SuppliesOut> list = suppliesOutService.list();
        return R.ok().data("list",list);
    }
    @GetMapping("/{suppliesOutId}")
    public R findOne(@PathVariable Integer suppliesOutId){
        //获取订单表信息
        SuppliesOut byId = suppliesOutService.getById(suppliesOutId);
        //从中间表获取捐赠物资信息
        QueryWrapper<SuppliesOutMidSupplies> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("supplies_Out_id",suppliesOutId);
        //获取订单的中间表信息
        List<SuppliesOutMidSupplies> list = suppliesOutMidSuppliesService.list(queryWrapper);
        //根据中间表获取所有捐赠物资信息
        List<Supplies> supplies = suppliesService.listByIdsWithCountOut(list);
        //赋值
        byId.setSuppliesList(supplies);
        return R.ok().data("list",byId);
    }
    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "出库",notes = "插入出库单信息")
    public R save(@RequestBody SuppliesOut suppliesOut){
        //保存订单
        suppliesOut.setSuppliesOutNo(UUID.randomUUID().toString());
        boolean b = suppliesOutService.save(suppliesOut);
        List<Supplies> suppliesList = suppliesOut.getSuppliesList();
        //保存中间表信息
        boolean a = suppliesOutMidSuppliesService.saveSuppliesOutAndSupplies(
                suppliesOut.getSuppliesOutId(),suppliesList);
        //给物资表数量减少
        boolean c = suppliesService.updateCount(suppliesList,false);
        if(b && a){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除入库单",notes = "删除入库单信息")
    public R delete(@PathVariable Integer id){
        boolean b = suppliesOutService.removeById(id);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/batch/ids")
    @ApiOperation(value = "批量删除入库单",notes = "批量删除入库单信息")
    public R deleteBatch(@RequestBody List<Integer> ids){
        boolean b = suppliesOutService.removeBatchByIds(ids);
        if(b == true){
            return R.ok();
        }else {
            return R.error();
        }
    }
    @GetMapping("/page")
    @ApiOperation(value = "分页查询",notes = "分页查询所有入库信息，按条件查询")
    public R findByPage(@RequestParam Integer pageCurrent,
                        @RequestParam Integer pageSize){
        IPage<SuppliesOut> iPage = new Page<>(pageCurrent, pageSize);
        QueryWrapper<SuppliesOut> queryWrapper = new QueryWrapper<>();
        IPage<SuppliesOut> page = suppliesOutService.page(iPage,queryWrapper);
        return R.ok().data("page",page);
    }
}
