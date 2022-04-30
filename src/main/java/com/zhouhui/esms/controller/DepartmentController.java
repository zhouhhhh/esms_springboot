package com.zhouhui.esms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhouhui.esms.entity.Department;
import com.zhouhui.esms.service.DepartmentService;
import com.zhouhui.esms.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
