package com.zhouhui.esms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhouhui.esms.entity.SuppliesType;
import com.zhouhui.esms.service.SuppliesTypeService;
import com.zhouhui.esms.utils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 物资类别;此为多级目录，一级目录下可以添加二级子目录，展开可以显示二级子目录列表，往下依次类推实现多级目录显示。 前端控制器
 * </p>
 *
 * @author zhouhui
 * @since 2022-04-27
 */
@RestController
@RequestMapping("/esms/supplies-type")
public class SuppliesTypeController {

    @Autowired
    SuppliesTypeService suppliesTypeService;


    @GetMapping("/suppliestypename")
    public R findSuppliesTypeName(){
        QueryWrapper<SuppliesType> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("type_id","type_name");
        List<SuppliesType> list = suppliesTypeService.list(queryWrapper);
        return R.ok().data("suppliesTypeList",list);
    }

    @GetMapping
    @ApiOperation(value = "返回所有物质类型",notes = "返回所有物质类型，按照三级目录的形式")
    public R findAll() {
        List<SuppliesType> level1Node = suppliesTypeService.findAll();

        return R.ok().data("suppliesTypeList", level1Node);
    }



    @PostMapping
    @ApiOperation(value = "修改保存物资类型信息", notes = "修改保存物资类型信息,根据是否有id进行判断")
    public R save(@RequestBody SuppliesType suppliesType) {
        boolean b = suppliesTypeService.saveOrUpdate(suppliesType);
        if (b) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除菜单信息", notes = "删除菜单信息")
    public R delMenu(@PathVariable Integer id) {
        boolean b = suppliesTypeService.removeById(id);
        if (b) {
            return R.ok();
        } else {
            return R.error();
        }
    }

}
