package com.zhouhui.esms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhouhui.esms.entity.User;
import com.zhouhui.esms.service.UserService;
import com.zhouhui.esms.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public boolean save(@RequestBody User user){
        return userService.saveOrUpdate(user);
    }

    @GetMapping("/page")
    public R findByPage(@RequestParam Integer pageCurrent,
                                 @RequestParam Integer pageSize,
                                 @RequestParam(defaultValue = "")String userName){

        Page<User> userPage = new Page<>(pageCurrent, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("user_name",userName);
        Page<User> page = userService.page(userPage, queryWrapper);
        return R.ok().data("page",page);
    }

    @GetMapping("/test")
    public R test(){
//        String str = null;
//        str.equals("aaa");
        Integer.parseInt("aaa123");
        return R.ok();
    }
}
