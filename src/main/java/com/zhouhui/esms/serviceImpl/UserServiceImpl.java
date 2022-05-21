package com.zhouhui.esms.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhouhui.esms.entity.Menu;
import com.zhouhui.esms.entity.User;
import com.zhouhui.esms.mapper.MenuMapper;
import com.zhouhui.esms.mapper.RoleMenuMapper;
import com.zhouhui.esms.mapper.UserMapper;
import com.zhouhui.esms.service.MenuService;
import com.zhouhui.esms.service.UserService;
import com.zhouhui.esms.utils.JWTUtils;
import com.zhouhui.esms.utils.exceptionhandler.BizException;
import com.zhouhui.esms.utils.exceptionhandler.ExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author zhouhui
 * @since 2022-04-27
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    RoleMenuMapper roleMenuMapper;
    @Autowired
    MenuMapper menuMapper;
    @Autowired
    MenuService menuService;

    @Override
    public IPage<User> findUsersAndDepartmentNameByPage(IPage<User> page,
                                                        @Param(Constants.WRAPPER)
                                                                QueryWrapper<User> userQueryWrapper)
    {
        UserMapper userMapper = getBaseMapper();
        return userMapper.findUsersAndDepartmentNameByPage(page, userQueryWrapper);
    }

    @Override
    public List<User> findUsersAndDepartmentName(QueryWrapper<User> userQueryWrapper) {
        UserMapper userMapper = getBaseMapper();
        return userMapper.findUsersAndDepartmentName(userQueryWrapper);
    }

    @Override
    public User login(User user) {
        String userCode = user.getUserCode();
        String userPassword = user.getUserPassword();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_code", userCode);
        queryWrapper.eq("user_password", userPassword);
        UserMapper userMapper = getBaseMapper();
        try
        {
            User selectOne = userMapper.selectOne(queryWrapper);
            if(selectOne != null){
                String token = JWTUtils.createToken(selectOne.getUserId().toString(),selectOne.getUserPassword());
                selectOne.setToken(token);
                List<Menu> menus = getUserRoleMenus(selectOne);
                selectOne.setMenus(menus);
            }
            return selectOne;
        }
        catch (Exception e)
        {
            log.error("发生业务逻辑异常！原因是：" + e);
            throw new BizException(ExceptionEnum.SERVICE_ERROR);
        }

    }

    /**
     * 获取用户自身的权限菜单
     * @param selectOne
     * @return
     */
    private List<Menu> getUserRoleMenus(User selectOne){
        Integer userRoleId = selectOne.getUserRoleId();
        List<Integer> menuList = roleMenuMapper.selectListByRoleId(userRoleId);
        List<Menu> all = menuService.findAll("");
        List<Menu> menus = new ArrayList<>();
        //一共有三级目录
        for(Menu first : all){
            if(menuList.contains(first.getMenuId()) || first.getChildren() != null){
                menus.add(first);
            }
            List<Menu> seconds = first.getChildren();

            seconds.removeIf(menu -> !menuList.contains(menu.getMenuId()) && menu.getChildren() == null);


            for (Menu second : seconds){
                List<Menu> thirds = second.getChildren();
                thirds.removeIf(menu -> !menuList.contains(menu.getMenuId()));
            }
        }
        return menus;
    }
}
