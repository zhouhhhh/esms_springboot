package com.zhouhui.esms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhouhui.esms.entity.Supplies;
import com.zhouhui.esms.entity.SuppliesIn;
import com.zhouhui.esms.entity.SuppliesInMidSupplies;
import com.zhouhui.esms.service.SuppliesInMidSuppliesService;
import com.zhouhui.esms.service.SuppliesInService;
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
 * 物资入库 前端控制器
 * </p>
 *
 * @author zhouhui
 * @since 2022-04-27
 */
@RestController
@RequestMapping("/esms/supplies-in")
public class SuppliesInController {

    @Autowired
    SuppliesInService suppliesInService;

    @Autowired
    SuppliesInMidSuppliesService suppliesInMidSuppliesService;

    @Autowired
    SuppliesService suppliesService;
    @GetMapping
    public R findAll(){
        List<SuppliesIn> list = suppliesInService.list();
        return R.ok().data("list",list);
    }
    @GetMapping("/{suppliesInId}")
    public R findOne(@PathVariable Integer suppliesInId){
        //获取订单表信息
        SuppliesIn byId = suppliesInService.getById(suppliesInId);
        //从中间表获取捐赠物资信息
        QueryWrapper<SuppliesInMidSupplies> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("supplies_in_id",suppliesInId);
        //获取订单的中间表信息
        List<SuppliesInMidSupplies> list = suppliesInMidSuppliesService.list(queryWrapper);
        //根据中间表获取所有捐赠物资信息
        List<Supplies> supplies = suppliesService.listByIdsWithCountIn(list);
        //赋值
        byId.setSuppliesList(supplies);
        return R.ok().data("list",byId);
    }
    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "入库",notes = "插入入库单信息")
    public R save(@RequestBody SuppliesIn suppliesIn){
        //保存订单
        suppliesIn.setSuppliesInNo(UUID.randomUUID().toString());
        boolean b = suppliesInService.save(suppliesIn);
        List<Supplies> suppliesList = suppliesIn.getSuppliesList();
        //保存中间表信息
        boolean a = suppliesInMidSuppliesService.saveSuppliesInAndSupplies(
                suppliesIn.getSuppliesInId(),suppliesList);
        //给物资表数量增加
        boolean c = suppliesService.updateCount(suppliesList,true);
        if(b && a){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除入库单",notes = "删除入库单信息")
    public R delete(@PathVariable Integer id){
        boolean b = suppliesInService.removeById(id);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/batch/ids")
    @ApiOperation(value = "批量删除入库单",notes = "批量删除入库单信息")
    public R deleteBatch(@RequestBody List<Integer> ids){
        boolean b = suppliesInService.removeBatchByIds(ids);
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
        IPage<SuppliesIn> iPage = new Page<>(pageCurrent, pageSize);
        QueryWrapper<SuppliesIn> queryWrapper = new QueryWrapper<>();
        IPage<SuppliesIn> page = suppliesInService.page(iPage,queryWrapper);
        return R.ok().data("page",page);
    }
}
