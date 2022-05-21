package com.zhouhui.esms.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhouhui.esms.entity.Menu;
import com.zhouhui.esms.mapper.MenuMapper;
import com.zhouhui.esms.mapper.RoleMenuMapper;
import com.zhouhui.esms.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhouhui
 * @since 2022-05-06
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    RoleMenuMapper roleMenuMapper;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOrUpdateAndSoOn(Menu menu) {
        //因为有些展开项菜单在他的子菜单全选中后他才会选中，
        // 所以在他之后再增加一个子菜单，应该把这个展开项在权限里给删除
        try {
            MenuMapper baseMapper = getBaseMapper();
            if (menu.getMenuId() != null) {
                //代表是修改
                baseMapper.updateById(menu);
            } else {
                //代表增加，先在权限菜单删除展开项,展开项一级二级要全删除
                //判断是几级
                if(menu.getPid() != null){
                    roleMenuMapper.deleteByMenuId(menu.getPid());
                    Menu menu1 = baseMapper.selectById(menu.getPid());
                    roleMenuMapper.deleteByMenuId(menu1.getPid());
                    if(menu1.getPid() != null){
                        roleMenuMapper.deleteByMenuId(menu.getPid());
                    }
                }
                baseMapper.insert(menu);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
