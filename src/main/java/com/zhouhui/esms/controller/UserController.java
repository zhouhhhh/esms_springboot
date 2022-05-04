package com.zhouhui.esms.controller;


import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhouhui.esms.entity.User;
import com.zhouhui.esms.service.UserService;
import com.zhouhui.esms.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author zhouhui
 * @since 2022-04-27
 */
@RestController
@RequestMapping("/esms/users")
@Api(value = "用户模块",tags = "系统用户接口")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping
    @ApiOperation(value = "用户列表",notes = "查询所有用户信息")
    public R findAll(){
        return R.ok().data("userList",userService.list());
    }

    @PostMapping
    @ApiOperation(value = "保存用户",notes = "插入或更新用户信息，根据是否有id判断")
    public R save(@RequestBody User user){
        boolean b = userService.saveOrUpdate(user);
        if(b == true){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除用户",notes = "删除用户信息")
    public R delete(@PathVariable Integer id){
        boolean b = userService.removeById(id);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }
    }
    @GetMapping("/page")
    public R findByPage(@RequestParam Integer pageCurrent,
                                 @RequestParam Integer pageSize,
                                 @RequestParam(required = false) Integer departmentId,
                                 @RequestParam(defaultValue = "")String userName){

        IPage<User> userPage = new Page<>(pageCurrent, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(departmentId != null){
            queryWrapper.eq("user_department_id",departmentId);
        }
        queryWrapper.like("user_name",userName);
        IPage<User> page = userService.findUsersAndDepartmentNameByPage(userPage, queryWrapper);
        return R.ok().data("page",page);
    }

    /**
     * 导出excel接口
     */
    @GetMapping("/excel")
    public void exportExcel(HttpServletResponse response) throws Exception{
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        List<User> list = userService.findUsersAndDepartmentName(queryWrapper);

        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.addHeaderAlias("userId","用户ID");
        writer.addHeaderAlias("userCode","用户编号");
        writer.addHeaderAlias("userName","用户名");
        writer.addHeaderAlias("userPassword","密码");
        writer.addHeaderAlias("gender","性别");
        writer.addHeaderAlias("DepartmentName","部门");
        writer.addHeaderAlias("birthday","生日");
        writer.addHeaderAlias("phone","手机号");
        writer.addHeaderAlias("email","邮件地址");
        writer.addHeaderAlias("createdBy","创建人");
        writer.addHeaderAlias("createdTime","创建时间");
        writer.addHeaderAlias("updatedBy","更新人");
        writer.addHeaderAlias("updatedTime","更新时间");
        writer.addHeaderAlias("version","乐观锁");
        writer.addHeaderAlias("delFlag","逻辑删除(0代表未删除，1代表删除)");

        // 默认的，未添加alias的属性也会写出，如果想只写出加了别名的字段，可以调用此方法排除之
        writer.setOnlyAlias(true);

        writer.write(list,true);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String filename = URLEncoder.encode("用户信息","UTF-8");
        response.setHeader("Content-Disposition","attachment;filename="+filename+".xlsx");

        ServletOutputStream outputStream = response.getOutputStream();
        writer.flush(outputStream,true);

        outputStream.close();
        writer.close();
    }
}
