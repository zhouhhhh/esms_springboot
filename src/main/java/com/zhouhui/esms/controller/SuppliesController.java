package com.zhouhui.esms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhouhui.esms.entity.Supplies;
import com.zhouhui.esms.entity.SuppliesMidType;
import com.zhouhui.esms.service.SuppliesMidTypeService;
import com.zhouhui.esms.service.SuppliesService;
import com.zhouhui.esms.service.SuppliesTypeService;
import com.zhouhui.esms.utils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    SuppliesTypeService suppliesTypeService;

    @Autowired
    SuppliesMidTypeService suppliesMidTypeService;

    @GetMapping
    public R findAll(){
        List<Supplies> list = suppliesService.list();
        return R.ok().data("suppliesList",list);
    }

    @GetMapping("/page")
    @ApiOperation(value = "分页查询物资信息", notes = "分页查询物资信息,并按条件查询")
    public R findAllByPages(@RequestParam Integer pageCurrent,
                            @RequestParam Integer pageSize,
                            @RequestParam(required = false)Integer typeId
                            ){
        IPage<Supplies> page = new Page<>(pageCurrent,pageSize);
        QueryWrapper<Supplies> queryWrapper = new QueryWrapper<>();
        List<Integer> childrenTypeIds = null;
        if(typeId != null){
            //通过物资类型条件查询，先查询id以及所有的子id，获得typeId集合
            childrenTypeIds = suppliesTypeService.findChildrenTypeIds(typeId);

            //通过typeId集合，去中间表查询所有的物资id

        }else {
            IPage<Supplies> iPage = suppliesService.page(page);
            return R.ok().data("page",iPage);
        }
        //通过typeId集合，去中间表查询所有的物资id
        List<Integer> collect = null;
        if (childrenTypeIds != null && childrenTypeIds.size() > 0){
            QueryWrapper<SuppliesMidType> temp = new QueryWrapper<>();
            temp.in("supplies_type_id",childrenTypeIds);
            List<SuppliesMidType> suppliesMidTypes = suppliesMidTypeService.list(temp);
            collect = suppliesMidTypes.stream().map(x -> x.getSuppliesId()).collect(Collectors.toList());

        }
        //根据所有的物资id分页查询
        if (collect != null && collect.size() > 0){
            queryWrapper.in("supplies_id",collect);
        }else {
            //此时typeid不为空，但是根据物资类型查询不到有物资属于该类型，就应该返回空的
            queryWrapper.eq("supplies_id",0);
        }
        System.out.println(collect);
        IPage<Supplies> iPage = suppliesService.page(page,queryWrapper);
        return R.ok().data("page",iPage);
    }

    @PostMapping
    @ApiOperation(value = "修改保存物资信息", notes = "修改保存物资信息,根据是否有id进行判断")
    @Transactional(rollbackFor = Exception.class)
    public R save(@RequestBody Supplies supplies){
        boolean b = suppliesService.saveOrUpdate(supplies);
        suppliesMidTypeService.saveOrUpdateBysuppliesId(supplies);
        if(b){
            return R.ok();
        }else {
            return R.error();

        }
    }
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除物资信息",notes = "删除物资信息")
    public R delMenu(@PathVariable Integer id){
        boolean b = suppliesService.removeById(id);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/batch/ids")
    @ApiOperation(value = "批量删除物资",notes = "批量删除物资信息")
    public R deleteBatch(@RequestBody List<Integer> ids){
        boolean b = suppliesService.removeBatchByIds(ids);
        if(b == true){
            return R.ok();
        }else {
            return R.error();
        }
    }
}
