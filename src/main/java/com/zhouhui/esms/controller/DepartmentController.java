package com.zhouhui.esms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhouhui.esms.entity.Department;
import com.zhouhui.esms.service.DepartmentService;
import com.zhouhui.esms.utils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 部门;代表用户所属的部门 前端控制器
 * </p>
 *
 * @author zhouhui
 * @since 2022-04-27
 */
@RestController
@RequestMapping("/esms/departments")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/departmentsnames")
    public R findDepartmentName(){
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("department_id","department_name");
        List<Department> list = departmentService.list(queryWrapper);
        return R.ok().data("departmentlist",list);
    }

    @GetMapping
    public R findAll(){
        List<Department> list = departmentService.list();
        return R.ok().data("departmentList",list);
    }

    @GetMapping("/page")
    @ApiOperation(value = "分页查询部门信息", notes = "分页查询部门信息,并按条件查询")
    public R findAllByPages(@RequestParam Integer pageCurrent,
                            @RequestParam Integer pageSize,
                            @RequestParam(defaultValue = "") String departmentName){

        IPage<Department> departmentPage = new Page<>(pageCurrent,pageSize);
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("department_name",departmentName);
        queryWrapper.eq("del_flag",0);
        IPage<Department> page = departmentService.page(departmentPage,queryWrapper);
        return R.ok().data("page",page);
    }

    @PostMapping
    @ApiOperation(value = "修改保存部门信息", notes = "修改保存部门信息,根据是否有id进行判断")
    public R save(@RequestBody Department department){
        boolean b = departmentService.saveOrUpdate(department);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }
    }
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除部门信息",notes = "删除部门信息")
    public R delMenu(@PathVariable Integer id){
        boolean b = departmentService.removeById(id);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/batch/ids")
    @ApiOperation(value = "批量删除部门",notes = "批量删除部门信息")
    public R deleteBatch(@RequestBody List<Integer> ids){
        boolean b = departmentService.removeBatchByIds(ids);
        if(b == true){
            return R.ok();
        }else {
            return R.error();
        }
    }

}
