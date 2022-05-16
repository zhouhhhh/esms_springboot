package com.zhouhui.esms.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhouhui.esms.entity.Menu;
import com.zhouhui.esms.mapper.MenuMapper;
import com.zhouhui.esms.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhouhui
 * @since 2022-05-06
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public List<Menu> findAll(String menuName) {
        MenuMapper baseMapper = getBaseMapper();
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("menu_name", menuName);
        queryWrapper.eq("del_flag", 0);
        List<Menu> list = list(queryWrapper);
        //找出一级菜单
        List<Menu> level1Node = list.stream().filter(menu -> {
            if (menu.getPid() == null) {
                menu.setLevel(1);
                return true;
            } else {
                return false;
            }
        }).collect(Collectors.toList());
        //最多三级菜单
        for (Menu menu1 : level1Node) {
            List<Menu> level2Node = list.stream().filter(m -> {
                if (menu1.getMenuId().equals(m.getPid())) {
                    m.setLevel(2);
                    return true;
                } else {
                    return false;
                }
            }).collect(Collectors.toList());
            menu1.setChildren(level2Node);
            for (Menu menu2 : level2Node) {
                List<Menu> level3Node = list.stream().filter(m -> {
                    if (menu2.getMenuId().equals(m.getPid())) {
                        m.setLevel(3);
                        return true;
                    } else {
                        return false;
                    }
                }).collect(Collectors.toList());
                menu2.setChildren(level3Node);
            }
        }
        return level1Node;
    }
}
