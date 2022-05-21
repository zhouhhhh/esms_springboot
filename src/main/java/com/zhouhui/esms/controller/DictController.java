package com.zhouhui.esms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhouhui.esms.entity.Dict;
import com.zhouhui.esms.service.DictService;
import com.zhouhui.esms.utils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhouhui
 * @since 2022-05-18
 */
@RestController
@RequestMapping("/esms/dict")
public class DictController {
    @Autowired
    DictService dictService;


    @GetMapping
    @ApiOperation(value = "图标列表",notes = "查询所有图标信息")
    public R findAll(){
        return R.ok().data("dictList",dictService.list());
    }


    @PostMapping
    @ApiOperation(value = "保存图标",notes = "插入或更新图标信息，根据是否有id判断")
    public R save(@RequestBody Dict dict){
        boolean b = dictService.saveOrUpdate(dict);
        if(b == true){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除图标",notes = "删除图标信息")
    public R delete(@PathVariable Integer id){
        boolean b = dictService.removeById(id);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/batch/ids")
    @ApiOperation(value = "批量删除图标",notes = "批量删除图标信息")
    public R deleteBatch(@RequestBody List<Integer> ids){
        boolean b = dictService.removeBatchByIds(ids);
        if(b == true){
            return R.ok();
        }else {
            return R.error();
        }
    }
    @GetMapping("/page")
    @ApiOperation(value = "分页查询图标",notes = "分页查询所有图标信息，按条件查询")
    public R findByPage(@RequestParam Integer pageCurrent,
                        @RequestParam Integer pageSize){
        IPage<Dict> dictPage = new Page<>(pageCurrent, pageSize);
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        IPage<Dict> page = dictService.page(dictPage,queryWrapper);
        return R.ok().data("page",page);
    }
}
